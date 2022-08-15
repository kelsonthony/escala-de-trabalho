package br.com.qintess.controller;

import br.com.qintess.entities.Perfil;
import br.com.qintess.entities.Usuario;
import br.com.qintess.services.interfaces.IPerfilService;
import br.com.qintess.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("usuario")
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
    mv.addObject("perfis",this.perfilService.listar());
    mv.addObject("perfisAdicionado",perfisAdicionado);

    return mv;
  }

  @GetMapping("/cadastrar/adicionarperfil")
  public String adicionarPerfil(@ModelAttribute("usuario") Usuario usuario, @ModelAttribute("perfisAdicionado") Perfil perfisAdicionado, RedirectAttributes attr){

      List<Perfil> listaPerfis = usuario.getPerfis();

      listaPerfis.add(perfisAdicionado);
      usuario.setPerfis(listaPerfis);

      attr.addFlashAttribute("usuario",usuario);

      return "redirect:/usuario/cadastrar";

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
