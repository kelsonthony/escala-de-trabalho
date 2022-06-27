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
@RequestMapping("escalas")
public class EscalaController {

    @Autowired
    private IEscalaService escalaService;

    @Autowired
    private IFuncionarioService funcionarioService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        List<Escala> escalas = escalaService.listar();

        model.addAttribute("escalas", escalas);
        return new ModelAndView("/escala/list", model);
    }

}
