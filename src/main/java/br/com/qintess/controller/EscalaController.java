package br.com.qintess.controller;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.EscalaTipo;
import br.com.qintess.exceptions.EscalaException;
import br.com.qintess.services.interfaces.IEscalaService;
import br.com.qintess.services.interfaces.IEscalaTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("escalas")
public class EscalaController {

    @Autowired
    private IEscalaService escalaService;

    @Autowired
    private IEscalaTipoService escalaTipoService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("escalas", escalaService.listar());
        return new ModelAndView("/escala/list", model);
    }

    @GetMapping("/listar/tipo")
    public ModelAndView listarTipo(ModelMap model) {
        model.addAttribute("tipos", escalaTipoService.listar());
        return new ModelAndView("/escala/tipo/list", model);
    }

    @GetMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("escala") Escala escala) {
        return "/escala/add";
    }

    @GetMapping("/salvar")
    public String salvarRedirect(@ModelAttribute("escala") Escala escala){
        return "redirect:/escalas/cadastrar";
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("escala") Escala escala,
                        BindingResult result,
                        RedirectAttributes attr) {

        if(result.hasErrors()) {
            return new ModelAndView("/escala/add");
        }

        escalaService.salvar(escala);

        attr.addFlashAttribute("mensagem", "Escala criada com sucesso.");
        return new ModelAndView("redirect:/escalas/listar");
    }

    @GetMapping("/cadastrar/tipo")
    public String cadastrarTipo(@ModelAttribute("escalaTipo") EscalaTipo escalaTipo, ModelMap model) {
        model.addAttribute("escalas", escalaService.listar());
        return "/escala/tipo/add";
    }

    @GetMapping("/salvar/tipo")
    public String salvarTipoRedirect(@ModelAttribute("escalaTipo") EscalaTipo escalaTipo){
        return "redirect:/escalas/cadastrar/tipo";
    }

    @PostMapping("/salvar/tipo")
    public String salvarTipo(@Valid @ModelAttribute("escalaTipo") EscalaTipo escalaTipo,
                             BindingResult result,
                             @ModelAttribute("escala") Escala escala,
                             ModelMap model,
                             RedirectAttributes attr) {
        if(result.hasErrors()) {
            model.addAttribute("escalas", escalaService.listar());
            return "/escala/tipo/add";
        }

        escalaTipo.setEscala(escala);
        escalaTipoService.salvar(escalaTipo);
        attr.addFlashAttribute("mensagem", "Tipo de escala criada com sucesso.");
        return "redirect:/escalas/listar/tipo";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizar(@PathVariable("id") long id, ModelMap model) {
        Escala escala = escalaService.listarPorId(id);
        model.addAttribute("escala", escala);
        return new ModelAndView("/escala/add", model);
    }

    @GetMapping("/atualizar/{id}/tipo")
    public ModelAndView atualizarTipo(@PathVariable("id") long id, ModelMap model) {

        model.addAttribute("escalas", escalaService.listar());
        model.addAttribute("escalaTipo", escalaTipoService.listarPorId(id));
        return new ModelAndView("/escala/tipo/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView salvarAtualizacao(@Valid @ModelAttribute("escala") Escala escala, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return new ModelAndView("/escala/add");
        }

        escalaService.atualizar(escala);
        attr.addFlashAttribute("mensagem", "Escala atualizada com sucesso.");
        return new ModelAndView("redirect:/escalas/listar");
    }

    @PutMapping("/salvar/tipo")
    public ModelAndView salvarTipoAtualizacao(@Valid @ModelAttribute("escalaTipo") EscalaTipo escalaTipo,
                                              BindingResult result,
                                              @ModelAttribute("escala") Escala escala,
                                              RedirectAttributes attr,
                                              ModelMap model) {
        if(result.hasErrors()) {
            model.addAttribute("escalas", escalaService.listar());
            return new ModelAndView("/escala/tipo/add");
        }

        escalaTipo.setEscala(escala);
        escalaTipoService.atualizar(escalaTipo);
        attr.addFlashAttribute("mensagem", "Tipo de Escala atualizada com sucesso.");
        return new ModelAndView("redirect:/escalas/listar/tipo");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        String mensagem = "Mensagem do sistema: ";
        try {
            escalaService.excluir(id);
            mensagem +=  "Escala excluída com sucesso.";
        } catch (EscalaException e) {
            mensagem += e.getMessage() + " - Código: " + e.getCodigo();
        } catch (RuntimeException e ) {
            mensagem += e.getMessage();
        }
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/escalas/listar";
    }

    @GetMapping("tipo/{id}/remover")
    public String removerTipo(@PathVariable("id") long id, RedirectAttributes attr) {
        String mensagem = "Mensagem do sistema: ";
        try {
            escalaService.excluirTipo(id);
            mensagem +=  "Tipo de escala excluído com sucesso.";
        } catch (EscalaException e) {
            mensagem += e.getMessage() + " - Código: " + e.getCodigo();
        } catch (RuntimeException e ) {
            mensagem += e.getMessage();
        }
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/escalas/listar/tipo";
    }
}
