# API de Livros

## Objetivo

Esta API foi desenvolvida propositalmente com diversos problemas de arquitetura e violações dos princípios SOLID estudados em aula.

O objetivo da atividade é analisar o código, identificar os problemas existentes e realizar as refatorações necessárias para tornar a aplicação mais organizada, flexível e aderente às boas práticas de desenvolvimento.

## O que deve ser feito

* Identificar violações dos princípios SOLID presentes no projeto.
* Refatorar a aplicação corrigindo os problemas encontrados.
* Manter o funcionamento da API após as alterações.
* Documentar brevemente quais problemas foram identificados e como foram corrigidos.

## Endpoints

### Listar livros

```http
GET /livros
```

### Cadastrar livro

```http
POST /livros
```

Exemplo de corpo da requisição:

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "preco": 120.0,
  "categoria": "TECNOLOGIA"
}
```

### Atualizar livro

```http
PUT /livros/{id}
```

Exemplo:

```http
PUT /livros/1
```

```json
{
  "titulo": "Clean Architecture",
  "autor": "Robert C. Martin",
  "preco": 150.0,
  "categoria": "TECNOLOGIA"
}
```

### Remover livro

```http
DELETE /livros/{id}
```

Exemplo:

```http
DELETE /livros/1
```

## Banco de Dados

O projeto utiliza H2 Database em memória.

Console H2:

```text
http://localhost:8080/h2-console
```

Configurações padrão:

```text
JDBC URL: jdbc:h2:mem:refacdb
User Name: sa
Password:
```

## Entrega

Enviar:

* Link do repositório contendo a solução.
* Documento curto explicando quais princípios SOLID foram identificados e aplicados durante a refatoração.

O foco da atividade não é apenas fazer a API funcionar, mas demonstrar compreensão dos conceitos de arquitetura e orientação a objetos estudados em aula.
