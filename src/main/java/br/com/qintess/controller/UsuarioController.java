package br.com.qintess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    //    @Autowired
    //    private IUsuarioService usuarioService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
//        List<Usuario> usuarios = usuarioService.listar();

//        model.addAttribute("usuarios", usuarios);
        return new ModelAndView("/usuario/list", model);
    }

    @GetMapping("/listar/{id}")
    public ModelAndView listarPorId(@PathVariable("id") long id, ModelMap model) {
//        Usuario usuario = usuarioService.listarPorId(id);

//        model.addAttribute("usuario", usuario);
        return new ModelAndView("/usuario/view", model);
    }
}
