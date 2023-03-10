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

import com.foro.juniors.foro.Models.Entity.Comentario;
import com.foro.juniors.foro.Models.Service.IComentarioService;
import com.foro.juniors.foro.Models.Service.IPublicacionService;
import com.foro.juniors.foro.Models.Service.IUsuarioService;

@Controller
@RequestMapping("comentarios")
public class ComentarioCont {
    
    @Autowired
    private IComentarioService comentarioService;

    @Autowired 
    private IUsuarioService usuarioService;

    @Autowired
    private IPublicacionService publicacionService;


    @GetMapping("/listar") //Método para mostrar la lista de los comentarios existentes
    public String listar(Model model){
        
        model.addAttribute("titulo", "Listado de Comentarios");
        model.addAttribute("datos", comentarioService.findAll());

        return "comentarios/listarComentario";
    }


    @GetMapping("/form") //Método para acceder mostrar los datos de la Entity en un formulario.
    public String crear(Map<String, Object> model){
        
        Comentario comentario = new Comentario();

        model.put("datosUser", usuarioService.findAll());
        model.put("datosPub", publicacionService.findAll());
        model.put("comentario", comentario);
        model.put("titulo", "Registro de comentario");
        
        return "comentarios/crearComentario";
    }


    @PostMapping("/guardar") //Método para guardar los comentarios que se agrueguen
    public String guardar(@Valid Comentario comentario, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Comentarios");
            return "comentarios/crearComentario";
        }
        comentarioService.save(comentario);
        return "redirect:/comentarios/listar";
    }

    @GetMapping("/editar/{Id}") //Método para editar los comentarios
    public String editar(@PathVariable Long Id, Map<String, Object> model) {

        Comentario comentario = null;

        if (Id > 0) {
            comentario = comentarioService.findOne(Id);
        } else {
            return "redirect:/comentarios/listar";
        }

        model.put("datosUser", usuarioService.findAll());
        model.put("datosPub", publicacionService.findAll());
        model.put("comentario", comentario);
        model.put("titulo", "Editar un Comentario");

        return "comentarios/crearComentario";
    }

    @GetMapping("/eliminar/{Id}") //Método para eliminar los registros de los comentarios
    public String eliminar(@PathVariable Long Id) {

        if (Id > 0){
            comentarioService.delete(Id);
        }
        return "redirect:/comentarios/listar";
    }

}   
