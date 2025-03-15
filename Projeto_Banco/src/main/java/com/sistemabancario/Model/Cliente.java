package com.sistemabancario.Model;

public class Cliente {

		private String nome;
		private String CPF;
		private String email;
		private String senha;
		private double saldo;
		
		public Cliente(String nome, String cpf, String email, String senha, double saldo) {
	        this.nome = nome;
	        this.CPF = CPF;
	        this.email = email;
	        this.senha = senha;
	        this.saldo = saldo;
	    }
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCPF() {
			return CPF;
		}
		public void setCPF(String cPF) {
			CPF = cPF;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}

		public double getSaldo() {
			return saldo;
		}

		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}
		
		
				
		}
	
		
	


