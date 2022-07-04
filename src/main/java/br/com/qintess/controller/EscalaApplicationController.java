package br.com.qintess.controller;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.entities.Mes;
import br.com.qintess.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@Controller
public class EscalaApplicationController {

    @Autowired
    private IFuncionarioService funcionarioService;
    @Autowired
    private IEscalaService escalaService;
    @Autowired
    private IMesService mesService;


    @GetMapping("/")
    public ModelAndView home(ModelMap model) {

        List<Mes> meses  = mesService.listar();
        LocalDate dataInicio = meses.get(0).getDataInicio();
        List<LocalDate> diasDoMes = mesService.dias(meses.get(0).getDataInicio());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/yyyy");

        model.addAttribute("titulo", dataInicio.format(formatter));
        model.addAttribute("meses", meses);
        model.addAttribute("diasDoMes", diasDoMes);

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
