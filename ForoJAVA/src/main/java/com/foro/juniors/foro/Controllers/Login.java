package com.foro.juniors.foro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.foro.juniors.foro.Models.Entity.Usuario;
import com.foro.juniors.foro.Models.Service.IUsuarioService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Login {

    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("/login")
    public String loginForm(Model model){

        model.addAttribute("titulo", "Bienvenido al login");
        return "login";
    }

    @GetMapping("loginVal/{usuario}/{password}")
    public String validarLogin(@PathVariable String usuario, @PathVariable String password, Model model){

        model.addAttribute("datos", "Usuario: "+usuario +" y contraseña: "+password);
        System.out.println("Usuario: "+usuario +" y contraseña: "+password);
        return "redirect:/login";
    }

    @GetMapping("/validarLogin")
    public String loginValidacion(@RequestParam(name="usuario", defaultValue = "NombreDeUsuario") String usuario,
                                  @RequestParam(name="password",
                                          defaultValue = "Contraseña") String password, Model model)
    {
        //System.out.println("Usuario: "+usuario +" y contraseña: "+password);
        //System.out.println(usuarioService.findAllLogin("Respuesta de la consulta: "+usuario, password));
        return "redirect:/index";
    }

}
