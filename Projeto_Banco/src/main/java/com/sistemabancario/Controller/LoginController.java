package com.sistemabancario.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/homesite")
    public String iniciosite(){
        return "home";
    }
    @GetMapping("/cadastrocliente")
    public String cadastro(){
        return "cadastro";
    }

}
