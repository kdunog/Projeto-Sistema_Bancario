# 💳 Sistema Bancário Web

Este projeto é um sistema bancário desenvolvido em **Java** utilizando **Spring Boot**, **Thymeleaf** e **JPA/Hibernate**. O objetivo é simular operações bancárias como cadastro, login, depósitos, saques, transferências e exibição do histórico de transações de cada usuário.

## Funcionalidades

- Cadastro e login de clientes
- Depósito, saque e transferência entre contas
- Histórico de todas as transações (depósitos, saques, transferências)
- Controle de sessão: operações só podem ser feitas pelo usuário logado
- Mensagens de sucesso e erro para cada operação
- Interface web responsiva com HTML5, CSS3 e Thymeleaf

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Thymeleaf
- Banco de dados relacional (MySQL, H2, etc)
- HTML5, CSS3

## Como executar o projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/Projeto-Sistema_Bancario.git
   ```
2. **Configure o banco de dados em `application.properties`**
3. **Execute o projeto:**
   ```bash
   ./mvnw spring-boot:run
   ```
   ou
   ```bash
   mvn spring-boot:run
   ```
4. **Acesse no navegador:**
   ```
   http://localhost:8080
   ```

## Estrutura do Projeto

- `/src/main/java/com/sistemabancario/` - Código fonte Java
- `/src/main/resources/templates/` - Templates HTML (Thymeleaf)
- `/src/main/resources/application.properties` - Configurações do projeto

## Observações

- Apenas o cliente logado pode movimentar sua própria conta.
- Todas as operações são registradas e exibidas no histórico.
- O sistema impede operações com CPFs diferentes do logado.

## Autor

Carlos Eduardo

---

Sinta-se à vontade para contribuir ou sugerir
