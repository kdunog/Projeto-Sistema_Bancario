package com.sistemabancario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    public static Connection getConnection() throws SQLException {
        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Estabelecer a conexão com o banco de dados
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistemabancario", "root", ""); // Ajuste o usuário e senha conforme necessário
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }
}
