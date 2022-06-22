package br.com.qintess.controller;

import br.com.qintess.entities.Equipe;
import br.com.qintess.services.interfaces.IEquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("equipes")
public class EquipeController {

    @Autowired
    private IEquipeService equipeService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("equipes", equipeService.listar());
        return new ModelAndView("/equipe/list", model);
    }

    @GetMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("equipe") Equipe equipe) {
        return "/equipe/add";
    }

    @GetMapping("/salvar")
    public String salvarRedirect(@ModelAttribute("equipe") Equipe equipe){
      return "redirect:/equipes/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("equipe") Equipe equipe, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "/equipe/add";
        }

        equipeService.salvar(equipe);
        attr.addFlashAttribute("mensagem", "Equipe criada com sucesso.");
        return "redirect:/equipes/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizar(@PathVariable("id") long id, ModelMap model) {
        Equipe equipe = equipeService.listarPorId(id);
        model.addAttribute("equipe", equipe);
        return new ModelAndView("/equipe/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView salvarAtualizacao(@Valid @ModelAttribute("equipe") Equipe equipe, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return new ModelAndView("/equipe/add");
        }

        equipeService.atualizar(equipe);
        attr.addFlashAttribute("mensagem", "Equipe atualizada com sucesso.");
        return new ModelAndView("redirect:/equipes/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        equipeService.excluir(id);
        attr.addFlashAttribute("mensagem", "Equipe excluída com sucesso.");
        return "redirect:/equipes/listar";
    }
}
