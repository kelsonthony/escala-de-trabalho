package br.com.qintess.controller;

import br.com.qintess.entities.GeradorDeSenha;
import br.com.qintess.entities.Perfil;
import br.com.qintess.entities.Usuario;
import br.com.qintess.services.interfaces.IPerfilService;
import br.com.qintess.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

  @Autowired
  private IUsuarioService usuarioService;
  @Autowired
  private IPerfilService perfilService;

  @GetMapping("/cadastrar")
  public ModelAndView cadastrar(@ModelAttribute("usuario") Usuario usuario, @ModelAttribute("perfisAdicionado") Perfil perfisAdicionado) {

    List<Perfil> opcoes = new ArrayList<>();

    ModelAndView mv = new ModelAndView("usuario/add");
    mv.addObject("opcoes",opcoes);
    mv.addObject("usuario",usuario);
    mv.addObject("perfisCadastrados",this.perfilService.listar());
    mv.addObject("perfisAdicionado",perfisAdicionado);

    return mv;
  }

  @PostMapping("/salvar")
  public ModelAndView adicionarPerfil(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr){

    Usuario novoUsuario = usuario;
    novoUsuario.setLogin(usuario.getMatricula());

    GeradorDeSenha geradorDeSenha = new GeradorDeSenha();
    String senha = geradorDeSenha.gerarSenha(8);
    novoUsuario.setSenha(new BCryptPasswordEncoder().encode(senha));

    attr.addFlashAttribute("perfisCadastrados",this.perfilService.listar());

    if(result.hasErrors()){
      attr.addFlashAttribute("mensagem",result.getAllErrors());
      return new ModelAndView("redirect:/usuario/cadastrar");
    }

    try{

      this.usuarioService.salvar(novoUsuario);
      usuario.setSenha(senha);

      attr.addFlashAttribute("mensagem","Usuario cadastrado com sucesso");
      attr.addFlashAttribute("usuarioCadastrado",usuario);



    }catch (Exception e){
      attr.addFlashAttribute("mensagem",e.getMessage());
    }


      return new ModelAndView("redirect:/usuarios/cadastrar");

  }

  @GetMapping("/listar")
  public ModelAndView listar(ModelMap model) {
    List<Usuario> usuarios = usuarioService.listar();

    model.addAttribute("usuarios", usuarios);
    return new ModelAndView("/usuario/list", model);
  }

  @GetMapping("/listar/{id}")
  public ModelAndView listarPorId(@PathVariable("id") long id, ModelMap model) {
    Usuario usuario = usuarioService.listarPorId(id);

    model.addAttribute("usuario", usuario);
    return new ModelAndView("/usuario/view", model);
  }


}
