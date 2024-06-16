# AcademicShare API

> API para gerenciamento de publicações de estudantes universitários.

## Sumário
- [Sobre](#sobre)
- [Tecnologias](#tecnologias)
- [Instalação](#instalação)
- [Endpoints](#endpoints)

## Sobre

A API AcademicShare é uma aplicação que tem como objetivo gerenciar publicações de estudantes universitários. A aplicação permite que os usuários possam criar, editar, visualizar e deletar publicações. Além disso, a aplicação permite que os usuários possam visualizar publicações de outros usuários.

Também há um [repositório](https://github.com/VictorMS-200/AcademicShare.Front-end) com o código do front-end da aplicação.

Essa aplicação foi desenvolvida como projeto final da disciplina de [Usabilidade, desenvolvimento web, mobile e jogos] da Universidade Una de Uberlândia.

## Tecnologias

A API foi desenvolvida utilizando as seguintes tecnologias:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [MySQL](https://www.mysql.com/)

## Instalação

Para instalar a aplicação é necessário ter o [Maven](https://maven.apache.org/) e o [Java 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) instalados na máquina.

### Clonar o repositório

```bash
git clone
```

### Configurar o banco de dados

Para configurar o banco de dados é necessário criar um banco de dados MySQL e configurar as credenciais no arquivo `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=usuario
spring.datasource.password=senha
```

### Executar a aplicação

Para executar a aplicação é necessário executar o comando abaixo.

```bash
mvn spring-boot:run
```

## Endpoints
Abaixo estão listados os endpoints disponíveis na API. Para cada endpoint é possível realizar as operações de GET, GET{id}, POST, PUT e DELETE.
Sendo que o GET{id} é utilizado para buscar um registro específico, o POST para criar um novo registro, o PUT para atualizar um registro e o DELETE para deletar um registro.


### /api/v1/tipocursos
Essa rota é responsável por gerenciar os tipos de cursos disponíveis na aplicação.

#### Request
Abaixo está o modelo de requisição para a rota de tipos de cursos.

```json
{
    "idTipoCurso": "UUID",
    "nome": "string"
}
```

###  /api/v1/cursos
Essa rota é responsável por gerenciar os cursos disponíveis na aplicação.

#### Request
Abaixo está o modelo de requisição para a rota de cursos.

```json
{
    "idCurso": "UUID",
    "nome": "string",
    "idTipoCurso": "UUID",
    "idFaculdade": "UUID"
}
```

### /api/v1/faculdades
Essa rota é responsável por gerenciar as faculdades disponíveis na aplicação.

#### Request
Abaixo está o modelo de requisição para a rota de faculdades.

```json
{
    "idFaculdade": "UUID",
    "nome": "string"
}
```

### /api/v1/cursos
Essa rota é responsável por gerenciar os cursos disponíveis na aplicação.

#### Request
Abaixo está o modelo de requisição para a rota de cursos.

```json
{
    "idCurso": "UUID",
    "nome": "string",
    "idTipoCurso": "UUID",
    "idFaculdade": "UUID"
}
```

### /api/v1/usuarios
Essa rota é responsável por gerenciar os usuários disponíveis na aplicação.

#### Request
Abaixo está o modelo de requisição para a rota de usuários.

```json
{
    "idUsuario": "UUID",
    "nome": "string",
    "email": "string",
    "senha": "string",
    "idCurso": "UUID",
    "numeroDeMatricula": "string",
    "dataDeNascimento": "string"
}
```

### /api/v1/assuntos
Essa rota é responsável por gerenciar os assuntos disponíveis na aplicação.

#### Request

```json
{
    "idAssunto": "UUID",
    "nome": "string"
}
```

### /api/v1/publicacao
Essa rota é responsável por gerenciar as publicações disponíveis na aplicação.

#### Request
Abaixo está o modelo de requisição para a rota de publicação.

```json
{
    "idPublicacao": "UUID",
    "titulo": "string",
    "resumo": "string",
    "conteudo": "string",
    "dataDePublicacao": "Date",
    "idAssunto": "UUID",
    "idUsuario": "UUID"
}
```