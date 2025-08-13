package com.sistemabancario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sistemabancario.Model.Cliente;
import com.sistemabancario.Model.TransferenciaDTO;
import com.sistemabancario.Repository.ClienteRepository;

@Controller
@RequestMapping
public class TransacoesController {

    @Autowired
    private ClienteRepository cRepository;

    @GetMapping("/pagamentos")
    public String deposito(Model model) {
        model.addAttribute("transferenciaDTO", new TransferenciaDTO());
        return "pagamentos";
    }


@PostMapping("/depositocliente")
public String depositocliente(Cliente cliente, RedirectAttributes redirectAttrs) {
    Cliente clienteEncontrado = cRepository.findByCpf(cliente.getCpf());
    if (clienteEncontrado != null) {
        clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + cliente.getSaldo());
        cRepository.save(clienteEncontrado);

        redirectAttrs.addFlashAttribute("successMessage", "Depósito realizado com sucesso!");
        return "redirect:/pagamentos";
    }
    redirectAttrs.addFlashAttribute("errorMessage", "Erro: Cliente não encontrado.");
    return "redirect:/dashboard";
}

@PostMapping("/saquecliente")
public String saquecliente(Cliente cliente, RedirectAttributes redirectAttrs) {
    Cliente clienteEncontrado = cRepository.findByCpf(cliente.getCpf());
    if (clienteEncontrado != null) {
        if (clienteEncontrado.getSaldo() >= cliente.getSaldo()) {
            clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() - cliente.getSaldo());
            cRepository.save(clienteEncontrado);

            redirectAttrs.addFlashAttribute("successMessage", "Saque realizado com sucesso!");
            return "redirect:/pagamentos";
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", "Saldo insuficiente.");
            return "redirect:/dashboard";
        }
    }
    redirectAttrs.addFlashAttribute("errorMessage", "Cliente não encontrado.");
    return "redirect:/dashboard";
}

@PostMapping("/transferenciaContas")
public String transferenciaContas(@ModelAttribute TransferenciaDTO dto, RedirectAttributes redirectAttrs) {
    Cliente origem = cRepository.findByCpf(dto.getCpfOrigem());
    Cliente destino = cRepository.findByCpf(dto.getCpfDestino());

    if (origem != null && destino != null) {
        if (origem.getSaldo() >= dto.getValor()) {
            origem.setSaldo(origem.getSaldo() - dto.getValor());
            destino.setSaldo(destino.getSaldo() + dto.getValor());

            cRepository.save(origem);
            cRepository.save(destino);

            redirectAttrs.addFlashAttribute("successMessage", "Transferência realizada com sucesso!");
            return "redirect:/pagamentos";
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", "Saldo insuficiente na conta de origem.");
            return "redirect:/dashboard";
        }
    }
    redirectAttrs.addFlashAttribute("errorMessage", "Conta de origem ou destino não encontrada.");
    return "redirect:/dashboard";
}

}
