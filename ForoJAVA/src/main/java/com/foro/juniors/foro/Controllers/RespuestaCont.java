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

import com.foro.juniors.foro.Models.Entity.Respuesta;
import com.foro.juniors.foro.Models.Service.IComentarioService;
import com.foro.juniors.foro.Models.Service.IRespuestaService;
import com.foro.juniors.foro.Models.Service.IUsuarioService;

@Controller
@RequestMapping("respuestas")
public class RespuestaCont {
    
    @Autowired
    private IRespuestaService respuestaService;

    @Autowired
    private IComentarioService comentarioService;

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping("/listar")
    public String listar(Model model){
        
        model.addAttribute("titulo", "Listado de Respuestas");
        model.addAttribute("datos", respuestaService.findAll());

        return "respuestas/listarRespuesta";
    }


    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        
        Respuesta res = new Respuesta();

        model.put("datosUser", usuarioService.findAll());
        model.put("datosCom", comentarioService.findAll());
        model.put("respuesta", res);
        model.put("titulo", "Registro de Respuestas");
        
        return "respuestas/crearRespuesta";
    }


    @PostMapping("/guardar")
    public String guardar(@Valid Respuesta res, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Respuestas");
            return "respuestas/crearRespuesta";
        }
        respuestaService.save(res);
        return "redirect:/respuestas/listar";
    }

    @GetMapping("/editar/{Id}")
    public String editar(@PathVariable Long Id, Map<String, Object> model) {

        Respuesta res = null;

        if (Id > 0) {
            res = respuestaService.findOne(Id);
        } else {
            return "redirect:/respuestas/listar";
        }

        model.put("datosUser", usuarioService.findAll());
        model.put("datosCom", comentarioService.findAll());
        model.put("respuesta", res);
        model.put("titulo", "Editar respuesta");

        return "respuestas/crearRespuesta";
    }

    @GetMapping("/eliminar/{Id}")
    public String eliminar(@PathVariable Long Id) {

        if (Id > 0){
            respuestaService.delete(Id);
        }
        return "redirect:/respuestas/listar";
    }

}   
