# 📦 CP1-API-ANIMAL

API RESTful desenvolvida em Spring Boot para o gerenciamento de animais e tipos de animais. Projeto entregue para o **Check Point 1 — Microservices and Web Engineering (2º semestre/2026)**.

Este documento descreve como executar e validar a aplicação a partir da imagem publicada no Docker Hub.

---

## 🛠️ Tecnologias

*   Java 17
*   Spring Boot (Web, Data JPA, Validation)
*   MySQL
*   Docker
*   ModelMapper
*   Springdoc OpenAPI / Swagger UI

---

## 🔗 Links do projeto

| Recurso | URL |
|---|---|
| Repositório GitHub | `https://github.com/KarlaFamula/CP1-API-ANIMAL` |
| Repositório Docker Hub | `https://hub.docker.com/r/karlafamula/CP1-API-ANIMAL` |

---

## ✅ Pré-requisitos para execução

*   Docker instalado e em execução.
*   Porta `8080` livre na máquina.
*   Porta `3306` livre, caso um MySQL local seja utilizado para os testes.

---

## 1. Baixar a imagem do Docker Hub

```sh
docker pull karlafamula/CP1-API-ANIMAL:1.0
```

---

## 2. Subir um banco de dados MySQL

A aplicação exige um banco MySQL acessível. Para subir um banco local rapidamente:

```sh
docker run -d \
  --name mysql-fiap \
  -e MYSQL_ROOT_PASSWORD=root_pwd \
  -e MYSQL_DATABASE=db_animais \
  -p 3306:3306 \
  mysql:8.0
```

Aguardar cerca de 15-20 segundos até o banco estar pronto antes de subir a aplicação.

---

## 3. Executar o container da aplicação

A aplicação possui dois profiles de execução: **default** e **prd**. O comando abaixo executa a aplicação com o profile `prd`, mapeando a porta `8080` e informando as variáveis de ambiente necessárias:

```sh
docker run \
  -p 8080:8080 \
  -e DB_SERVER_URL=host.docker.internal \
  -e DB_SERVER_PORT=3306 \
  -e DB_SCHEMA=db_animais \
  -e DB_USER=root \
  -e DB_PWD=root_pwd \
  -e SPRING_PROFILES_ACTIVE=prd \
  SEU_USUARIO_DOCKERHUB/CP1-API-ANIMAL:1.0
```

> `host.docker.internal` permite que o container da aplicação acesse o MySQL executado no host. Em ambientes Linux, pode ser necessário adicionar `--add-host=host.docker.internal:host-gateway` ao comando.

### Sobre o profile `prd`

No profile `prd`, o banco de dados e as tabelas **não** são criados automaticamente pela aplicação (`spring.jpa.hibernate.ddl-auto=none`). É necessário executar o script de migração disponível em `src/main/resources/migration-2026-17-08.sql` no banco MySQL antes de subir o container com esse profile.

Caso o objetivo seja apenas validar que a aplicação sobe e expõe suas rotas sem executar a migração manualmente, o profile **default** pode ser usado — nesse caso, basta omitir a variável `SPRING_PROFILES_ACTIVE` do comando acima, e o banco/tabelas serão criados automaticamente.

---

## 4. Acessar a documentação Swagger / OpenAPI

Com o container em execução, a documentação interativa da API fica disponível em:

```text
http://localhost:8080/
```

O Swagger UI está configurado para abrir diretamente na raiz da aplicação (`springdoc.swagger-ui.path=/`). A partir dele é possível visualizar e testar todos os endpoints de `Animal` e `TipoAnimal`.

Endpoint de verificação rápida (health check), sem depender do Swagger:

```text
http://localhost:8080/ping
```

Resposta esperada: `pong`.

---

## ⚙️ Variáveis de ambiente

| Variável | Descrição | Exemplo |
|---|---|---|
| `DB_SERVER_URL` | Endereço do servidor MySQL | `host.docker.internal` |
| `DB_SERVER_PORT` | Porta do MySQL | `3306` |
| `DB_SCHEMA` | Nome do schema/banco | `db_animais` |
| `DB_USER` | Usuário do banco de dados | `root` |
| `DB_PWD` | Senha do banco de dados | `root_pwd` |
| `SPRING_PROFILES_ACTIVE` | Profile ativo do Spring Boot (`prd` para produção; ausente = profile default) | `prd` |

---

## 📋 Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/v1/animal` | Lista todos os animais |
| GET | `/api/v1/animal/{id}` | Busca um animal por ID |
| POST | `/api/v1/animal` | Cadastra um novo animal |
| PUT | `/api/v1/animal/{id}` | Atualiza um animal existente |
| DELETE | `/api/v1/animal/{id}` | Remove um animal |
| GET | `/api/v1/tipoAnimal` | Lista todos os tipos de animal |
| POST | `/api/v1/tipoAnimal` | Cadastra um novo tipo de animal |

A lista completa e interativa está disponível no Swagger UI (item 4).

---

## ⚙️ Profiles do Spring Boot

Este projeto possui dois profiles de execução:

*   **default** (`application.properties`) — usado quando `SPRING_PROFILES_ACTIVE` não é definida. Cria o banco e as tabelas automaticamente (`ddl-auto=update`).
*   **prd** (`application-prd.properties`) — usado quando `SPRING_PROFILES_ACTIVE=prd`. **Não** cria banco nem tabelas automaticamente (`ddl-auto=none`); depende da execução prévia do script de migração.

---

## 🐳 Build local da imagem (alternativa ao Docker Hub)

Caso seja necessário gerar a imagem a partir do código-fonte, na raiz do repositório:

```sh
docker build -t cp1-api-animal:1.0 .
docker run \
  -p 8080:8080 \
  -e DB_SERVER_URL=host.docker.internal \
  -e DB_SERVER_PORT=3306 \
  -e DB_SCHEMA=db_animais \
  -e DB_USER=root \
  -e DB_PWD=root_pwd \
  -e SPRING_PROFILES_ACTIVE=prd \
  cp1-api-animal:1.0
```

---

## 🔒 Segurança

Nenhuma credencial real está versionada neste repositório. As credenciais utilizadas nos exemplos acima (`root` / `root_pwd`) são exclusivamente para fins de teste local.