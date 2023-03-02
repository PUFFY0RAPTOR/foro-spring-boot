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

import com.foro.juniors.foro.Models.Entity.Tema;
import com.foro.juniors.foro.Models.Service.ITemaService;

@Controller
@RequestMapping("temas")
public class TemaController {
    
    @Autowired
    private ITemaService temaService;

    @GetMapping("/listar")
    public String listar(Model model){
        
        model.addAttribute("titulo", "Listado de Temas");
        model.addAttribute("datos", temaService.findAll());

        return "temas/listar";
    }


    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        
        Tema tema = new Tema();

        model.put("tema", tema);
        model.put("titulo", "Registro de Temas");
        
        return "temas/crear";
    }


    @PostMapping("/guardar")
    public String guardar(@Valid Tema tema, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Temas");
            return "temas/crear";
        }
        temaService.save(tema);
        return "redirect:/temas/listar";
    }

    @GetMapping("/editar/{Id}")
    public String editar(@PathVariable Long Id, Map<String, Object> model) {

        Tema tema = null;

        if (Id > 0) {
            tema = temaService.findOne(Id);
        } else {
            return "redirect:/publicaciones/listar";
        }
        
        model.put("tema", tema);
        model.put("titulo", "Editar Tema");

        return "temas/crear";
    }

    @GetMapping("/eliminar/{Id}")
    public String eliminar(@PathVariable Long Id) {

        if (Id > 0){
            temaService.delete(Id);
        }
        return "redirect:/temas/listar";
    }
}
