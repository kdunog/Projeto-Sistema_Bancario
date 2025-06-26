package com.sistemabancario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistemabancario.Model.Cliente;
import com.sistemabancario.Repository.ClienteRepository;


//NECESSARIO IMPLEMENTACAO
@Controller
public class TransacoesController {

 @Autowired
 public ClienteRepository cRepository;


 @GetMapping("transferencia")
 public String deposito(){
    
    
    return "transferencia";
 }
    @PostMapping("/transferencia") 
    public String transferencia(Cliente cliente, Model model){
        
        return null;
        
    }
    
 }
