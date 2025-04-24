
# 💳 Projeto Banco em Java

Este é um projeto de **sistema bancário básico** desenvolvido em **Java puro com JDBC e MySQL**, permitindo cadastro de clientes, login, depósito, saque e exibição de saldo.


## 🚀 Funcionalidades

- Cadastro de clientes com nome, CPF, email e senha.
- Login usando CPF e senha.
- Depósito com atualização no banco de dados.
- Saque com validação de saldo.
- Exibição de saldo.
- Registros de transações no banco de dados.
- Interface simples via console (terminal).

## 🧠 Tecnologias utilizadas

- **Java 17+**
- **JDBC (Java Database Connectivity)**
- **MySQL**
- **Maven ou Spring Boot (opcional)**

## 🛠️ Requisitos para rodar

- MySQL instalado
- Java JDK 17 ou superior
- IDE como Eclipse ou IntelliJ
- Banco de dados criado com as seguintes tabelas:

```sql
CREATE DATABASE sistemabancario;

USE sistemabancario;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(100),
    senha VARCHAR(100),
    saldo DOUBLE DEFAULT 0
);

CREATE TABLE transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf_origem VARCHAR(14),
    tipo_transacao VARCHAR(50),
    valor DOUBLE
);
```

## ⚙️ Configuração do Banco

Edite o arquivo `conexao.java` com seus dados:

```java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/sistemabancario", "root", "sua_senha");
```

## ▶️ Como rodar

1. Compile o projeto
2. Execute `Banco.java` ou `ProjetoBancoApplication.java` (caso esteja usando Spring Boot)
3. Siga as instruções no terminal

## 📌 Observações

- O sistema não salva data ou tipo de transação, conforme escopo definido.
- O saldo é persistido no banco e carregado ao logar.
