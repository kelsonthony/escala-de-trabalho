package br.com.qintess.controller;

import br.com.qintess.entities.Cargo;
import br.com.qintess.services.interfaces.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.modelmbean.ModelMBean;
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
}
