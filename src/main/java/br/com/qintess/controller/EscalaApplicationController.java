package br.com.qintess.controller;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Mes;
import br.com.qintess.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class EscalaApplicationController {

    @Autowired
    private IFuncionarioService funcionarioService;
    @Autowired
    private IEscalaService escalaService;
    @Autowired
    private IMesService mesService;

    @Autowired
    private IDiaService diaService;

    @Autowired
    private IDashboardService dashboardService;


    @GetMapping("/")
    public ModelAndView home(ModelMap model) {

        List<Mes> meses = mesService.listar();

        List<DashboardDto> dtos = dashboardService.listar(meses);
        model.addAttribute("dtos", dtos);
        model.addAttribute("titulo", dashboardService.titulo(meses.get(0).getDataInicio()));
        model.addAttribute("diasDoMes", dashboardService.dias(meses.get(0).getDataInicio()));

        return new ModelAndView("home", model);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/contato-suporte")
    public String contatoSuporte() { return "contato-suporte"; }

}
