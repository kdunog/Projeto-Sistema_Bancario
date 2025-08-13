package com.sistemabancario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistemabancario.Model.Cliente;
import com.sistemabancario.Model.Transacoes;
import com.sistemabancario.Repository.ClienteRepository;
import com.sistemabancario.Repository.TransacoesRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private ClienteRepository cRepository;
    
    @Autowired
    private TransacoesRepository tRepository;

    @GetMapping("/")
    public String iniciosite(){
        return "home";
    }

    @GetMapping("/cadastrocliente")
    public String cadastro(){
        return "cadastro";
    }

    @GetMapping("/logincliente")
    public String login(){
        return "login";
    } 

    @GetMapping("/dashboard")
    public String dashboard(@SessionAttribute(value = "cpf", required = false) String cpf, Model model) {
        if (cpf == null) {
         return "redirect:/logincliente";
        }
        List<Transacoes> transacoes = tRepository.findByCpfOrigem(cpf);
        model.addAttribute("historicoTransacoes", transacoes);
        return "dashboard";
}


    @PostMapping("/cadastrocliente")
    public String cadastrarCliente(Cliente cliente, Model model){

        if(cliente.getCpf() == null || cliente.getCpf().isEmpty()){
            model.addAttribute("erro", "O Cpf nao pode ser vazio");
            return "cadastro";
        }

        if(cRepository.findByCpf(cliente.getCpf()) != null){
            model.addAttribute("error", "CPF ja cadastrado");
        }
        else{

            cRepository.save(cliente);
            model.addAttribute("sucesso", "Você foi cadastrado com sucesso!");
            return "login";
        }

        return "cadastro";
    }

    @PostMapping("/logincliente")
    public String logarCliente(Cliente cliente, Model model, HttpSession session, RedirectAttributes redirectAttrs){
        Cliente c = cRepository.findByCpf(cliente.getCpf());
        if(c != null && cliente.getSenha() != null &&  cliente.getSenha().equals(c.getSenha())){
            session.setAttribute("cpf", c.getCpf()); 
            return "redirect:/dashboard";
        }
        else{
            model.addAttribute("erro", "Login invalido");
            return "login";
        }
        
    }
}
