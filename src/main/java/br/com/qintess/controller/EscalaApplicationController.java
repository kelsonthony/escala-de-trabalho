package br.com.qintess.controller;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Mes;
import br.com.qintess.exceptions.EscalaException;
import br.com.qintess.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public ModelAndView home(@ModelAttribute("escala") Escala escala,
                             ModelMap model) {

        List<Mes> meses = mesService.listar();
        List<DashboardDto> dtos = dashboardService.listar(meses);
        model.addAttribute("escalas", escalaService.listar());
        model.addAttribute("dtos", dtos);
        model.addAttribute("titulo", "Lista de Escalas Cadastradas");
        model.addAttribute("diasDoMes", dashboardService.dias(meses.get(0)));

        return new ModelAndView("home", model);
    }

    @PostMapping("/filtrar")
    @ResponseBody
    public ModelAndView homeFiltered(@RequestParam(name = "escalaId") Long escalaId,
                               ModelMap model,
                               RedirectAttributes attr) {

        if (escalaId.longValue() == 0)
            return new ModelAndView("redirect:/");

        List<Mes> meses;

        if (escalaId.equals(null))
            meses = mesService.listar();
        else
            meses = mesService.listarPorEscala(escalaId.longValue());

        if (!meses.isEmpty()) {
            List<DashboardDto> dtos = dashboardService.listar(meses);

            model.addAttribute("dtos", dtos);
            model.addAttribute("titulo", dashboardService.titulo(meses.get(0)));
            model.addAttribute("diasDoMes", dashboardService.dias(meses.get(0)));
        }

        model.addAttribute("escalas", escalaService.listar());
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
