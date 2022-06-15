package br.com.qintess.controller;

import br.com.qintess.services.interfaces.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.modelmbean.ModelMBean;

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
}
