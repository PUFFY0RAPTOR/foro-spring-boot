package com.foro.juniors.foro.Controllers;

import com.foro.juniors.foro.Models.Service.*;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("reportes")
public class Reporte {
    
    //Generar reporte dónde se vea: El usuario, la publicación (con su tema) de ese usuario, y los comentarios.

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPublicacionService publicacionService;

    @Autowired
    private IComentarioService comentarioService;

    @Autowired
    private IRespuestaService respuestaService;


    @GetMapping("/generar")
    public String generarReporte(Model model){
        
        //Reciba a un usuario con su id, una vez hecho eso, que lo encuentre y, cuando lo encuentre
        //que me traiga todo lo que pedí anteriormente
    
        model.addAttribute("titulo", "Reportes de usuarios");
        model.addAttribute("usuario", usuarioService.findAll());
        model.addAttribute("publicacion", publicacionService.findAll());
        model.addAttribute("comentario", comentarioService.findAll());
        model.addAttribute("respuesta", respuestaService.findAll());
        
        return "reportes";
    }

    @RequestMapping(path = "/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TemplateEngine templateEngine = new TemplateEngine();

        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("usuario", usuarioService.findAll());
        context.setVariable("publicacion", publicacionService.findAll());
        context.setVariable("comentario", comentarioService.findAll());
        context.setVariable("respuesta", respuestaService.findAll());
        String contentHtml = templateEngine.process("reportes", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        HtmlConverter.convertToPdf(contentHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

}
