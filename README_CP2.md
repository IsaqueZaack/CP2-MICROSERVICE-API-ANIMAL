# 📦 CP1-API-ANIMAL - Check Point 2

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de Animais, com acesso a um **banco de dados SQL Server**.

**Checkpoint:** Check Point 2 — Microservices and Web Engineering (2º semestre/2026)  
**Disciplina:** Microservices and Web Engineering  
**Professor:** Antonio Carlos de Lima Júnior

---

## 📋 Tabela de Conteúdos

1. [Requisitos Atendidos](#requisitos-atendidos)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Arquitetura do Projeto](#arquitetura-do-projeto)
4. [Como Executar a Aplicação](#como-executar-a-aplicação)
5. [Configuração da Conexão com SQL Server](#configuração-da-conexão-com-sql-server)
6. [Endpoints para Testes](#endpoints-para-testes)
7. [Exemplos de Requisições](#exemplos-de-requisições)
8. [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)

---

## ✅ Requisitos Atendidos

### 1. API Java com Spring Boot ✓
- ✅ Projeto desenvolvido em Java 17
- ✅ Framework Spring Boot 4.0.3
- ✅ API REST funcional e bem estruturada
- ✅ Endpoints implementados corretamente
- ✅ Documentação Swagger/OpenAPI integrada

### 2. Conexão com SQL Server ✓
- ✅ Driver JDBC para SQL Server configurado
- ✅ Configuração de conexão com SQL Server local/remoto
- ✅ Operações CRUD no banco de dados
- ✅ Suporte a variáveis de ambiente
- ✅ Profiles para desenvolvimento e produção

### 3. Persistência e Operações sobre Dados ✓
- ✅ Spring Data JPA para camada de persistência
- ✅ Entidades mapeadas para tabelas do SQL Server
- ✅ Operações de consulta (SELECT)
- ✅ Operações de inserção, alteração e exclusão (INSERT, UPDATE, DELETE)

### 4. Organização e Documentação ✓
- ✅ Estrutura organizada por camadas (Controller, Service, Repository, DTO, Model)
- ✅ README com instruções completas
- ✅ Informações de conexão e endpoints

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.3 | Framework web |
| Spring Data JPA | 4.0.3 | Persistência de dados |
| SQL Server JDBC | 12.x | Conector para SQL Server |
| Maven | 3.x+ | Gerenciador de dependências |
| Springdoc OpenAPI | 3.0.2 | Documentação API (Swagger) |
| ModelMapper | 3.1.1 | Mapeamento de DTOs |
| Validation | 4.0.3 | Validação de dados |

---

## 🏗️ Arquitetura do Projeto

```
cp1-api-animal/
├── src/main/java/br/com/fiap/apiAnimais/
│   ├── controller/
│   │   ├── AnimalController.java      # Endpoints de Animal
│   │   ├── TipoAnimalController.java  # Endpoints de Tipo de Animal
│   │   └── PingController.java        # Health check
│   ├── service/
│   │   └── AnimalService.java         # Lógica de negócio
│   ├── repository/
│   │   ├── AnimalRepository.java      # Acesso a dados - Animal
│   │   └── TipoAnimalRepository.java  # Acesso a dados - Tipo Animal
│   ├── model/
│   │   ├── Animal.java                # Entidade JPA - Animal
│   │   └── TipoAnimal.java            # Entidade JPA - Tipo Animal
│   ├── dto/
│   │   ├── AnimalCreateRequest.java   # DTO para criação
│   │   ├── AnimalUpdateRequest.java   # DTO para atualização
│   │   ├── AnimalResponse.java        # DTO de resposta
│   │   └── AnimalMapper.java          # Mapeador de entidades
│   └── Application.java               # Classe principal
├── src/main/resources/
│   ├── application.properties         # Configuração padrão
│   ├── application-dev.properties     # Configuração desenvolvimento
│   └── application-prd.properties     # Configuração produção
├── pom.xml                            # Dependências Maven
└── Dockerfile                         # Para containerização
```

---

## 🚀 Como Executar a Aplicação

### Pré-requisitos

- **Java 17** ou superior instalado
- **Maven 3.6+** instalado (ou use o `mvnw` fornecido)
- **SQL Server** local ou remoto acessível
- Porta **8080** disponível

### Opção 1: Executar localmente com Maven

#### Passo 1: Clonar o repositório

```bash
git clone https://github.com/seu-usuario/CP1-API-ANIMAL-CP2.git
cd CP1-API-ANIMAL-CP2
```

#### Passo 2: Compilar o projeto

```bash
mvn clean package
```

Ou use o `mvnw` fornecido:

```bash
./mvnw clean package
```

#### Passo 3: Executar a aplicação

**Modo Desenvolvimento (com profile `dev`):**

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

**Ou compilado:**

```bash
java -jar target/App.jar --spring.profiles.active=dev
```

**Modo Padrão (com criação automática de tabelas):**

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

### Opção 2: Executar com Docker Compose

#### Passo 1: Criar arquivo `docker-compose.yml`

```yaml
version: '3.8'

services:
  sqlserver:
    image: mcr.microsoft.com/mssql/server:2019-latest
    environment:
      SA_PASSWORD: "1q2w3e4R@"
      ACCEPT_EULA: "Y"
    ports:
      - "1433:1433"
    volumes:
      - sqlserver_data:/var/opt/mssql

  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      DB_SERVER_URL: sqlserver
      DB_SERVER_PORT: 1433
      DB_SCHEMA: apiAnimais
      DB_USER: sa
      DB_PWD: "1q2w3e4R@"
      SPRING_PROFILES_ACTIVE: dev
    depends_on:
      - sqlserver
    volumes:
      - sqlserver_data:/var/opt/mssql

volumes:
  sqlserver_data:
```

#### Passo 2: Executar com Docker Compose

```bash
docker-compose up
```

---

## ⚙️ Configuração da Conexão com SQL Server

### Arquivos de Configuração

A aplicação utiliza três perfis de configuração Spring Boot:

#### 1. `application.properties` (Padrão)

Configuração padrão com variáveis de ambiente para SQL Server:

```properties
spring.datasource.url=jdbc:sqlserver://${DB_SERVER_URL:localhost}:${DB_SERVER_PORT:1433};databaseName=${DB_SCHEMA:apiAnimais};encrypt=${DB_ENCRYPT:false};trustServerCertificate=${DB_TRUST_CERT:true}
spring.datasource.username=${DB_USER:sa}
spring.datasource.password=${DB_PWD:1q2w3e4R@}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

#### 2. `application-dev.properties` (Desenvolvimento)

Configuração para desenvolvimento local:

```properties
# Servidor SQL Server local
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=apiAnimais;encrypt=false;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=1q2w3e4R@
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# Hibernate auto-cria tabelas
spring.jpa.hibernate.ddl-auto=update
```

#### 3. `application-prd.properties` (Produção)

Configuração para produção com variáveis de ambiente:

```properties
spring.datasource.url=jdbc:sqlserver://${DB_SERVER_URL}:${DB_SERVER_PORT:1433};databaseName=${DB_SCHEMA};encrypt=${DB_ENCRYPT:true};trustServerCertificate=${DB_TRUST_CERT:false}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PWD}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# Hibernate valida apenas (não altera schema)
spring.jpa.hibernate.ddl-auto=validate
```

### Variáveis de Ambiente

| Variável | Descrição | Padrão | Exemplo |
|---|---|---|---|
| `DB_SERVER_URL` | Hostname/IP do SQL Server | `localhost` | `192.168.1.100` ou `sql.empresa.com` |
| `DB_SERVER_PORT` | Porta do SQL Server | `1433` | `1433` (padrão) |
| `DB_SCHEMA` | Nome do banco de dados | `apiAnimais` | `apiAnimais` |
| `DB_USER` | Usuário SQL Server | `sa` | `sa` ou outro usuário |
| `DB_PWD` | Senha do SQL Server | `1q2w3e4R@` | Sua senha |
| `DB_ENCRYPT` | Usar encriptação | `false` (dev) / `true` (prod) | `true` |
| `DB_TRUST_CERT` | Confiar em certificados | `true` (dev) / `false` (prod) | `true` |
| `SPRING_PROFILES_ACTIVE` | Profile ativo | nenhum (padrão) | `dev` ou `prd` |

### Exemplo: Conectar a SQL Server Remoto

```bash
# Via variáveis de ambiente
export DB_SERVER_URL=192.168.1.100
export DB_SERVER_PORT=1433
export DB_SCHEMA=apiAnimais
export DB_USER=sa
export DB_PWD=SenhaForte123!

# Executar a aplicação
java -jar target/App.jar --spring.profiles.active=dev
```

---

## 📡 Endpoints para Testes

A aplicação expõe os seguintes endpoints RESTful:

### Health Check

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/ping` | Verifica se a API está respondendo |

**Resposta esperada:** `pong`

### Documentação Interativa

| Recurso | URL |
|---|---|
| Swagger UI | `http://localhost:8080/` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |

### Endpoints de Animal

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| GET | `/api/v1/animal` | Lista todos os animais | 200 |
| GET | `/api/v1/animal/{id}` | Busca animal por ID | 200/404 |
| POST | `/api/v1/animal` | Cria novo animal | 201 |
| PUT | `/api/v1/animal/{id}` | Atualiza animal | 200/404 |
| DELETE | `/api/v1/animal/{id}` | Deleta animal | 204/404 |

### Endpoints de Tipo de Animal

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| GET | `/api/v1/tipoAnimal` | Lista tipos de animal | 200 |
| POST | `/api/v1/tipoAnimal` | Cria tipo de animal | 201 |

---

## 📝 Exemplos de Requisições

### 1. Health Check

```bash
curl -X GET http://localhost:8080/ping
```

**Resposta:**
```
pong
```

### 2. Listar todos os animais

```bash
curl -X GET http://localhost:8080/api/v1/animal
```

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Leão",
    "especie": "Panthera leo",
    "idade": "5 anos",
    "habitat": "Savana Africana"
  }
]
```

### 3. Buscar animal por ID

```bash
curl -X GET http://localhost:8080/api/v1/animal/1
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Leão",
  "especie": "Panthera leo",
  "idade": "5 anos",
  "habitat": "Savana Africana"
}
```

### 4. Criar novo animal

```bash
curl -X POST http://localhost:8080/api/v1/animal \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Tigre",
    "especie": "Panthera tigris",
    "idade": "3 anos",
    "habitat": "Floresta Tropical"
  }'
```

**Resposta (201 Created):**
```json
{
  "id": 2,
  "nome": "Tigre",
  "especie": "Panthera tigris",
  "idade": "3 anos",
  "habitat": "Floresta Tropical"
}
```

### 5. Atualizar animal

```bash
curl -X PUT http://localhost:8080/api/v1/animal/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Leão Africano",
    "especie": "Panthera leo",
    "idade": "6 anos",
    "habitat": "Savana do Quênia"
  }'
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "Leão Africano",
  "especie": "Panthera leo",
  "idade": "6 anos",
  "habitat": "Savana do Quênia"
}
```

### 6. Deletar animal

```bash
curl -X DELETE http://localhost:8080/api/v1/animal/1
```

**Resposta (204 No Content):**
```
(sem corpo)
```

### 7. Criar Tipo de Animal

```bash
curl -X POST http://localhost:8080/api/v1/tipoAnimal \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Felino",
    "descricao": "Animais da família Felidae"
  }'
