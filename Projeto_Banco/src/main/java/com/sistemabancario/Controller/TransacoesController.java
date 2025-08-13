package com.sistemabancario.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sistemabancario.Model.Cliente;
import com.sistemabancario.Model.Transacoes;
import com.sistemabancario.Model.TransferenciaDTO;
import com.sistemabancario.Repository.ClienteRepository;
import com.sistemabancario.Repository.TransacoesRepository;

@Controller
@RequestMapping
public class TransacoesController {

    @Autowired
    private ClienteRepository cRepository;
    @Autowired
    private TransacoesRepository transacoesRepository;

   @GetMapping("/pagamentos")
public String deposito(Model model, @SessionAttribute(value = "cpf", required = false) String cpf) {
    if (cpf == null) {
        return "redirect:/logincliente";
    }
    model.addAttribute("transferenciaDTO", new TransferenciaDTO());
    List<Transacoes> historico = transacoesRepository.findByCpfOrigem(cpf);
    model.addAttribute("historicoTransacoes", historico);
    return "pagamentos";
}

@PostMapping("/depositocliente")
public String depositocliente(@SessionAttribute("cpf") String cpf, Cliente cliente, RedirectAttributes redirectAttrs) {
    Cliente clienteEncontrado = cRepository.findByCpf(cpf); // Usa o CPF da sessão!
    if (clienteEncontrado != null) {
        clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + cliente.getSaldo());
        cRepository.save(clienteEncontrado);

        Transacoes transacao = new Transacoes();
        transacao.setCpfOrigem(cpf); // CPF da sessão!
        transacao.setTipo_transacao("Depósito");
        transacao.setValor(cliente.getSaldo());
        transacoesRepository.save(transacao);

        redirectAttrs.addFlashAttribute("successMessage", "Depósito realizado com sucesso!");
        return "redirect:/dashboard";
    }
    redirectAttrs.addFlashAttribute("errorMessage", "Erro: Cliente não encontrado.");
    return "redirect:/dashboard";
}

@PostMapping("/saquecliente")
public String saquecliente(@SessionAttribute("cpf") String cpf, Cliente cliente, RedirectAttributes redirectAttrs) {
    Cliente clienteEncontrado = cRepository.findByCpf(cpf);
    if (clienteEncontrado != null) {
        if (clienteEncontrado.getSaldo() >= cliente.getSaldo()) {
            clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() - cliente.getSaldo());
            cRepository.save(clienteEncontrado);

            Transacoes transacao = new Transacoes();
            transacao.setCpfOrigem(cpf);
            transacao.setTipo_transacao("Saque");
            transacao.setValor(cliente.getSaldo());
            transacoesRepository.save(transacao);

            redirectAttrs.addFlashAttribute("successMessage", "Saque realizado com sucesso!");
            return "redirect:/dashboard";
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", "Saldo insuficiente.");
            return "redirect:/dashboard";
        }
    }
    redirectAttrs.addFlashAttribute("errorMessage", "Cliente não encontrado.");
    return "redirect:/dashboard";
}

@PostMapping("/transferenciaContas")
public String transferenciaContas(@SessionAttribute("cpf") String cpf, @ModelAttribute TransferenciaDTO dto, RedirectAttributes redirectAttrs) {
    Cliente origem = cRepository.findByCpf(cpf); // CPF da sessão!
    Cliente destino = cRepository.findByCpf(dto.getCpfDestino());

    if (origem != null && destino != null) {
        if (origem.getSaldo() >= dto.getValor()) {
            origem.setSaldo(origem.getSaldo() - dto.getValor());
            destino.setSaldo(destino.getSaldo() + dto.getValor());

            cRepository.save(origem);
            cRepository.save(destino);

            Transacoes transacao = new Transacoes();
            transacao.setCpfOrigem(cpf); // CPF da sessão!
            transacao.setTipo_transacao("Transferência");
            transacao.setValor(dto.getValor());
            transacoesRepository.save(transacao);

            redirectAttrs.addFlashAttribute("successMessage", "Transferência realizada com sucesso!");
            return "redirect:/dashboard";
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", "Saldo insuficiente na conta de origem.");
            return "redirect:/dashboard";
        }
    }
    redirectAttrs.addFlashAttribute("errorMessage", "Conta de origem ou destino não encontrada.");
    return "redirect:/dashboard";
}

}
