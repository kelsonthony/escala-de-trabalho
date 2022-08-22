package br.com.qintess.controller;


import br.com.qintess.entities.dtos.CadastroUsuarioDto;
import br.com.qintess.entities.Perfil;
import br.com.qintess.entities.Usuario;
import br.com.qintess.services.interfaces.IPerfilService;
import br.com.qintess.services.interfaces.IUsuarioService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

  @Autowired
  private IUsuarioService usuarioService;
  @Autowired
  private IPerfilService perfilService;


  @GetMapping("/cadastrar")
  public ModelAndView cadastrar(@ModelAttribute("usuario") CadastroUsuarioDto usuarioDto, @ModelAttribute("perfil") Perfil perfil) {

    ModelAndView mv = new ModelAndView("usuario/add");

    //mv.addObject("usuario",usuarioDto);
    mv.addObject("perfis",this.perfilService.listar());
    return mv;
  }

  @PostMapping("/salvar")
  public ModelAndView salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
                                    @ModelAttribute("perfilId") Long perfilId, RedirectAttributes attr){

    if(result.hasErrors()){
      return new ModelAndView("usuario/add");
    }

    try{

      Perfil perfil = this.perfilService.listarPorId(perfilId).get();
      this.usuarioService.salvar(usuario,perfil);
      attr.addFlashAttribute("mensagem","Usuário cadastrado com sucesso");

    }catch (Exception e){
      attr.addFlashAttribute("mensagem",e.getMessage());
    }

    return new ModelAndView("redirect:/usuarios/listar");

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

  @GetMapping("/{id}/atualizar")
  public String atualizar(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr){

    try{

      Usuario usuario = usuarioService.listarPorId(id);
      Perfil perfil = new Perfil();
      List<Perfil> perfis = perfilService.listar();

      model.addAttribute("usuario",usuario);
      model.addAttribute("perfil",perfil);
      model.addAttribute("perfis",perfis);

      return "/usuario/update";

    }catch (Exception e){

      attr.addAttribute("mensagem",e.getMessage());
      return "redirect:/usuarios/listar";

    }


  }

  @PutMapping("/atualizar/salvar")
  public ModelAndView salvarAtualizacao(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
                                  @ModelAttribute("perfilId") Long perfilId, ModelMap model, RedirectAttributes attr){

    model.addAttribute("perfis",this.perfilService.listar());

    if(result.hasErrors()){
      return new ModelAndView("/usuario/update");
    }

      try{

          Perfil perfil = this.perfilService.listarPorId(perfilId).get();
          this.usuarioService.atualizar(usuario,perfil);

          attr.addAttribute("mensagem","Atualizado com sucesso");
          return new ModelAndView("redirect:/usuarios/listar");


      }catch (Exception e){
        attr.addAttribute("mensagem",e.getMessage());
        return new ModelAndView("redirect:/usuarios/"+usuario.getId()+"/atualizar");
      }


  }

  @GetMapping("/remover/{idUsuario}/perfil/{idPerfil}")
  public String removePerfil(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idPerfil") Long idPerfil, ModelMap model, RedirectAttributes attr){

    try{

      Usuario usuario = this.usuarioService.listarPorId(idUsuario);
      Perfil perfil = this.perfilService.listarPorId(idPerfil).get();

      List<Perfil> perfilUsuario = usuario.getPerfis();
      perfilUsuario.remove(perfil);
      usuario.setPerfis(perfilUsuario);

      //this.usuarioService.salvar(usuario);

      model.addAttribute("usuario",usuario);
      model.addAttribute("perfis",this.perfilService.listar());
      model.addAttribute("perfil",perfil);

      return "/usuario/update";

    }catch (Exception e){

      attr.addFlashAttribute("mensagem",e.getMessage());
      return "redirect:/usuarios/listar";

    }

  }

  @GetMapping("/{id}/remover")
  public String removerUsuario(@PathVariable final Long id, RedirectAttributes atributo){

    try{
      usuarioService.excluir(id);
      atributo.addFlashAttribute("mensagem","Usuario removido com sucesso");
    }catch (Exception e){
      atributo.addFlashAttribute("mensagem",e.getMessage());
    }

    return "redirect:/usuarios/listar";
  }

}
