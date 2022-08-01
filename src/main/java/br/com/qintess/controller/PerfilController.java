package br.com.qintess.controller;

import br.com.qintess.entities.Autorizacao;
import br.com.qintess.entities.Perfil;
import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.interfaces.IUsuarioRepository;
import br.com.qintess.services.interfaces.IAutorizacaoService;
import br.com.qintess.services.interfaces.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("perfil")
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;

    @Autowired
    private IAutorizacaoService autorizacaoService;


    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {

      List<Perfil> perfis = perfilService.listar();
      model.addAttribute("perfis", perfis);
      return new ModelAndView("/perfil/list", model);
    }

    @GetMapping("{id}/atualizar")
    public ModelAndView atualizar(@PathVariable("id") Long id){

      ModelAndView mv = new ModelAndView("/perfil/update");

      Optional<Perfil> perfilCadastrado = this.perfilService.listarPorId(id);
      List<Autorizacao> autorizacaoList = this.autorizacaoService.listar();

      if(perfilCadastrado.isPresent()){
        mv.addObject("perfil",perfilCadastrado.get());
        mv.addObject("autorizacoesList",autorizacaoList);
        return mv;
      }else{
        return "redirect:";
      }
       return mv;
    }

  @GetMapping("{id}/remover")
  public String remover(@PathVariable("id") Long id, RedirectAttributes attr){

      try {

        perfilService.excluir(id);
        attr.addFlashAttribute("mensagem","Usuario excluido com sucesso");
        return "redirect:/perfil/listar";

      }catch (Exception e){

        attr.addFlashAttribute("mensagem",e.getMessage());
        return "redirect:/perfil/listar";

      }

  }





}
