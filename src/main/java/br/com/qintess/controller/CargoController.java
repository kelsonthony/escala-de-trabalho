package br.com.qintess.controller;

import br.com.qintess.entities.Cargo;
import br.com.qintess.exceptions.EscalaException;
import br.com.qintess.services.interfaces.ICargoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("cargos")
public class CargoController {

    @Autowired
    private ICargoService cargoService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("cargos", cargoService.listar());
        return new ModelAndView("/cargo/list", model);
    }

    @GetMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("cargo") Cargo cargo) {
        return "/cargo/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "/cargo/add";
        }

        cargoService.salvar(cargo);
        attr.addFlashAttribute("mensagem", "Cargo criado com sucesso.");
        return "redirect:/cargos/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizar(@PathVariable("id") long id, ModelMap model) {
        Cargo cargo = cargoService.listarPorId(id);
        model.addAttribute("cargo", cargo);
        return new ModelAndView("/cargo/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView salvarAtualizacao(@Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return new ModelAndView("/cargo/add");
        }

        cargoService.atualizar(cargo);
        attr.addFlashAttribute("mensagem", "Cargo atualizado com sucesso.");
        return new ModelAndView("redirect:/cargos/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        String mensagem = "Mensagem do sistema: ";
        try {
            cargoService.excluir(id);
            mensagem +=  "Cargo excluído com sucesso.";
        } catch (EscalaException e) {
            mensagem += e.getMessage() + " - Código: " + e.getCodigo();
        } catch (RuntimeException e ) {
            mensagem += e.getMessage();
        }
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/cargos/listar";
    }
}
