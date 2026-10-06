# 🚀 Quick Start - CP1-API-ANIMAL CP2

Guia rápido para colocar a aplicação rodando em **5 minutos**.

---

## ⚡ Opção 1: Docker Compose (Recomendado)

### Pré-requisitos
- Docker e Docker Compose instalados

### Executar

```bash
# Clone o repositório
git clone <seu-repositorio>
cd CP1-API-ANIMAL-CP2

# Execute com Docker Compose (cria SQL Server + App)
docker-compose up
```

**Espere 30-40 segundos até o SQL Server estar pronto.**

### Acessar
- **API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/
- **SQL Server:** localhost:1433 (usuário: `sa`, senha: `1q2w3e4R@`)

---

## ⚡ Opção 2: Maven + SQL Server Local

### Pré-requisitos
- Java 17+
- Maven 3.6+
- SQL Server rodando em `localhost:1433`

### Passo 1: Compilar

```bash
./mvnw clean package
```

### Passo 2: Executar

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Acessar
- **API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/

---

## ⚡ Opção 3: JAR Compilado

### Compilar

```bash
./mvnw clean package
```

### Executar

```bash
# Modo desenvolvimento
java -jar target/App.jar --spring.profiles.active=dev

# Modo padrão (cria tabelas automaticamente)
java -jar target/App.jar
```

---

## 🧪 Testar Rápido

### Health Check
```bash
curl http://localhost:8080/ping
# Resposta: pong
```

### Criar Animal
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

### Listar Animais
```bash
curl http://localhost:8080/api/v1/animal
```

### Acessar Swagger
Abra no navegador: **http://localhost:8080/**

---

## 🛑 Parar a Aplicação

### Docker Compose
```bash
docker-compose down
```

### Maven/JAR
Pressione `Ctrl+C` no terminal

---

## ❌ Problemas Comuns

### "Connection refused" ao SQL Server
- Verifique se SQL Server está rodando
- Confirme a porta (padrão: 1433)
- Teste conectividade: `telnet localhost 1433`

### "Port 8080 already in use"
```bash
# Encontre qual processo usa a porta
lsof -i :8080

# Mate o processo (Mac/Linux)
kill -9 <PID>

# Ou use outra porta na variável de ambiente
export SERVER_PORT=8081
```

### Docker Compose falha ao conectar
```bash
# Recrie os containers
docker-compose down -v
docker-compose up --build
```

---

## 📚 Documentação Completa

Veja **README_CP2.md** para:
- Arquitetura detalhada
- Todos os endpoints
- Exemplos de requisições
- Variáveis de ambiente
- Testes avançados

---

**Desenvolvido para Check Point 2 — Microservices and Web Engineering**
