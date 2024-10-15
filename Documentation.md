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

# Desenvolvimento
### configuração inicial do projeto
Este projeto foi desenvolvido utilizando Java com o framework Spring Boot, que facilita a criação de APIs REST. O Spring Boot permite iniciar o desenvolvimento rapidamente, com uma configuração mínima, além de fornecer uma estrutura robusta e escalável.

Dependências Utilizadas
As dependências principais utilizadas no projeto foram adicionadas no arquivo `pom.xml` (para projetos Maven). Abaixo estão as dependências usadas:

- Spring Web: Fornece funcionalidades para criar e gerenciar a API REST.
- Spring Data JPA: Facilita a integração com bancos de dados relacionais, permitindo o uso do JPA para operações de persistência.
- H2 Database: Configura o uso do H2 Database, um banco de dados em memória, para simplificar os testes e o desenvolvimento local.
- Spring Security: Adiciona autenticação e controle de acesso à API, permitindo proteger os endpoints da aplicação.
- JSON Web Token: Um token JWT (JSON Web Token) é um formato compactado e seguro de transmitir informações entre partes como um objeto JSON.

### Configuração do banco de dados

Neste projeto, optei, a princípio, por utilizar o H2 Database, um banco de dados em memória, durante o desenvolvimento e para testes. A escolha do H2 oferece algumas vantagens iniciais:

- Simplicidade de Configuração: O H2 não requer instalação adicional e é integrado diretamente ao projeto.
- Execução em Memória: Ideal para desenvolvimento e testes, as informações são armazenadas temporariamente enquanto a aplicação está em execução.

O H2 facilita o teste da API sem complicações. No entanto, em um ambiente de produção, deve ser substituído por um banco de dados mais robusto, como PostgreSQL ou MySQL, para garantir a persistência dos dados.
As configurações do banco de dados se emcontram em  `src.resources.application.properties`.

### Creiação da entidade Model

Essa entidade foi criada para facilitar as operações de CRUD no sistema de tarefas. O campo tarefaId é gerado automaticamente para garantir a unicidade das tarefas, e tarefaCorpo armazena a descrição da tarefa.
A TarefaModel representa uma tarefa no sistema e será mapeada para a tabela tb_tarefa no banco de dados.
- **@Entity**: Define que a classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
- **@Table(name = "tb_tarefa")**: Define explicitamente o nome da tabela no banco de dados como ``tb_tarefa``.
- **@Id e @GeneratedValue(strategy = GenerationType.AUTO)**: Marca o campo ``tarefaId`` como chave primária e indica que seu valor será gerado automaticamente.
- **tarefaId**: Identificador único do tipo ``UUID``, gerado automaticamente.
- **tarefaCorpo**: Um campo String que contém o conteúdo da tarefa.

### Interface TarefaRepository
A interface ``TarefaRepository`` é responsável pela conexão com o JPA (Java Persistence API) e facilita as operações de persistência da entidade TarefaModel. Ao estender a interface ``JpaRepository``, ela herda métodos prontos para realizar operações de CRUD, como salvar, buscar, atualizar e deletar tarefas.

### Tarefa Record DTO
O ``TarefaDTO`` (Data Transfer Object) é um record que serve para transferir dados entre a camada de controle (``Controller``) e outras partes da aplicação, ele encapsula as informações necessárias para representar uma tarefa, facilitando a comunicação entre os componentes.

### Tarefa Controller
O TarefaController é responsável por expor os endpoints da API e implementar as operações CRUD (Create, Read, Update e Delete) para gerenciar as tarefas. Os principais métodos HTTP foram utilizados para definir essas operações.

- **POST**: Utilizado no método ``criarTarefa()``, que recebe um TarefaDTO como parâmetro e salva a tarefa no banco de dados. Esse endpoint permite a criação de novas tarefas, utilizando o método HTTP POST.

- **GET**: Utilizado em dois métodos:

    - ``listarTarefas()``: Retorna uma lista de todas as tarefas cadastradas no banco de dados.
    - ``selecionarTarefa()``: Retorna uma tarefa específica, identificada pelo id, que é passado como parâmetro na URL do endpoint, caso não encontre nada, retornará um erro ``404 Not_Found``.

- **PUT**: O método ``atualizarTarefa()`` é responsável por atualizar uma tarefa existente. Ele recebe o id da tarefa a ser atualizada e um ``TarefaDTO`` com os novos dados, caso não encontre nada, retornará um erro ``404 Not_Found``. A atualização é feita utilizando o método HTTP PUT.

- **DELETE**: O método ``deletarTarefa()`` utiliza o método HTTP DELETE para excluir uma tarefa com base no id passado como parâmetro na URL, caso não encontre nada, retornará um erro ``404 Not_Found``. Ele localiza a tarefa pelo id e a remove do banco de dados.

# Autenticação
### Criação da entidade User
A entidade UserModel representa um usuário no sistema e implementa a interface ``UserDetails``, que é fundamental para a integração com o Spring Security. Os atributos desta entidade incluem:

- **id** (tipo ``String``, gerado automaticamente como ``UUID`` com as anotações ``@Id`` e ``@GeneratedValue``): Um identificador único para cada usuário.
- **login** (tipo ``String``): O nome de usuário, que é utilizado para autenticação.
- **password** (tipo ``String``): A senha do usuário, armazenada de forma segura.
- **role** (tipo ``RoleUser``, um enum): Representa o papel do usuário no sistema, definindo suas permissões.
  A classe implementa os métodos da interface ``UserDetails``, incluindo ``getAuthorities()``, que retorna uma lista de autoridades associadas ao usuário; ``getPassword()``, que retorna a senha; e ``getUsername()``, que retorna o nome do usuário. Além disso, os métodos ``isAccountNonExpired``, ``isAccountNonLocked``, ``isCredentialsNonExpired`` e ``isEnabled`` são implementados para retornar true, indicando que a conta do usuário está ativa e válida.

### Criação do UserRepository
O UserRepository é uma interface que estende o JpaRepository, permitindo a interação com o banco de dados de forma eficiente. Nesta interface, foi criado o método findByLogin(String login), que busca um usuário com base no nome de usuário fornecido. Esse método retorna um objeto do tipo UserDetails, permitindo que a autenticação e a autorização sejam realizadas facilmente pelo Spring Security.

### Criação AuthenticationService
O AuthenticationService é uma classe que implementa a interface UserDetailsService, que é crucial para o processo de autenticação no Spring Security. Esta classe contém o método loadUserByUsername(String username), que busca um usuário no banco de dados com base no nome de usuário fornecido. Se o usuário for encontrado, o método retorna um objeto do tipo UserDetails, que inclui as informações de autenticação necessárias. Caso contrário, uma exceção é lançada, garantindo que apenas usuários válidos possam ser autenticados no sistema.

### Pasta Security