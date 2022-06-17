package br.com.qintess.controller;


import br.com.qintess.entities.Feriado;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("feriados")
public class FeriadoController {

  @Autowired
  private IFeriadoService feriadoService;

  @GetMapping("/listar")
  public ModelAndView listar(ModelMap model) {
    model.addAttribute("feriados", feriadoService.listar());
    return new ModelAndView("/feriado/list", model);
  }

  @GetMapping("/cadastrar")
  public String cadastrar(@ModelAttribute("feriado") Feriado feriado){
    return "feriado/add";

  }

  @GetMapping("{id}/remover")
  public String remover(@PathVariable("id") Integer id, RedirectAttributes attr){

    if(feriadoService.listarPorId(id) == null){
      attr.addFlashAttribute("mensagem", "Feriado informado não existe");
      return "redirect:/feriados/listar";
    }

    feriadoService.excluir(id);
    attr.addFlashAttribute("mensagem", "Feriado removido com sucesso");
    return "redirect:/feriados/listar";

  }



  @PostMapping("/salvar")
  public String salvar(@Valid @ModelAttribute("feriado") Feriado feriado, BindingResult result, RedirectAttributes attr) {
    if(result.hasErrors()) {
      return "/feriado/add";
    }

    feriadoService.salvar(feriado);
    attr.addFlashAttribute("mensagem", "Feriado criado com sucesso.");
    return "redirect:/feriados/listar";
  }

}
