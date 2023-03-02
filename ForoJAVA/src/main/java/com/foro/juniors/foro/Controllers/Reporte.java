package com.foro.juniors.foro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foro.juniors.foro.Models.Service.IComentarioService;
import com.foro.juniors.foro.Models.Service.IPublicacionService;
import com.foro.juniors.foro.Models.Service.ITemaService;
import com.foro.juniors.foro.Models.Service.IUsuarioService;

@Controller
@RequestMapping("reportes")
public class Reporte {
    
    //Generar reporte dónde se vea: El usuario, la publicación (con su tema) de ese usuario, y los comentarios.

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPublicacionService publicacionService;

    @Autowired
    private ITemaService temaService;

    @Autowired
    private IComentarioService comentarioService;


    @GetMapping("/generar")
    public String generarReporte(Model model){
        
        //Reciba a un usuario con su id, una vez hecho eso, que lo encuentre y, cuando lo encuentre
        //que me traiga todo lo que pedí anteriormente
    
        model.addAttribute("titulo", "Reportes de usuarios");
        model.addAttribute("usuario", usuarioService.findAll());
        model.addAttribute("publicacion", publicacionService.findAll());
        
        return "reportes";
    }

}
