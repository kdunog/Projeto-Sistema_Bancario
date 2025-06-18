package com.sistemabancario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemabancario.Model.Cliente;
import com.sistemabancario.Repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private ClienteRepository cRepository;

    @GetMapping("/logincliente")
    public String login(){
        return "login";
    }

    @GetMapping("/homesite")
    public String iniciosite(){
        return "home";
    }
    @GetMapping("/cadastrocliente")
    public String cadastro(){
        return "cadastro";
    }

    @PostMapping("/cadastrocliente")
    public String cadastrarCliente(Cliente cliente, Model model){
        if(cRepository.findByCPF(cliente.getCPF()) != null){
            model.addAttribute("error", "CPF ja cadastrado");
        }
        else{

            cRepository.save(cliente);
            model.addAttribute("sucesso", "Você foi cadastrado com sucesso!");
        }
        // ESTA CADASTRANDO MAS N INDO PRO LOGIN
        // NA VERDADE AQUI PRECISA RETORNAR PARA O LOGIN, CASO FOI CADASTRADO VAI CAIR NA PAGINA DE LOGIN, PRECISA SER REDIRECIONADO PARA LA
        return "cadastro";
    }

    // APOS O CLIQUE NO LOGIN DEVE DIRECIONAR PRO DASHBOARD OQ N TA ACONTECENDO NECESSARIO CORREÇÃO
    @PostMapping("/login")
    public String logarCliente(Cliente cliente, Model model, HttpSession session){
        Cliente c = cRepository.findByCPF(cliente.getCPF());
        if(c != null && cliente.getSenha().equals(c.getSenha())){
            session.setAttribute("Cliente logado", c);
            return "redirect:/dashboard";
        }
        else{
            model.addAttribute("erro", "Login invalido");
            return "login";
        }
        
    }
}
