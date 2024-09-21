# Trilha Inicial BackEnd Jr

API REST que gerencia tarefas, com funções de CRUD com autenticação de usuarios

## Requisitos

- **Java 21**: [Instalar JDK 21](https://www.oracle.com/br/java/technologies/downloads/)
- **Maven**: [Instalar Maven](https://maven.apache.org/install.html)

## Configuração do Ambiente

1. **Configurar Java 21**: Verifique se a JDK está devidamente instalado com o comando `java -version`.
2. **Configurar Maven**: Verifique se o Maven está devidamente instalado com o comando `mvn -version`.

## Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/Alan-Santiago2004/TrilhaBackEndJR_CodigoCerto.git

2. Instale as dependecias
    ```bash
    mvn clean install

3. execute a aplicação
    ```bash
    mvn spring-boot:run

# Desenvolvimento
### configuração inicial do projeto
Este projeto foi desenvolvido utilizando Java com o framework Spring Boot, que facilita a criação de APIs REST. O Spring Boot permite iniciar o desenvolvimento rapidamente, com uma configuração mínima, além de fornecer uma estrutura robusta e escalável.

Dependências Utilizadas
As dependências principais utilizadas no projeto foram adicionadas no arquivo `pom.xml` (para projetos Maven). Abaixo estão as dependências usadas:

- Spring Web: Fornece funcionalidades para criar e gerenciar a API REST.
- Spring Data JPA: Facilita a integração com bancos de dados relacionais, permitindo o uso do JPA para operações de persistência.
- H2 Database: Configura o uso do H2 Database, um banco de dados em memória, para simplificar os testes e o desenvolvimento local.
- Spring Security: Adiciona autenticação e controle de acesso à API, permitindo proteger os endpoints da aplicação.

### Configuração do banco de dados

Neste projeto, optei, a princípio, por utilizar o H2 Database, um banco de dados em memória, durante o desenvolvimento e para testes. A escolha do H2 oferece algumas vantagens iniciais:

- Simplicidade de Configuração: O H2 não requer instalação adicional e é integrado diretamente ao projeto.
- Execução em Memória: Ideal para desenvolvimento e testes, as informações são armazenadas temporariamente enquanto a aplicação está em execução.

O H2 facilita o teste da API sem complicações. No entanto, em um ambiente de produção, deve ser substituído por um banco de dados mais robusto, como PostgreSQL ou MySQL, para garantir a persistência dos dados.
As configurações do banco de dados se emcontram em  `src.resources.application.properties`.
