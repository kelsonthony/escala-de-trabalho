package br.com.qintess.controller;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.services.interfaces.IEscalaService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class EscalaApplicationController {

    @Autowired
    private IEscalaService escalaService;

    @Autowired
    private IFuncionarioService funcionarioService;

    @GetMapping("/")
    public ModelAndView home(ModelMap model) {
        LocalDate dataUltimaEscala = escalaService.listarUltimaEscala();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/yyyy");
        String titulo = dataUltimaEscala.format(formatter);

        model.addAttribute("titulo", titulo);
        model.addAttribute("funcionarios", funcionarioService.listar());

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
