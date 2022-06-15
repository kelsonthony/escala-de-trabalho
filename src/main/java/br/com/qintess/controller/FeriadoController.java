package br.com.qintess.controller;

import br.com.qintess.entities.Feriado;
import br.com.qintess.services.interfaces.ICargoService;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
