package br.com.qintess.controller;

import br.com.qintess.dashboard.entities.Dashboard;
import br.com.qintess.dashboard.entities.Especialidade;
import br.com.qintess.dashboard.entities.EspecialidadeDto;
import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Turno;
import br.com.qintess.services.interfaces.ICargoService;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("dashboards")
public class DashboardController {

  private final ICargoService cargoService;
  private final ITurnoService turnoService;

  public DashboardController(ICargoService cargoService, ITurnoService turnoService) {
    this.cargoService = cargoService;
    this.turnoService = turnoService;
  }

  @GetMapping("/cadastrar")
  public ModelAndView add(@ModelAttribute("dashboard") Dashboard dashboard){

    List<Turno> turnos = this.turnoService.listar().stream().filter(t -> t.getPadraoSistema() != 1).collect(Collectors.toList());


    ModelAndView mv = new ModelAndView("dashboard/add");
    mv.addObject("cargos",this.cargoService.listar());
    mv.addObject("turnos",turnos);
    return mv;

  }

  @PostMapping("/adicionar/cargo")
  public String adicionarCargo(@ModelAttribute("especialidadeDto") EspecialidadeDto especialidadeDto, BindingResult result,
                               @ModelAttribute("dashboard") Dashboard dashboard,
                                     RedirectAttributes attr){

    if(result.hasErrors()){
      attr.addFlashAttribute("mensagem",result.getAllErrors());
      return "turno/list";
    }

    Dashboard novoDashboard = dashboard;

    Cargo cargo = this.cargoService.listarPorId(especialidadeDto.getCargo());
    Turno turno = this.turnoService.listarPorId(especialidadeDto.getTurno());

    for(int i = 0; i < especialidadeDto.getQuantidade(); i++ ){

      Especialidade especialidade = new Especialidade();
      especialidade.setCargo(cargo);
      especialidade.setTurno(turno);

      novoDashboard.getEspecialidades().add(especialidade);

    }

    attr.addFlashAttribute("dashboard",dashboard);
    return "redirect:/dashboards/cadastrar";

  }

}
