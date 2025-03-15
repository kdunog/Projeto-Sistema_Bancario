package com.sistemabancario.Model;

import java.util.Scanner;

import com.sistemabancario.dao.ClienteDAO;

public class SistemaBancario {

	private Cliente c = new Cliente(null, null, null, null, 0);
	private Scanner sc = new Scanner(System.in);

	private ClienteDAO cDAO = new ClienteDAO();

	public void iniciar() {

		int opcao = 0;
		while (opcao != 2 || opcao != 3) {
			exibirMenu();
			opcao = sc.nextInt();

			sc.nextLine();

			switch (opcao) {
			case 1 -> cadastrarCliente();
			case 2 -> {
				if (loginDoCliente()) {
					exibirOpçoesBancarias();
					return;
				}
			}
			case 3 -> {
				System.out.println("Saindo do sistema...");
				return;
			}
			default -> System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private boolean cadastrarCliente() {
		// Cadastrando Cliente
		System.out.print("Nome: ");
		c.setNome(sc.nextLine());

		System.out.print("CPF: ");
		c.setCPF(sc.nextLine());

		System.out.print("Email: ");
		c.setEmail(sc.nextLine());

		System.out.print("Senha: ");
		c.setSenha(sc.nextLine());

		// salva no banco de dados
		cDAO.salvarCliente(c);
		return true;
	}

	private boolean loginDoCliente() {
		// Login caso cliente ja tiver conta
		System.out.println("Digite seus dados: ");
		System.out.print("CPF: ");
		String cpfLogin = sc.next();
		System.out.print("Senha: ");
		String senhaLogin = sc.next();

		boolean clienteExistente = cDAO.autenticarCliente(cpfLogin, senhaLogin);

		if (clienteExistente) {
			System.out.println("Login bem-sucedido!");
			c.setSaldo(0);
			c.setCPF(cpfLogin);
			return true;

		} else {
			System.out.println("CPF ou senha inválidos");
			return false;
		}

	}

	private void exibirMenu() {
		// exibindo opcoes pro cliente
		System.out.println("\n===== SISTEMA BANCÁRIO =====");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Fazer Login");
		System.out.println("3 - Sair");
	}

	private void exibirSaldo() {
		System.out.println("Seu saldo Bancário é " + c.getSaldo());
		exibirOpçoesBancarias();
	}

	private void realizarDeposito() {
		System.out.println("Valor a depositar R$: ");
		double valor = sc.nextDouble();
		if (valor <= 0) {
			System.out.println("Valor inválido");
		} else {
			c.setSaldo(c.getSaldo() + valor);
			cDAO.atualizarSaldo(c.getCPF(), c.getSaldo());
			cDAO.registrarTransacao(c.getCPF(), "Depósito ", valor);
			System.out.println("Deposito Realizado com sucesso!");
			exibirOpçoesBancarias();
		}
	}

	private void realizarSaque() {
		System.out.println("Digite o valor do saque R$: ");
		double valor = sc.nextDouble();
		if (valor > c.getSaldo()) {
			System.out.println("Saldo insuficiente!");
		} else {
			c.setSaldo(c.getSaldo() - valor);
			cDAO.atualizarSaldo(c.getCPF(), c.getSaldo());
			cDAO.registrarTransacao(c.getCPF(), "Saque ", valor);
			System.out.println("Saque realizado com sucesso!");
			exibirOpçoesBancarias();
		}

	}

	private void exibirOpçoesBancarias() {
		// opcoes do cliente após o login
		System.out.println("\n===== Escolha o que deseja fazer =====");
		System.out.println("4 - Exibir saldo bancário");
		System.out.println("5 - Realizar depósito");
		System.out.println("6 - Realizar Saque");
		System.out.println("7 - Para sair");

		int opcao = 0;
		while (opcao != 7) {
			opcao = sc.nextInt();
			sc.nextLine();
			switch (opcao) {
			case 4 -> exibirSaldo();
			case 5 -> realizarDeposito();
			case 6 -> realizarSaque();
			case 7 -> {System.out.println("Saindo do sistema...");
			sc.close(); // Fechando o Scanner apenas aqui
	        System.exit(0); // Encerra todo o programa
			}
			default -> System.out.println("Opcão inválida, Tente Novamente");
			}
		}
	}

}
