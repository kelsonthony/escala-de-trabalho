package br.com.qintess.controller;

import br.com.qintess.entities.Turno;
import br.com.qintess.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("turnos")
public class TurnoController {

  @Autowired
  private TurnoService turnoService;


  @GetMapping("/cadastrar")
  public String cadastrar(@ModelAttribute("turno") Turno turno){
    return "turno/add";
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
