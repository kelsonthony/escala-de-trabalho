package br.com.qintess.controller;


import br.com.qintess.entities.Feriado;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @PostMapping("/salvar")
  public String salvar(@Valid @ModelAttribute("feriado") Feriado feriado, BindingResult result, RedirectAttributes attr) {
    if(result.hasErrors()) {
      return "/feriado/add";
    }

    feriadoService.salvar(feriado);
    attr.addFlashAttribute("mensagem", "Feriado criado com sucesso.");
    return "redirect:/feriado/listar";
  }

}
