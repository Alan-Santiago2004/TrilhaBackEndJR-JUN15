# Trilha Inicial BackEnd Jr

A API de Tarefas com Autenticação de Usuários é projetada para gerenciar tarefas com funcionalidades completas de CRUD (Create, Read, Update, Delete), além de autenticação e controle de acesso com base em roles de usuários. Os usuários podem se registrar como USER ou ADMIN, onde cada um tem permissões específicas: usuários do tipo USER podem visualizar tarefas e consultar informações de outros usuários, enquanto usuários com a role ADMIN têm controle total sobre as tarefas, podendo criar, editar e excluir, além de consultar dados de usuários. A autenticação é feita via JWT, garantindo segurança no acesso às operações protegidas da API.

## Swagger
[Interface interativa com Swagger](https://trilhabackendjr-jun15-production-1e59.up.railway.app/swagger-ui/index.html#/)

Para registrar um usuário na API, envie uma requisição ``POST`` para o endpoint ``/auth/register`` com o seguinte corpo:
```    
    {
        "login": "String",
        "password": "String",
        "role": "USER" // ou "ADMIN"
    }
```
Após registrar, faça login enviando uma requisição ``POST`` para ``/auth/login`` com o corpo:
```    
    {
        "login": "String",
        "password": "String"
    }
```
A resposta retornará um token JWT que deve ser usado para autenticar as demais requisições.
```
    {
        "token": "..."
    }
```
O token deve ser copiado e inserido no campo **Authorize** no Swagger para assim permitir que as requisições nos endpoints tarefa e user sejam realizadas.

## Endpoints
- ``/auth/register`` - Registrar Usuário

    - Método: ``POST``
    - Descrição: Registra um novo usuário no sistema com a role USER ou ADMIN.
    - Acesso: Público (não requer autenticação).

- ``/auth/login`` - Autenticar Usuário

    - Método: ``POST``
    - Descrição: Realiza o login do usuário e retorna um token JWT para autenticação nas demais requisições.
    - Acesso: Público (não requer autenticação).

- ``/tarefa`` - Gerenciamento de Tarefas (CRUD)

    - Métodos:
        - ``GET``: Lista todas as tarefas (acesso para USER e ADMIN).
        - ``POST``: Cria uma nova tarefa (acesso apenas para ADMIN).
        - ``PUT``: Atualiza uma tarefa existente (acesso apenas para ADMIN).
        - ``DELETE``: Remove uma tarefa (acesso apenas para ADMIN).
    - Acesso: Requer autenticação com token JWT.

- ``/user`` - Informações de Usuários

    - Método: ``GET``
    - Descrição: Retorna informações dos usuários cadastrados.
    - Acesso: Disponível para USER e ADMIN. Requer autenticação com token JWT.

## Rodar Localmente

- **Java 21**: [Instalar JDK 21](https://www.oracle.com/br/java/technologies/downloads/)
- **Maven**: [Instalar Maven](https://maven.apache.org/install.html)

### Configuração do Ambiente

1. **Configurar Java 21**: Verifique se a JDK está devidamente instalado com o comando `java -version`.
2. **Configurar Maven**: Verifique se o Maven está devidamente instalado com o comando `mvn -version`.

### Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/Alan-Santiago2004/TrilhaBackEndJR_CodigoCerto.git

2. Instale as dependecias
    ```bash
    mvn clean install

3. execute a aplicação
    ```bash
    mvn spring-boot:run

## Tecnologias utilizadas
- Spring Framework
- SQLite
- Maven
- JWT (JSON Web Token)