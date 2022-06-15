package br.com.qintess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EscalaController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/lista-usuarios")
    public String listarUsuarios() {
        return "lista-usuarios";
    }

    @GetMapping("/dados-acesso")
    public String relatorioAcessos() {
        return "dados-acesso";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/contato-suporte")
    public String contatoSuporte() { return "contato-suporte"; }
}
