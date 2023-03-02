package com.foro.juniors.foro.Controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foro.juniors.foro.Models.Entity.Usuario;
import com.foro.juniors.foro.Models.Service.IUsuarioService;

@Controller
@RequestMapping("usuarios")
public class UsuarioCont {
    
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/listar")
    public String listar(Model model){
        
        model.addAttribute("titulo", "Listado de Usuarios");
        model.addAttribute("datos", usuarioService.findAll());

        return "usuarios/listarUsuario";
    }


    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        
        Usuario usuario = new Usuario();

        model.put("usuario", usuario);
        model.put("titulo", "Registro de usuario");
        
        return "usuarios/crearUsuario";
    }


    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Usuarios");
            return "usuarios/crearUsuario";
        }
        usuarioService.save(usuario);
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/editar/{Id}")
    public String editar(@PathVariable Long Id, Map<String, Object> model) {

        Usuario usuario = null;

        if (Id > 0) {
            usuario = usuarioService.findOne(Id);
        } else {
            return "redirect:/usuarios/listar";
        }

        model.put("usuario", usuario);
        model.put("titulo", "Editar Usuario");

        return "usuarios/crearUsuario";
    }

    @GetMapping("/eliminar/{Id}")
    public String eliminar(@PathVariable Long Id) {

        if (Id > 0){
            usuarioService.delete(Id);
        }
        return "redirect:/usuarios/listar";
    }

}   
