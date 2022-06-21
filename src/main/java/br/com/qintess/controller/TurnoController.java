package br.com.qintess.controller;

import br.com.qintess.entities.Turno;
import br.com.qintess.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("turnos")
public class TurnoController {

  @Autowired
  private TurnoService turnoService;


  @GetMapping("/cadastrar")
  public String cadastrar(@ModelAttribute("turno") Turno turno){
    return "turno/add";
  }

  @PostMapping("/salvar")
  public String salvar(@Valid @ModelAttribute("turno") Turno turno, BindingResult result, RedirectAttributes attr) {
    if(result.hasErrors()) {
      return "/turno/add";
    }

    turnoService.salvar(turno);
    attr.addFlashAttribute("mensagem", "Turno criado com sucesso.");
    return "redirect:/turnos/listar";
  }


  @GetMapping("/listar")
  public ModelAndView listar(ModelMap model){
    model.addAttribute("turnos",this.turnoService.listar());
    return new ModelAndView("/turno/list",model);
  }

  @GetMapping("{id}/remover")
  public String remover(@PathVariable("id") long id, RedirectAttributes attr){

    if(turnoService.listarPorId(id) == null){
      attr.addFlashAttribute("mensagem", "Turno informado não existe");
      return "redirect:/turnos/listar";
    }

    turnoService.excluir(id);
    attr.addFlashAttribute("mensagem", "Turno removido com sucesso");
    return "redirect:/turnos/listar";

  }



}
