package com.sistemabancario.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transacoes")
public class Transacoes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpfOrigem;
    private String tipo_transacao;
    private double valor;

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCpfOrigem() {
        return cpfOrigem;
    }
    public void setCpfOrigem(String cpfOrigem) {
        this.cpfOrigem = cpfOrigem;
    }
    public String getTipo_transacao() {
        return tipo_transacao;
    }
    public void setTipo_transacao(String tipo_transacao) {
        this.tipo_transacao = tipo_transacao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    

}
