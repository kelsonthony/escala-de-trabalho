package br.com.qintess.controller;

import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("log")
public class LogController {

  @Autowired
  private ILogService logService;

  @Autowired
  private IFuncionarioService funcionarioService;

  @GetMapping("/listar")
  public ModelAndView listar(ModelMap model){

    model.addAttribute("logs",this.logService.listar());
    return new ModelAndView("log/list",model);

  }

  @GetMapping("/listar/funcionario/{funcionarioId}")
  public ModelAndView listarFuncionarioId(@PathVariable long funcionarioId, ModelMap model){

    model.addAttribute("funcionario",this.funcionarioService.listarPorId(funcionarioId));
    return new ModelAndView("funcionario/view",model);

  }

}
