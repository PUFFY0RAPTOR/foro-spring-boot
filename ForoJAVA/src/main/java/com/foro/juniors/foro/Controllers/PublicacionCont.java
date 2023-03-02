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

import com.foro.juniors.foro.Models.Entity.Publicacion;
//import com.foro.juniors.foro.Models.Entity.Usuario;
import com.foro.juniors.foro.Models.Service.IPublicacionService;
import com.foro.juniors.foro.Models.Service.ITemaService;
import com.foro.juniors.foro.Models.Service.IUsuarioService;

@Controller
@RequestMapping("publicaciones")
public class PublicacionCont {
    
    @Autowired
    private IPublicacionService publicacionService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ITemaService temaService;

    @GetMapping("/listar")
    public String listar(Model model){
        
        model.addAttribute("titulo", "Listado de publicaciones");
        model.addAttribute("datos", publicacionService.findAll());

        return "publicaciones/listarPublicacion";
    }


    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        
        Publicacion publicacion = new Publicacion();

        model.put("publicacion", publicacion);
        model.put("titulo", "Registro de publicaciones");
        model.put("datosUser", usuarioService.findAll());
        model.put("datosTema", temaService.findAll());
        //model.put("usuario", "Usuarios");
        
        return "publicaciones/crearPublicacion";
    }


    @PostMapping("/guardar")
    public String guardar(@Valid Publicacion publicacion, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Publicaciones");
            return "publicaciones/crearPublicacion";
        }
        publicacionService.save(publicacion);
        return "redirect:/publicaciones/listar";
    }

    @GetMapping("/editar/{Id}")
    public String editar(@PathVariable Long Id, Map<String, Object> model) {

        Publicacion publicacion = null;

        if (Id > 0) {
            publicacion = publicacionService.findOne(Id);
        } else {
            return "redirect:/publicaciones/listar";
        }
        
        model.put("datosUser", usuarioService.findAll());
        model.put("datosTema", temaService.findAll());
        model.put("publicacion", publicacion);
        model.put("titulo", "Editar Publicacion");

        return "publicaciones/crearPublicacion";
    }

    @GetMapping("/eliminar/{Id}")
    public String eliminar(@PathVariable Long Id) {

        if (Id > 0){
            publicacionService.delete(Id);
        }
        return "redirect:/publicaciones/listar";
    }

}   
