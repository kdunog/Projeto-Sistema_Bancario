package com.sistemabancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemabancario.Model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    Cliente findByCpf(String cpf);
    

}
