package br.com.qintess.controller;

import br.com.qintess.entities.Funcionario;
import br.com.qintess.services.FuncionarioService;
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
    private FuncionarioService funcionarioService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("funcionarios", funcionarioService.listar());
        return new ModelAndView("/funcionario/list", model);
    }

    @GetMapping("/listar/cargo/{cargoId}")
    public ModelAndView listarPorCargo(@PathVariable("cargoId") long cargoId, ModelMap model) {
        List<Funcionario> funcionarios = funcionarioService.listarPorCargo(cargoId);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("cargoId", cargoId);
        model.addAttribute("cargo", funcionarios.get(0).getCargo().getNome() + " (" + funcionarios.get(0).getCargo().getSigla() + ") ");
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
