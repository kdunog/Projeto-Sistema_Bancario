package com.sistemabancario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemabancario.Model.Cliente;
import com.sistemabancario.Repository.ClienteRepository;


// CODIGO NAO ESTA FUNCIONANDO QUANDO TENTO DEPOSITAR SACAR E TRANSFERIR ELE CAI EM 404 WHITELABEL RESOLVER TALVEZ SEJA METODO GET EM DEPOSITO SAQUE E TRANSFERENCIA

@Controller
@RequestMapping
public class TransacoesController {

    @Autowired
    private ClienteRepository cRepository;

    @GetMapping("/pagamentos")
    public String deposito() {
        return "pagamentos";
    }

    @PostMapping("/depositocliente")
    public String depositocliente(Cliente cliente) {
        Cliente clienteEncontrado = cRepository.findByCpf(cliente.getCpf());
        if (clienteEncontrado != null) {
            clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + cliente.getSaldo()); // Corrigido
            cRepository.save(clienteEncontrado);
            return "redirect:/pagamentos";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/saquecliente")
    public String saquecliente(Cliente cliente) {
        Cliente clienteEncontrado = cRepository.findByCpf(cliente.getCpf());
        if (clienteEncontrado != null) {
            if (clienteEncontrado.getSaldo() >= cliente.getSaldo()) {
                clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() - cliente.getSaldo());
                cRepository.save(clienteEncontrado);
                return "redirect:/pagamentos";
            } else {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/transferenciaContas")
    public String transferenciaContas(Cliente clienteOrigem, Cliente clienteDestino) {
        Cliente origem = cRepository.findByCpf(clienteOrigem.getCpf());
        Cliente destino = cRepository.findByCpf(clienteDestino.getCpf());

        if (origem != null && destino != null) {
            if (origem.getSaldo() >= clienteOrigem.getSaldo()) { // Corrigido
                origem.setSaldo(origem.getSaldo() - clienteOrigem.getSaldo()); // reduz da conta origem
                destino.setSaldo(destino.getSaldo() + clienteDestino.getSaldo()); // adiciona na conta destino
                cRepository.save(origem); // Corrigido para salvar a conta de origem
                cRepository.save(destino); // Corrigido para salvar a conta de destino
                return "redirect:/pagamentos";
            } else {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/pagamentos") 
    public String pagamentos(Cliente cliente, Model model) {
        model.addAttribute("cliente", cliente); // Adicionado para passar o cliente ao modelo
        return "pagamentos";
    }
}
