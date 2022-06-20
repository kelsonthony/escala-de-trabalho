package br.com.qintess.controller;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Equipe;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.entities.Turno;
import br.com.qintess.services.interfaces.ICargoService;
import br.com.qintess.services.interfaces.IEquipeService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private IFuncionarioService funcionarioService;

    @Autowired
    private ICargoService cargoService;

    @Autowired
    private IEquipeService equipeService;

    @Autowired
    private ITurnoService turnoService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("funcionarios", funcionarioService.listar());
        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/listar/{funcionarioId}")
    public ModelAndView listarPorId(@PathVariable("funcionarioId") long funcionarioId, ModelMap model) {
        model.addAttribute("funcionario", funcionarioService.listarPorId(funcionarioId));
        return new ModelAndView("/funcionario/view", model);
    }

    @GetMapping("/listar/cargo/{cargoId}")
    public ModelAndView listarPorCargo(@PathVariable("cargoId") long cargoId, ModelMap model) {
        Cargo cargo = cargoService.listarPorId(cargoId);
        List<Funcionario> funcionarios = funcionarioService.listarPorCargo(cargoId);

        model.addAttribute("cargoId", cargoId);
        model.addAttribute("nome", cargo.getNome() + " (" + cargo.getSigla() + ") ");
        model.addAttribute("type", "Cargos");
        if(!funcionarios.isEmpty())
            model.addAttribute("funcionarios", funcionarios);

        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/listar/equipe/{equipeId}")
    public ModelAndView listarPorEquipe(@PathVariable("equipeId") long equipeId, ModelMap model){
        Equipe equipe = equipeService.listarPorId(equipeId);
        List<Funcionario> funcionarios = funcionarioService.listarPorEquipe(equipeId);

        model.addAttribute("equipeId", equipeId);
        model.addAttribute("nome", equipe.getNome());
        model.addAttribute("type", "Equipes");
        if(!funcionarios.isEmpty())
            model.addAttribute("funcionarios", funcionarios);

        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/listar/turno/{turnoId}")
    public ModelAndView listarPorTurno(@PathVariable("turnoId") long turnoId, ModelMap model){
        Turno turno = turnoService.listarPorId(turnoId);
        List<Funcionario> funcionarios = funcionarioService.listarPorTurno(turnoId);

        model.addAttribute("turnoId", turnoId);
        model.addAttribute("nome", turno.getNome());
        model.addAttribute("type", "Turnos");
        if(!funcionarios.isEmpty())
            model.addAttribute("funcionarios", funcionarios);

        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/cadastrar/cargo/{cargoId}")
    public String cadastrar(@ModelAttribute("funcionario") Funcionario funcionario, @PathVariable("cargoId") long cargoId) {
        return "/funcionario/add";
    }

    @PostMapping("/salvar/cargo/{cargoId}")
    public String salvar(@PathVariable("cargoId") long cargoId, @Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/funcionario/add";
        }

        funcionarioService.salvar(funcionario, cargoId);
        attr.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso.");
        return "redirect:/cargos/" + cargoId + "/funcionarios/listar";
    }

    @GetMapping("/{funcionarioId}/atualizar/cargo/{cargoId}")
    public ModelAndView atualizar(@PathVariable("cargoId") long cargoId, @PathVariable("funcionarioId") long funcionarioId, ModelMap model) {
        Funcionario funcionario = funcionarioService.listarPorCargoIdEFuncionarioId(cargoId, funcionarioId);
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("cargoId", cargoId);
        return new ModelAndView("/funcionario/add", model);
    }

    @PutMapping("/salvar/cargo/{cargoId}")
    public ModelAndView salvarAtualizacao(@PathVariable("cargoId") long cargoId, @Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/funcionario/add");
        }

        funcionarioService.atualizar(funcionario, cargoId);
        attr.addFlashAttribute("mensagem", "Funcionário atualizado com sucesso.");
        return new ModelAndView("redirect:/cargos/" + cargoId + "funcionarios/listar");
    }

    @GetMapping("/funcionarioId}/remover/cargo/{cargoId}")
    public String remover(@PathVariable("cargoId") long cargoId, @PathVariable("funcionarioId") long funcionarioId, RedirectAttributes attr) {
        funcionarioService.excluir(cargoId, funcionarioId);
        attr.addFlashAttribute("mensagem", "Funcionário excluído com sucesso.");
        return "redirect:/cargos/" + cargoId + "funcionarios/listar";
    }
}
