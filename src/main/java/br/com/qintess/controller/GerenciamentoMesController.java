package br.com.qintess.controller;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.services.interfaces.IEscalaService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.IGerenciamentoMesFixoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

  @GetMapping("/cadastrar/funcionario")
  public ModelAndView cadastrarPorFuncionario(@ModelAttribute("escala") Escala escala,
                                           @ModelAttribute("funcionario") Funcionario funcionario,
                                           ModelMap model) {
    model.addAttribute("escalas", escalaService.listar());
    model.addAttribute("funcionarios", funcionarioService.listar());
    return new ModelAndView("/gerenciamento/add-funcionario");
  }

  @ResponseBody
  @PostMapping("/gerar/funcionario/escala")
  public ModelAndView gerarPorFuncionario(@RequestParam(name = "funcionarioId") Long funcionarioId,
                                @RequestParam(name = "escalaId") Long escalaId,
                                RedirectAttributes attr) {
    if (funcionarioId.longValue() == 0
        || funcionarioId.equals(null)
        || escalaId.longValue() == 0
        || escalaId.equals(null)) {

      attr.addFlashAttribute("mensagem", "As listas não podem estar em branco. Selecione as opções desejadas.");
      return new ModelAndView("redirect:/gerenciamento/cadastrar/funcionario");
    }

    Funcionario funcionario = this.funcionarioService.listarPorId(funcionarioId.longValue());
    Escala escala = this.escalaService.listarPorId(escalaId.longValue());

    try {
      this.gerenciamentoMesFixoService.cadastrarMes(funcionario, escala);
      attr.addFlashAttribute("mensagem", "Escala x Mês gerados com sucesso.");
    } catch (Exception e) {
      attr.addFlashAttribute("mensagem", e.getMessage());
      return new ModelAndView("redirect:/gerenciamento/cadastrar/funcionario");
    }

    return new ModelAndView("redirect:/gerenciamento/cadastrar/funcionario");
  }

  @GetMapping("/cadastrar")
  public ModelAndView cadastrar(@ModelAttribute("escala") Escala escala,
                                              ModelMap model) {
    model.addAttribute("escalas", escalaService.listar());
    return new ModelAndView("/gerenciamento/add");
  }

  @ResponseBody
  @PostMapping("/gerar/escala")
  public ModelAndView gerar(@RequestParam(name = "escalaId") Long escalaId,
                            RedirectAttributes attr) {
    if (escalaId.longValue() == 0 || escalaId.equals(null)) {
      attr.addFlashAttribute("mensagem", "A lista não podem estar em branco. Selecione a opção desejada.");
      return new ModelAndView("redirect:/gerenciamento/cadastrar");
    }

    Escala escala = this.escalaService.listarPorId(escalaId.longValue());

    try {
      this.gerenciamentoMesFixoService.cadastrarListaMeses(escala);
      attr.addFlashAttribute("mensagem", "Escalas geradas com sucesso.");
    } catch (Exception e) {
      attr.addFlashAttribute("mensagem", e.getMessage());
      return new ModelAndView("redirect:/gerenciamento/cadastrar");
    }

    return new ModelAndView("redirect:/gerenciamento/cadastrar");
  }

}
