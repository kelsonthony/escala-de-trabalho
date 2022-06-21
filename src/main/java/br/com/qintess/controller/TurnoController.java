package br.com.qintess.controller;


import br.com.qintess.entities.Turno;
import br.com.qintess.entities.TurnoFixo;
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


  @GetMapping("/cadastrar/fixo")
  public String cadastrarTurnoFixo(@ModelAttribute("turno") Turno turno){
    return "turno/fixo/add";
  }

  @GetMapping("/cadastrar/alternado")
  public String cadastrarTurnoAlternado(@ModelAttribute("turno") Turno turno){
    return "turno/alternado/add";
  }

  @PostMapping("/salvar/fixo")
  public String salvarTurnoFixo(@Valid @ModelAttribute("turno") Turno turno, @Valid @ModelAttribute("turnofixo") TurnoFixo turnoFixo,
                       BindingResult result, RedirectAttributes attr) {

    if(result.hasErrors()) {
      return "turno/fixo/add";
    }

    turno.setTurnoFixo(turnoFixo);
    turnoService.salvar(turno);
    attr.addFlashAttribute("mensagem", "Turno criado com sucesso.");
    return "redirect:/turnos/listar";
  }

  @PutMapping("/salvar/fixo")
  public ModelAndView salvarAtualizacaoTurnoFixo(@Valid @ModelAttribute("turno") Turno turno, @Valid @ModelAttribute("turnofixo") TurnoFixo turnoFixo
                                        ,BindingResult result, RedirectAttributes attr) {
    if(result.hasErrors()) {
      return new ModelAndView("turno/fixo/update");
    }

    turno.setTurnoFixo(turnoFixo);
    turnoService.atualizar(turno);
    attr.addFlashAttribute("mensagem", "Turno atualizado com sucesso.");
    return new ModelAndView("redirect:/turnos/listar") ;

  }



  @GetMapping("/{id}/atualizar")
  public ModelAndView atualizar(@PathVariable("id") long id, ModelMap model) {
    Turno turno = turnoService.listarPorId(id);

    if(!turno.getTurnoFixo().equals(null)){
      model.addAttribute("turno", turno);
      model.addAttribute("turnofixo",turno.getTurnoFixo());
      return new ModelAndView("/turno/fixo/update", model);
    }

    return new ModelAndView("redirect:/turnos/listar");
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
