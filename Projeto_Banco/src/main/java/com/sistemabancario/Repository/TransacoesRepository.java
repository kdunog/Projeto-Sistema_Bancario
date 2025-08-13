package com.sistemabancario.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemabancario.Model.Transacoes;

public interface TransacoesRepository  extends JpaRepository<Transacoes, Long> {
    List<Transacoes> findByCpfOrigem(String cpfOrigem);

}
