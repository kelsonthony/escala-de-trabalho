package br.com.qintess.controller;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.services.interfaces.IEscalaService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.IGerenciamentoMesFixoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gerenciamento")
public class GerenciamentoMesController {

  @Autowired
  private IFuncionarioService funcionarioService;
  @Autowired
  private IEscalaService escalaService;
  @Autowired
  private IGerenciamentoMesFixoService gerenciamentoMesFixoService;

  @GetMapping("/cadastrar")
  public ModelAndView cadastrar(ModelMap model) {

    Funcionario funcionario = this.funcionarioService.listarPorId(2);
    Escala escala = this.escalaService.listarPorId(1);
    this.gerenciamentoMesFixoService.cadastrarMes(funcionario,escala);

    return null;

  }



}
