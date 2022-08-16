package br.com.qintess.controller;

import br.com.qintess.entities.Suporte;
import br.com.qintess.services.interfaces.ISuporteService;
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

import javax.validation.Valid;

@Controller
@RequestMapping("suporte")
public class SuporteController {

    @Autowired
    private ISuporteService suporteService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {

        model.addAttribute("mensagens",this.suporteService.listar());
        return new ModelAndView("suporte/list", model);

    }

    @GetMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("suporte") Suporte suporte) {
        return "/suporte/add";
    }

    @GetMapping("/salvar")
    public String salvarRedirect(@ModelAttribute("suporte") Suporte suporte){
        return "redirect:/suporte/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("suporte") Suporte suporte,
                         BindingResult result,
                         RedirectAttributes attr) {
        if (result.hasErrors())
            return "/suporte/add";

        this.suporteService.salvar(suporte);
        attr.addFlashAttribute("mensagem", "Solicitação inserida com sucesso: Logo retornaremos o contato.");
        return "redirect:/suporte/cadastrar";
    }
}
