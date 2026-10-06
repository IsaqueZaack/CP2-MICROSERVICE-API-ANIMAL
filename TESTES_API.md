# 🧪 Testes da API - CP1-API-ANIMAL CP2

Script completo de testes para validar todos os endpoints da API.

**Base URL:** `http://localhost:8080`

---

## 1️⃣ Health Check

Verificar se a API está respondendo:

```bash
curl -X GET http://localhost:8080/ping
```

**Resposta esperada:** `pong` (código 200)

---

## 2️⃣ Operações com Animal

### 2.1 Criar Animal

```bash
curl -X POST http://localhost:8080/api/v1/animal \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Leão",
    "especie": "Panthera leo",
    "idade": "5 anos",
    "habitat": "Savana Africana"
  }'
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "nome": "Leão",
  "especie": "Panthera leo",
  "idade": "5 anos",
  "habitat": "Savana Africana"
}
```

---

### 2.2 Listar Todos os Animais

```bash
curl -X GET http://localhost:8080/api/v1/animal
```

**Resposta esperada (200 OK):**
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

---

### 2.3 Buscar Animal por ID

```bash
curl -X GET http://localhost:8080/api/v1/animal/1
```

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "nome": "Leão",
  "especie": "Panthera leo",
  "idade": "5 anos",
  "habitat": "Savana Africana"
}
```

**Buscar ID inexistente (404 Not Found):**
```bash
curl -X GET http://localhost:8080/api/v1/animal/999
```

---

### 2.4 Atualizar Animal

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

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "nome": "Leão Africano",
  "especie": "Panthera leo",
  "idade": "6 anos",
  "habitat": "Savana do Quênia"
}
```

---

### 2.5 Deletar Animal

```bash
curl -X DELETE http://localhost:8080/api/v1/animal/1
```

**Resposta esperada (204 No Content):**
```
(sem corpo na resposta)
```

---

## 3️⃣ Operações com Tipo de Animal

### 3.1 Criar Tipo de Animal

```bash
curl -X POST http://localhost:8080/api/v1/tipoAnimal \
  -H "Content-Type: application/json" \
  -d '{
    "categoria": "Felino",
    "descricao": "Animais da família Felidae",
    "alimentacao": "Carnívoro",
    "porte": "Grande"
  }'
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "categoria": "Felino",
  "descricao": "Animais da família Felidae",
  "alimentacao": "Carnívoro",
  "porte": "Grande"
}
```

---

### 3.2 Listar Todos os Tipos de Animal

```bash
curl -X GET http://localhost:8080/api/v1/tipoAnimal
```

**Resposta esperada (200 OK):**
```json
[
  {
    "id": 1,
    "categoria": "Felino",
    "descricao": "Animais da família Felidae",
    "alimentacao": "Carnívoro",
    "porte": "Grande"
  }
]
```

---

### 3.3 Buscar Tipo de Animal por ID

```bash
curl -X GET http://localhost:8080/api/v1/tipoAnimal/1
```

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "categoria": "Felino",
  "descricao": "Animais da família Felidae",
  "alimentacao": "Carnívoro",
  "porte": "Grande"
}
```

---

### 3.4 Atualizar Tipo de Animal

```bash
curl -X PUT http://localhost:8080/api/v1/tipoAnimal/1 \
  -H "Content-Type: application/json" \
  -d '{
    "categoria": "Grande Felino",
    "descricao": "Grandes felinos selvagens",
    "alimentacao": "Carnívoro",
    "porte": "Gigante"
  }'
```

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "categoria": "Grande Felino",
  "descricao": "Grandes felinos selvagens",
  "alimentacao": "Carnívoro",
  "porte": "Gigante"
}
```

---

### 3.5 Deletar Tipo de Animal

```bash
curl -X DELETE http://localhost:8080/api/v1/tipoAnimal/1
```

**Resposta esperada (204 No Content):**
```
(sem corpo na resposta)
```

---

## 4️⃣ Script de Teste Completo (Bash)

Salve este script como `test-api.sh` e execute:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080"

echo "🧪 Iniciando testes da API..."
echo ""

# 1. Health Check
echo "1️⃣  Health Check"
curl -s -X GET $BASE_URL/ping
echo -e "\n"

# 2. Criar Animal 1
echo "2️⃣  Criando Animal 1"
ANIMAL_1=$(curl -s -X POST $BASE_URL/api/v1/animal \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Leão",
    "especie": "Panthera leo",
    "idade": "5 anos",
    "habitat": "Savana Africana"
  }')
echo "$ANIMAL_1" | jq '.'
echo ""

# 3. Criar Animal 2
echo "3️⃣  Criando Animal 2"
ANIMAL_2=$(curl -s -X POST $BASE_URL/api/v1/animal \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Tigre",
    "especie": "Panthera tigris",
    "idade": "3 anos",
    "habitat": "Floresta Tropical"
  }')
echo "$ANIMAL_2" | jq '.'
echo ""

