package com.sistemabancario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sistemabancario.Model.Cliente;

public class ClienteDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sistemabancario";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	// salva oq for digitado no banco de dados
	public void salvarCliente(Cliente cliente) {
		String sql = "INSERT INTO clientes (nome, cpf, email, senha, saldo) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCPF());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSenha());
			stmt.setDouble(5, cliente.getSaldo());

			stmt.executeUpdate();
			System.out.println("Cliente cadastrado com sucesso!\n");

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
		}

	}

	//
	public boolean autenticarCliente(String CPF, String Senha) {
		String sql = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";

		;
		try (Connection conn = conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Substitui os placeholders pelos dados do login
			stmt.setString(1, CPF);
			stmt.setString(2, Senha);

			// Fazendo a consulta
			ResultSet rs = stmt.executeQuery();

			// Se encontrar um cliente com esse CPF e senha, retorna true
			return rs.next();

		} catch (SQLException e) {
			System.out.println("Erro ao autenticar cliente: " + e.getMessage());
			return false;
		}
	}

	public void atualizarSaldo(String cpf, double novoSaldo) {
		String sql = "UPDATE clientes SET saldo = ? WHERE cpf = ?";

		try (Connection conn = conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDouble(1, novoSaldo);
			stmt.setString(2, cpf);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void registrarTransacao(String cpfOrigem, String tipo, double valor) {
		String sql = "INSERT INTO transacoes (cpf_origem, tipo_transacao, valor) VALUES (?, ?, ?)";

		try (Connection conn = conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cpfOrigem);
			stmt.setString(2, tipo);
			stmt.setDouble(3, valor);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
