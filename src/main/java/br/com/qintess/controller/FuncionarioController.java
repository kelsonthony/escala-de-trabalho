package br.com.qintess.controller;

import br.com.qintess.entities.*;
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
        List<Funcionario> funcionarios = funcionarioService.listar();

        model.addAttribute("funcionarios", funcionarios);
        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/listar/{funcionarioId}")
    public ModelAndView listarPorId(@PathVariable("funcionarioId") long funcionarioId, ModelMap model) {
        model.addAttribute("funcionario", funcionarioService.listarPorId(funcionarioId));
        return new ModelAndView("/funcionario/view", model);
    }

    @GetMapping("/listar/cargo/{id}")
    public ModelAndView listarPorCargo(@PathVariable("id") long id, ModelMap model) {
        Cargo cargo = cargoService.listarPorId(id);
        List<Funcionario> funcionarios = funcionarioService.listarPorCargo(id);

        model.addAttribute("id", id);
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

    @GetMapping("/listar/turno/{id}")
    public ModelAndView listarPorTurno(@PathVariable("id") long id, ModelMap model){
        Turno turno = turnoService.listarPorId(id);
        List<Funcionario> funcionarios = funcionarioService.listarPorTurno(id);

        model.addAttribute("id", id);
        model.addAttribute("nome", turno.getNome());
        model.addAttribute("type", "Turnos");
        if(!funcionarios.isEmpty())
            model.addAttribute("funcionarios", funcionarios);

        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {
        model.addAttribute("cargos", cargoService.listar());
        model.addAttribute("equipes", equipeService.listar());
        model.addAttribute("turnos", turnoService.listar());

        return "/funcionario/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("funcionario") Funcionario funcionario,
                         @ModelAttribute("turno") Turno turno,
                         @ModelAttribute("equipe") Equipe equipe,
                         @ModelAttribute("cargo") Cargo cargo,
                         BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            attr.addFlashAttribute("mensagem", "Erro ao cadastrar funcionário.");
            //attr.addFlashAttribute("mensagem", result.getAllErrors());
            return "redirect:/funcionarios/listar";
        }

        funcionario.setCargo(cargo);
        funcionario.setEquipe(equipe);
        funcionario.setTurno(turno);
        funcionarioService.salvar(funcionario);

        attr.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/{funcionarioId}/atualizar")
    public ModelAndView atualizar(@PathVariable("funcionarioId") long funcionarioId, ModelMap model) {
        Funcionario funcionario = funcionarioService.listarPorId(funcionarioId);
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("cargos", cargoService.listar());
        model.addAttribute("equipes", equipeService.listar());
        model.addAttribute("turnos", turnoService.listar());

        return new ModelAndView("/funcionario/add", model);
    }

    @PutMapping("/salvar/cargo/{cargoId}")
    public ModelAndView salvarAtualizacao(@PathVariable("cargoId") long cargoId, @Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/funcionario/add");
        }

        funcionarioService.atualizar(funcionario);
        attr.addFlashAttribute("mensagem", "Funcionário atualizado com sucesso.");
        return new ModelAndView("redirect:/cargos/" + cargoId + "funcionarios/listar");
    }

    @GetMapping("/funcionarioId}/remover")
    public String remover(@PathVariable("funcionarioId") long funcionarioId, RedirectAttributes attr) {
        funcionarioService.excluir(funcionarioId);
        attr.addFlashAttribute("mensagem", "Funcionário excluído com sucesso.");
        return "redirect:/funcionarios/listar";
    }
}
