package com.foro.juniors.foro.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class Index {


    
    @GetMapping({"/index", "/"})
    public String inicio(Model model){
        model.addAttribute("titulo", "Index");
        return "index";
    }

}
