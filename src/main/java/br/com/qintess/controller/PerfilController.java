package br.com.qintess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("perfil")
public class PerfilController {

//    @Autowired
//    private IPerfilService perfilService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
//        List<Perfil> perfis = perfilService.listar();

//        model.addAttribute("perfis", perfis);
        return new ModelAndView("/usuario/list", model);
    }
}