```

---

## 🗄️ Estrutura do Banco de Dados

### Tabela: `animais`

A tabela é criada automaticamente pelo Hibernate quando `spring.jpa.hibernate.ddl-auto=update`.

```sql
CREATE TABLE animais (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(100),
    idade VARCHAR(50),
    habitat VARCHAR(255)
);
```

### Tabela: `tipo_animal`

```sql
CREATE TABLE tipo_animal (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
);
```

---

## 🧪 Testando a Aplicação

### Via Swagger UI

1. Acesse `http://localhost:8080/`
2. Veja todos os endpoints disponíveis
3. Clique em "Try it out" para testar cada endpoint
4. Observe as respostas e códigos HTTP

### Via cURL (linha de comando)

Veja exemplos na seção [Exemplos de Requisições](#exemplos-de-requisições).

### Via Postman

1. Importe ou crie requisições para cada endpoint
2. Configure as variáveis de ambiente conforme necessário
3. Execute os testes

---

## 📊 Verificação de Operações no SQL Server

Para verificar se os dados estão sendo persistidos corretamente:

```sql
-- Conectar ao SQL Server
sqlcmd -S localhost -U sa -P "1q2w3e4R@" -d apiAnimais

-- Verificar dados na tabela
SELECT * FROM animais;
SELECT * FROM tipo_animal;
```

Ou com Azure Data Studio / SQL Server Management Studio:

1. Conecte ao SQL Server (`localhost,1433` com usuário `sa`)
2. Selecione o banco `apiAnimais`
3. Execute queries para verificar os dados

---

## 🔒 Notas de Segurança

⚠️ **As credenciais utilizadas nos exemplos são apenas para DESENVOLVIMENTO LOCAL.**

Em produção:
- Use credenciais seguras
- Use variáveis de ambiente
- Ative encriptação (`encrypt=true`)
- Desabilite `trustServerCertificate` ou configure certificados válidos
- Use HTTPS
- Implemente autenticação/autorização

---

## 📚 Recursos Adicionais

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SQL Server JDBC Driver](https://learn.microsoft.com/pt-br/sql/connect/jdbc/getting-started-with-the-jdbc-driver)
- [Springdoc OpenAPI](https://springdoc.org/)

---

## 📞 Contato e Suporte

Para dúvidas ou problemas:
1. Verifique a configuração de conexão com SQL Server
2. Consulte os logs da aplicação
3. Teste a conectividade com o banco: `telnet localhost 1433`

---

**Desenvolvido para Check Point 2 — Microservices and Web Engineering (2º semestre/2026)**
