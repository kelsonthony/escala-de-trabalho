package br.com.qintess.controller;


import br.com.qintess.entities.Turno;
import br.com.qintess.entities.TurnoAlternado;
import br.com.qintess.entities.TurnoFixo;
import br.com.qintess.exceptions.EscalaException;
import br.com.qintess.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

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

  @GetMapping("/salvar/fixo")
  public String salvarTurnoFixoRedirect(@ModelAttribute("turno") Turno turno){
    return "redirect:/turnos/cadastrar/fixo";
  }

  @PostMapping("/salvar/fixo")
  public String salvarTurnoFixo(@Valid @ModelAttribute("turno") Turno turno,BindingResult resultTurno,
                                @Valid @ModelAttribute("turnofixo") TurnoFixo turnoFixo,
                                BindingResult resultFixo, RedirectAttributes attr) {

    if(resultTurno.hasErrors() || resultFixo.hasErrors()) {
      return "turno/fixo/add";
    }

    try {

      turno.setTurnoFixo(turnoFixo);
      turnoService.salvar(turno);
      attr.addFlashAttribute("mensagem", "Turno criado com sucesso.");

    }catch (Exception e){
      attr.addFlashAttribute("mensagem",e.getMessage());
      return "redirect:/turnos/cadastrar/fixo";
    }


    return "redirect:/turnos/listar";
  }

  @PostMapping("/salvar/alternado")
  public String salvarTurnoAlternado(@Valid @ModelAttribute("turno") Turno turno,BindingResult resultTurno,
                                     @Valid @ModelAttribute("turnoAlternado") TurnoAlternado turnoAlternado,
                                     BindingResult resultAlternado, RedirectAttributes attr) {

    if(resultTurno.hasErrors() || resultAlternado.hasErrors()) {
      return "turno/alternado/add";
    }

    try{

      turno.setTurnoAlternado(turnoAlternado);
      turnoService.salvar(turno);
      attr.addFlashAttribute("mensagem", "Turno criado com sucesso.");

    }catch (Exception e){
      attr.addFlashAttribute("mensagem",e.getMessage());
      return "redirect:/turnos/cadastrar/alternado";
    }

    return "redirect:/turnos/listar";
  }

  @PutMapping("/salvar/fixo")
  public ModelAndView salvarAtualizacaoTurnoFixo(@Valid @ModelAttribute("turno") Turno turno,BindingResult resultTurno,
                                                 @Valid @ModelAttribute("turnofixo") TurnoFixo turnoFixo,
                                                 BindingResult resultFixo, RedirectAttributes attr) {

    if(resultFixo.hasErrors() || resultTurno.hasErrors()) {
      return new ModelAndView("turno/fixo/update");
    }

    turno.setTurnoFixo(turnoFixo);
    turnoService.atualizar(turno);
    attr.addFlashAttribute("mensagem", "Turno atualizado com sucesso.");
    return new ModelAndView("redirect:/turnos/listar") ;

  }

  @PutMapping("/salvar/alternado")
  public ModelAndView salvarAtualizacaoTurnoAlternado(@Valid @ModelAttribute("turno") Turno turno, BindingResult resultTurno,
                                                      @Valid @ModelAttribute("turnoAlternado") TurnoAlternado turnoAlternado,
                                                      BindingResult resultAlternado, RedirectAttributes attr) {

    if(resultAlternado.hasErrors() ||resultTurno.hasErrors()) {
      return new ModelAndView("turno/alternado/update");
    }


    turno.setTurnoAlternado(turnoAlternado);
    turnoService.atualizar(turno);
    attr.addFlashAttribute("mensagem", "Turno atualizado com sucesso.");
    return new ModelAndView("redirect:/turnos/listar") ;

  }


  @GetMapping("/{id}/atualizar")
  public ModelAndView atualizar(@PathVariable("id") long id, ModelMap model) {
    Turno turno = turnoService.listarPorId(id);

    if(!(Objects.isNull(turno.getTurnoFixo()))){
      model.addAttribute("turno", turno);
      model.addAttribute("turnofixo",turno.getTurnoFixo());
      return new ModelAndView("/turno/fixo/update", model);
    }else if (!(Objects.isNull(turno.getTurnoAlternado()))){

      model.addAttribute("turno",turno);
      model.addAttribute("turnoAlternado",turno.getTurnoAlternado());
      return new ModelAndView("/turno/alternado/update", model);

    }
    return new ModelAndView("redirect:/turnos/listar");

  }


  @GetMapping("/listar")
  public ModelAndView listar(ModelMap model){
    model.addAttribute("turnos",this.turnoService.listar());
    return new ModelAndView("/turno/list",model);
  }

  @GetMapping("{id}/remover")
  public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
    String mensagem = "Mensagem do sistema: ";
    try {
      turnoService.excluir(id);
      mensagem += "Turno removido com sucesso.";
    } catch (EscalaException e) {
      mensagem += e.getMessage() + " - Código: " + e.getCodigo();
    } catch (RuntimeException e) {
      mensagem += e.getMessage();
    }
    attr.addFlashAttribute("mensagem", mensagem);
    return "redirect:/turnos/listar";
  }

}