# 4. Listar Animais
echo "4️⃣  Listando todos os animais"
curl -s -X GET $BASE_URL/api/v1/animal | jq '.'
echo ""

# 5. Buscar Animal por ID
echo "5️⃣  Buscando animal com ID 1"
curl -s -X GET $BASE_URL/api/v1/animal/1 | jq '.'
echo ""

# 6. Criar Tipo de Animal
echo "6️⃣  Criando Tipo de Animal"
TIPO=$(curl -s -X POST $BASE_URL/api/v1/tipoAnimal \
  -H "Content-Type: application/json" \
  -d '{
    "categoria": "Felino",
    "descricao": "Animais carnívoros felinos",
    "alimentacao": "Carnívoro",
    "porte": "Grande"
  }')
echo "$TIPO" | jq '.'
echo ""

# 7. Listar Tipos
echo "7️⃣  Listando tipos de animais"
curl -s -X GET $BASE_URL/api/v1/tipoAnimal | jq '.'
echo ""

# 8. Atualizar Animal
echo "8️⃣  Atualizando animal com ID 1"
curl -s -X PUT $BASE_URL/api/v1/animal/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Leão Majestoso",
    "especie": "Panthera leo",
    "idade": "6 anos",
    "habitat": "Savana do Quênia"
  }' | jq '.'
echo ""

echo "✅ Testes finalizados!"
```

Execute:
```bash
chmod +x test-api.sh
./test-api.sh
```

---

## 5️⃣ Verificação no SQL Server

### Conectar ao SQL Server

```bash
# Com sqlcmd (SQL Server Command Line)
sqlcmd -S localhost -U sa -P "1q2w3e4R@" -d apiAnimais

# Ou com Azure Data Studio (GUI)
```

### Verificar dados nas tabelas

```sql
-- Verificar animais
SELECT * FROM animais;

-- Verificar tipos de animais
SELECT * FROM tipos_animal;

-- Contar registros
SELECT COUNT(*) as 'Total Animais' FROM animais;
SELECT COUNT(*) as 'Total Tipos' FROM tipos_animal;
```

---

## 6️⃣ Testes via Swagger UI

1. Abra no navegador: `http://localhost:8080/`
2. Você verá todos os endpoints listados
3. Clique em cada endpoint para expandir
4. Clique em "Try it out"
5. Preencha os dados no formulário
6. Clique em "Execute"
7. Veja a resposta e o código de status HTTP

---

## 7️⃣ Testes via Postman

### Importar Collection

1. Abra Postman
2. Clique em "Import"
3. Cole o JSON abaixo ou importe do arquivo

### Collection JSON

```json
{
  "info": {
    "name": "CP1-API-ANIMAL",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Health Check",
      "request": {
        "method": "GET",
        "url": "{{base_url}}/ping"
      }
    },
    {
      "name": "Criar Animal",
      "request": {
        "method": "POST",
        "url": "{{base_url}}/api/v1/animal",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"nome\": \"Leão\", \"especie\": \"Panthera leo\", \"idade\": \"5 anos\", \"habitat\": \"Savana\"}"
        }
      }
    },
    {
      "name": "Listar Animais",
      "request": {
        "method": "GET",
        "url": "{{base_url}}/api/v1/animal"
      }
    }
  ],
  "variable": [
    {
      "key": "base_url",
      "value": "http://localhost:8080"
    }
  ]
}
```

---

## ✅ Checklist de Testes

Utilize este checklist para validar a aplicação:

- [ ] API inicia sem erros
- [ ] Health check retorna "pong"
- [ ] Swagger UI carrega corretamente
- [ ] Consegue criar animal (201)
- [ ] Consegue listar animais (200)
- [ ] Consegue buscar animal por ID (200)
- [ ] Consegue atualizar animal (200)
- [ ] Consegue deletar animal (204)
- [ ] Consegue criar tipo de animal (201)
- [ ] Consegue listar tipos de animal (200)
- [ ] Consegue buscar tipo por ID (200)
- [ ] Consegue atualizar tipo (200)
- [ ] Consegue deletar tipo (204)
- [ ] Dados persistem no SQL Server
- [ ] Retorna 404 para recursos inexistentes
- [ ] Valida campos obrigatórios

---

## 🔍 Debug e Troubleshooting

### Ver logs da aplicação

```bash
# Se execução com Docker Compose
docker-compose logs -f app

# Se execução com Maven
./mvnw spring-boot:run 2>&1 | grep -i animal

# Se execução com JAR
java -jar target/App.jar 2>&1 | tail -50
```

### Testar conectividade com SQL Server

```bash
# Linux/Mac
telnet localhost 1433

# Windows
Test-NetConnection -ComputerName localhost -Port 1433
```

### Verificar se banco foi criado

```bash
sqlcmd -S localhost -U sa -P "1q2w3e4R@" \
  -Q "SELECT name FROM sys.databases WHERE name='apiAnimais'"
```

---

**Desenvolvido para Check Point 2 — Microservices and Web Engineering**
