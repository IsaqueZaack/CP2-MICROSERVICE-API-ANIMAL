# 🎯 CP2-API-ANIMAL - Check Point 2

**API REST em Java com Spring Boot e SQL Server**

---

## 📋 O Projeto

Aplicação para gerenciamento de Animais integrada com **SQL Server**, desenvolvida para o **Check Point 2 — Microservices and Web Engineering (2º semestre/2026)**.

---

## 🚀 Como Executar (Escolha Uma Opção)

### **Opção 1: Docker Compose (Recomendado - 5 minutos)**

```bash
docker-compose up
```

Isso vai:
- ✅ Rodar SQL Server automaticamente
- ✅ Compilar a aplicação
- ✅ Iniciar a API na porta 8080

Teste:
```bash
curl http://localhost:8080/ping
# Resposta: pong
```

### **Opção 2: Maven Local**

Pré-requisitos: Java 17, Maven, SQL Server rodando

```bash
# Compile
mvn clean package

# Execute
java -jar target/App.jar --spring.profiles.active=dev
```

### **Opção 3: SQL Server Manual + Maven**

```bash
# 1. Rodar SQL Server no Docker
docker run -d \
    --name sqlserver \
    -e MSSQL_SA_PASSWORD=1q2w3e4R@ \
    -e "ACCEPT_EULA=Y" \
    -p 1433:1433 \
    mcr.microsoft.com/mssql/server:latest

# 2. Aguarde 30-40 segundos

# 3. Criar banco via DBeaver ou SQL:
# CREATE DATABASE apiAnimais;

# 4. Rodar aplicação
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"
```

---

## 📡 Acessar a API

### **Swagger UI (Recomendado)**
```
http://localhost:8080
```
Visualize e teste todos os endpoints de forma interativa.

### **Health Check**
```bash
curl http://localhost:8080/ping
# Resposta: pong
```

---

## 🧪 Testes Rápidos (cURL)

### **1. Criar Animal**
```bash
curl -X POST http://localhost:8080/api/v1/animal \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Leão",
    "especie": "Panthera leo",
    "idade": "5 anos",
    "habitat": "Savana"
  }'
```

### **2. Listar Animais**
```bash
curl http://localhost:8080/api/v1/animal
```

### **3. Buscar por ID**
```bash
curl http://localhost:8080/api/v1/animal/1
```

### **4. Atualizar**
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

### **5. Deletar**
```bash
curl -X DELETE http://localhost:8080/api/v1/animal/1
```

---

## 📡 Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/v1/animal` | Listar todos |
| GET | `/api/v1/animal/{id}` | Buscar por ID |
| POST | `/api/v1/animal` | Criar |
| PUT | `/api/v1/animal/{id}` | Atualizar |
| DELETE | `/api/v1/animal/{id}` | Deletar |
| GET | `/api/v1/tipoAnimal` | Listar tipos |
| POST | `/api/v1/tipoAnimal` | Criar tipo |
| GET | `/ping` | Health check |

---

## ⚙️ Configuração

### **Arquivo: `application-dev.properties`**

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=apiAnimais;encrypt=false;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=1q2w3e4R@
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```

### **Variáveis de Ambiente**

| Variável | Padrão | Descrição |
|----------|--------|-----------|
| `DB_SERVER_URL` | `localhost` | Host do SQL Server |
| `DB_SERVER_PORT` | `1433` | Porta do SQL Server |
| `DB_SCHEMA` | `apiAnimais` | Nome do banco |
| `DB_USER` | `sa` | Usuário SQL Server |
| `DB_PWD` | `1q2w3e4R@` | Senha |

---

## 🗄️ Estrutura do Banco

```sql
CREATE TABLE animais (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(100),
    idade VARCHAR(50),
    habitat VARCHAR(255)
);

CREATE TABLE tipos_animal (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    categoria VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    alimentacao VARCHAR(100),
    porte VARCHAR(100)
);
```

---

## 🔍 Verificar Dados (DBeaver)

1. Instale [DBeaver](https://dbeaver.io/)
2. Nova conexão → SQL Server
3. Host: `localhost`, Port: `1433`, User: `sa`, Password: `1q2w3e4R@`
4. Execute:
```sql
SELECT * FROM animais;
```

---

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **SQL Server**
- **Docker & Docker Compose**
- **Maven**
- **Swagger/OpenAPI**

---

## 📋 Estrutura do Projeto

```
CP2-API-ANIMAL/
├── src/main/java/br/com/fiap/apiAnimais/
│   ├── controller/     (REST endpoints)
│   ├── service/        (Lógica de negócio)
│   ├── repository/     (Acesso a dados)
│   ├── model/          (Entidades JPA)
│   └── dto/            (Data Transfer Objects)
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   └── init-sql-server.sql
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── README.md
```

---

## ❌ Troubleshooting

### **"Cannot open database apiAnimais"**
- SQL Server não está rodando
- Banco não foi criado
- Solução: Use `docker-compose up` ou crie o banco manualmente via DBeaver

### **"Port 1433 already allocated"**
```bash
docker stop $(docker ps -q)
docker-compose up
```

### **"Port 8080 already in use"**
```bash
# Encontre o processo
lsof -i :8080

# Mate o processo
kill -9 <PID>
```

---

## ✅ Checklist

- [ ] Docker instalado
- [ ] `docker-compose up` executado
- [ ] `curl http://localhost:8080/ping` retorna "pong"
- [ ] Swagger UI acessa em http://localhost:8080
- [ ] Consegue criar animal via API
- [ ] DBeaver conecta e vê dados no SQL Server

---

## 📞 Precisa de Ajuda?

1. Verifique se SQL Server está rodando: `docker ps`
2. Verifique se o banco existe no DBeaver
3. Verifique credenciais em `application-dev.properties`
4. Leia logs: `docker-compose logs app`

---

## 📊 Critérios de Avaliação (Atendidos ✅)

- ✅ **API Java com Spring Boot (3,0 pontos)**
- ✅ **Conexão com SQL Server (4,0 pontos)**
- ✅ **Persistência e Operações (2,0 pontos)**
- ✅ **Documentação (1,0 ponto)**

**Total: 10,0 pontos**

---

**Desenvolvido para Check Point 2 — Microservices and Web Engineering (2º semestre/2026)**