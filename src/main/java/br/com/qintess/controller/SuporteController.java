package br.com.qintess.controller;

import br.com.qintess.entities.Suporte;
import br.com.qintess.services.interfaces.ISuporteService;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("suporte")
public class SuporteController {

    @Autowired
    private ISuporteService suporteService;

    @ResponseBody
    @GetMapping("/listar")
    public ModelAndView listar(@RequestParam(name = "rt") String rt,
                               @RequestParam(name = "st") String st,
                               ModelMap model) {
        // rt - Request type
        // st - Status type
        List<Suporte> chamados = null;
        if ((rt == null || rt.isEmpty() || rt == "T") && (st == null || st.isEmpty() || rt == "T"))
            chamados = this.suporteService.listar();

        model.addAttribute("chamados", chamados);
        return new ModelAndView("suporte/list", model);
    }

    @GetMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("suporte") Suporte suporte,
                            ModelMap model,
                            HttpServletRequest request) {
        model.addAttribute("userIp", request.getRemoteAddr());
        return "/suporte/add";
    }

    @GetMapping("/salvar")
    public String salvarRedirect(@ModelAttribute("suporte") Suporte suporte){
        return "redirect:/suporte/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("suporte") Suporte suporte,
                         BindingResult result,
                         RedirectAttributes attr,
                         ModelMap model,
                         HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("userIp", request.getRemoteAddr());
            return "/suporte/add";
        }

        this.suporteService.salvar(suporte);
        attr.addFlashAttribute("mensagem", "Solicitação inserida com sucesso: Logo retornaremos o contato.");
        return "redirect:/suporte/cadastrar";
    }
}
