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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("gerenciamento")
public class GerenciamentoMesController {

  @Autowired
  private IFuncionarioService funcionarioService;
  @Autowired
  private IEscalaService escalaService;
  @Autowired
  private IGerenciamentoMesFixoService gerenciamentoMesFixoService;

  @GetMapping("/{funcionarioId}/{escalaId}/cadastrar")
  public String cadastrar(
    @PathVariable("funcionarioId") long funcionarioId,
    @PathVariable("escalaId") long escalaId,
    RedirectAttributes attr) {

    Funcionario funcionario = this.funcionarioService.listarPorId(funcionarioId);
    Escala escala = this.escalaService.listarPorId(escalaId);

    try{
      this.gerenciamentoMesFixoService.cadastrarMes(funcionario,escala);
      attr.addFlashAttribute("Mês cadastrado com sucesso.");
    }catch (Exception e){
      attr.addFlashAttribute("mensagem",e.getMessage());
      return "redirect:/";
    }

    return "redirect:/";

  }

  @GetMapping("/lista/cadastrar")
  public String listaCadastrar() {

    Escala escala = this.escalaService.listarPorId(1);
    gerenciamentoMesFixoService.cadastrarListaMeses(escala);

    return "redirect:/";

  }



}
