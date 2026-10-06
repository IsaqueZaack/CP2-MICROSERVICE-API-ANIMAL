# 📚 Índice de Documentação - CP1-API-ANIMAL CP2

Guia completo de navegação da documentação do projeto para Check Point 2.

---

## 🎯 Para Começar Rápido

| Documento | Tempo | Descrição |
|---|---|---|
| **[QUICKSTART.md](QUICKSTART.md)** | ⚡ 5 min | Executar a API em 5 minutos |
| **[Ping Test](#ping-test)** | 🔧 1 min | Verificar se está funcionando |

### Ping Test

```bash
curl http://localhost:8080/ping
# Resposta: pong
```

---

## 📖 Documentação Principal

### 1. **[README_CP2.md](README_CP2.md)** 📋 (Leitura Obrigatória)

**Conteúdo:**
- ✅ Requisitos do Checkpoint 2
- 🛠️ Tecnologias utilizadas
- 🏗️ Arquitetura do projeto
- 🚀 Como executar (3 opções)
- ⚙️ Configuração SQL Server
- 📡 Endpoints da API
- 📝 Exemplos de requisições
- 🗄️ Estrutura do banco de dados

**Quando ler:** Primeira leitura após clonar o repositório

**Tempo:** ~20 minutos

---

### 2. **[QUICKSTART.md](QUICKSTART.md)** ⚡ (Para Pressa)

**Conteúdo:**
- 🐳 Opção 1: Docker Compose (5 min)
- 🏗️ Opção 2: Maven local (10 min)
- 📦 Opção 3: JAR compilado (5 min)
- 🧪 Testes rápidos
- ❌ Troubleshooting

**Quando usar:** Se quer executar rapidinho

**Tempo:** ~5-10 minutos

---

### 3. **[TESTES_API.md](TESTES_API.md)** 🧪 (Validação Completa)

**Conteúdo:**
- 1️⃣ Health check
- 2️⃣ CRUD de Animais
- 3️⃣ CRUD de Tipos de Animais
- 4️⃣ Script bash completo
- 5️⃣ Queries SQL Server
- 6️⃣ Testes Swagger UI
- 7️⃣ Testes Postman

**Quando usar:** Para validar se tudo está funcionando

**Tempo:** ~15-20 minutos

---

### 4. **[MUDANCAS_CP2.md](MUDANCAS_CP2.md)** 📝 (Comparação)

**Conteúdo:**
- Mudança 1: MySQL → SQL Server
- Mudança 2: Configurações
- Mudança 3: Modelos Java
- Mudança 4: Documentação
- Mudança 5: Infraestrutura
- Resumo de mudanças

**Quando usar:** Para entender o que foi alterado do CP1

**Tempo:** ~10 minutos

---

## 📁 Estrutura de Arquivos

```
CP1-API-ANIMAL-CP2/
│
├── 📚 DOCUMENTACAO.md                  ← Você está aqui
├── 📋 README_CP2.md                    ← Leitura obrigatória
├── ⚡ QUICKSTART.md                    ← Execução rápida
├── 🧪 TESTES_API.md                    ← Testes completos
├── 📝 MUDANCAS_CP2.md                  ← O que mudou
├── 📑 README.md                        ← Documentação original CP1
│
├── 📦 pom.xml                          ← Dependências Maven (SQL Server)
├── 🐳 Dockerfile                       ← Containerização
├── 🐳 docker-compose.yml               ← Docker + SQL Server
│
├── src/main/java/br/com/fiap/apiAnimais/
│   ├── Application.java                ← Classe principal
│   │
│   ├── controller/
│   │   ├── AnimalController.java       ← Endpoints de Animal
│   │   ├── TipoAnimalController.java   ← Endpoints de Tipo
│   │   └── PingController.java         ← Health check
│   │
│   ├── service/
│   │   └── AnimalService.java          ← Lógica de negócio
│   │
│   ├── repository/
│   │   ├── AnimalRepository.java       ← Acesso a dados - Animal
│   │   └── TipoAnimalRepository.java   ← Acesso a dados - Tipo
│   │
│   ├── model/
│   │   ├── Animal.java                 ← Entidade JPA
│   │   └── TipoAnimal.java             ← Entidade JPA (melhorada)
│   │
│   └── dto/
│       ├── AnimalCreateRequest.java    ← DTO criação
│       ├── AnimalUpdateRequest.java    ← DTO atualização
│       ├── AnimalResponse.java         ← DTO resposta
│       └── AnimalMapper.java           ← Mapeador
│
├── src/main/resources/
│   ├── application.properties          ← Config padrão (SQL Server)
│   ├── application-dev.properties      ← Config desenvolvimento
│   ├── application-prd.properties      ← Config produção
│   └── init-sql-server.sql             ← Script SQL Server
│
└── src/test/
    └── java/br/com/fiap/apiAnimais/
        └── ApplicationTests.java       ← Testes básicos
```

---

## 🎓 Fluxo de Aprendizado Sugerido

### Para o Professor Avaliar
1. Ler [README_CP2.md](README_CP2.md) (visão geral)
2. Executar [QUICKSTART.md](QUICKSTART.md) (5 min)
3. Rodar testes [TESTES_API.md](TESTES_API.md) (15 min)
4. Verificar mudanças [MUDANCAS_CP2.md](MUDANCAS_CP2.md) (10 min)

**Tempo total:** ~30-40 minutos

### Para o Desenvolvedor Trabalhar
1. Clonar repositório
2. Ler [QUICKSTART.md](QUICKSTART.md)
3. Executar `docker-compose up`
4. Acessar Swagger: http://localhost:8080
5. Rodar testes com cURL ou Postman
6. Consultar [README_CP2.md](README_CP2.md) para dúvidas

---

## 🔍 Busca Rápida

### "Como executar a aplicação?"
👉 [QUICKSTART.md](QUICKSTART.md)

### "Como testar os endpoints?"
👉 [TESTES_API.md](TESTES_API.md)

### "Como configurar SQL Server?"
👉 [README_CP2.md](README_CP2.md#-configuração-da-conexão-com-sql-server)

### "Quais são os endpoints?"
👉 [README_CP2.md](README_CP2.md#-endpoints-para-testes)

### "Como usar com Docker?"
👉 [README_CP2.md](README_CP2.md#opção-2-executar-com-docker-compose)

### "O que mudou do CP1?"
👉 [MUDANCAS_CP2.md](MUDANCAS_CP2.md)

### "Qual é a estrutura do projeto?"
👉 [README_CP2.md](README_CP2.md#-arquitetura-do-projeto)

### "Como conectar a SQL Server remoto?"
👉 [README_CP2.md](README_CP2.md#exemplo-conectar-a-sql-server-remoto)

---

## 🚀 Executando Agora

### Opção 1: Docker Compose (⭐ Recomendado)

```bash
# Clone e entre no diretório
git clone <seu-repo>
cd CP1-API-ANIMAL-CP2

# Execute (leva ~40 segundos na primeira vez)
docker-compose up

# Em outro terminal, teste:
curl http://localhost:8080/ping
# Resposta: pong
```

### Opção 2: Maven Local

```bash
# Instale Java 17, Maven, e SQL Server

# Compile
mvn clean package

# Execute
java -jar target/App.jar --spring.profiles.active=dev
```

### Opção 3: IDE (IntelliJ, VS Code)

1. Abra o projeto
2. Aguarde Maven baixar dependências
3. Configure variáveis de ambiente para SQL Server
4. Execute `Application.java`

---

## 📊 Checklist de Funcionamento

Use este checklist para validar tudo está funcionando:

```bash
# 1. API iniciou?
curl http://localhost:8080/ping
# Esperado: pong

# 2. Swagger está acessível?
# Abra no navegador: http://localhost:8080
# Você deve ver a documentação dos endpoints

# 3. Consegue criar animal?
curl -X POST http://localhost:8080/api/v1/animal \
  -H "Content-Type: application/json" \
  -d '{"nome":"Leão","especie":"Panthera leo","idade":"5 anos","habitat":"Savana"}'
# Esperado: Status 201 com JSON da resposta

# 4. Consegue listar animais?
curl http://localhost:8080/api/v1/animal
# Esperado: Array JSON com animais

# 5. Dados persistem no SQL Server?
# Conecte ao SQL Server e execute:
# SELECT * FROM animais;
# Você deve ver os dados inseridos
```

---

## 🆘 Precisa de Ajuda?

### Erro: "Connection refused"
👉 SQL Server não está rodando. Veja [Troubleshooting](QUICKSTART.md#-problemas-comuns)

### Erro: "Port 8080 already in use"
👉 Outra aplicação usa a porta. Veja [Solução](QUICKSTART.md#-problemas-comuns)

### Erro: "Cannot find database"
👉 O banco não foi criado. Use profile `dev` que cria automaticamente.

### Dúvidas sobre endpoints?
👉 Veja [Exemplos de Requisições](README_CP2.md#-exemplos-de-requisições)

### Dúvidas sobre variáveis de ambiente?
👉 Veja [Variáveis de Ambiente](README_CP2.md#variáveis-de-ambiente)

---

## 📞 Contato e Suporte

**Checkpoint:** Check Point 2 — Microservices and Web Engineering  
**Professor:** Antonio Carlos de Lima Júnior  
**Semestre:** 2º semestre/2026

---

## 📚 Recursos Adicionais

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SQL Server JDBC Driver](https://learn.microsoft.com/pt-br/sql/connect/jdbc/getting-started-with-the-jdbc-driver)
- [Docker Documentation](https://docs.docker.com/)
- [Maven Documentation](https://maven.apache.org/guides/index.html)

---

## ✅ Critérios de Avaliação (Checkpoint 2)

- [x] **API Java com Spring Boot (3,0 pontos)**
  - [x] Projeto em Java 17 com Spring Boot
  - [x] API REST funcional
  - [x] Endpoints funcionando

- [x] **Conexão com SQL Server (4,0 pontos)**
  - [x] Configuração de conexão
  - [x] Conexão realizada
  - [x] CRUD funcionando

- [x] **Persistência e Operações (2,0 pontos)**
  - [x] Spring Data JPA
  - [x] Entidades/Modelos
  - [x] Operações CRUD

- [x] **Organização e Documentação (1,0 ponto)**
  - [x] Projeto bem organizado
  - [x] README com instruções
  - [x] Endpoints documentados

**Total: 10,0 pontos** ✅

---

**Última atualização:** Outubro/2026  
**Status:** ✅ Pronto para entrega
