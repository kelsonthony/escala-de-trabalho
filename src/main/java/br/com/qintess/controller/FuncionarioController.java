package br.com.qintess.controller;

import br.com.qintess.entities.*;
import br.com.qintess.exceptions.EscalaException;
import br.com.qintess.services.interfaces.*;
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

    @Autowired
    private IEscalaService escalaService;

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
    public String cadastrar(@ModelAttribute("funcionario") Funcionario funcionario,
                            ModelMap model) {
        model.addAttribute("cargos", cargoService.listar());
        model.addAttribute("equipes", equipeService.listar());
        model.addAttribute("turnos", turnoService.listar());
        model.addAttribute("escalas", escalaService.listar());

        return "/funcionario/add";
    }

    @GetMapping("/salvar")
    public String salvarRedirect(@ModelAttribute("funcionario") Funcionario funcionario){
        return "redirect:/funcionarios/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result,
                         @ModelAttribute("turno") Turno turno,
                         @ModelAttribute("equipe") Equipe equipe,
                         @ModelAttribute("cargo") Cargo cargo,
                         @ModelAttribute("escala") Escala escala,
                         ModelMap model, RedirectAttributes attr) {

        if (result.hasErrors()) {
            model.addAttribute("escalas", escalaService.listar());
            model.addAttribute("cargos", cargoService.listar());
            model.addAttribute("equipes", equipeService.listar());
            model.addAttribute("turnos", turnoService.listar());
            return "/funcionario/add";
        }

        funcionario.setEscala(escala);
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
        model.addAttribute("selectedCargoId", funcionario.getCargo().getCargoId());
        model.addAttribute("selectedEquipeId", funcionario.getEquipe().getEquipeId());
        model.addAttribute("selectedTurnoId", funcionario.getTurno().getId());
        model.addAttribute("selectedLocalidade", funcionario.getLocalidade());

        model.addAttribute("cargos", cargoService.listar());
        model.addAttribute("equipes", equipeService.listar());
        model.addAttribute("turnos", turnoService.listar());

        return new ModelAndView("/funcionario/update", model);
    }

    @GetMapping("/atualizar")
    public String atualizarRedirect(@ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {
        Funcionario f = funcionarioService.listarPorId(funcionario.getFuncionarioId());
        model.addAttribute("cargos", cargoService.listar());
        model.addAttribute("equipes", equipeService.listar());
        model.addAttribute("turnos", turnoService.listar());

        return "/funcionario/update";
    }

    @PutMapping("/atualizar")
    public ModelAndView salvarAtualizacao(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result,
                         @ModelAttribute("turno") Turno turno,
                         @ModelAttribute("equipe") Equipe equipe,
                         @ModelAttribute("cargo") Cargo cargo,
                         ModelMap model, RedirectAttributes attr) {

        if (result.hasErrors()) {
            model.addAttribute("cargos", cargoService.listar());
            model.addAttribute("equipes", equipeService.listar());
            model.addAttribute("turnos", turnoService.listar());

            return new ModelAndView("/funcionario/update");
        }

        funcionario.setCargo(cargo);
        funcionario.setEquipe(equipe);
        funcionario.setTurno(turno);

        funcionarioService.atualizar(funcionario);
        attr.addFlashAttribute("mensagem", "Funcionário atualizado com sucesso.");
        return new ModelAndView("redirect:/funcionarios/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        String mensagem = "Mensagem do sistema: ";
        try {
            funcionarioService.excluir(id);
            mensagem +=  "Funcionário removido com sucesso.";
        } catch (EscalaException e) {
            mensagem += e.getMessage() + " - Código: " + e.getCodigo();
        } catch (RuntimeException e ) {
            mensagem += e.getMessage();
        }
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/funcionarios/listar";
    }
}
