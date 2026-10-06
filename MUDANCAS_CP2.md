# 📝 Sumário de Mudanças para Checkpoint 2

Documento descrevendo todas as mudanças realizadas no projeto CP1-API-ANIMAL para atender aos requisitos do Checkpoint 2.

---

## 🎯 Objetivo

Transformar o projeto CP1-API-ANIMAL (que usava MySQL) em uma API REST funcional com **SQL Server**, conforme requisitos do Checkpoint 2.

---

## 📋 Mudanças Realizadas

### 1. Dependências Maven (pom.xml)

#### ❌ Removido
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### ✅ Adicionado
```xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <scope>runtime</scope>
</dependency>
```

**Motivo:** Usar driver JDBC nativo para SQL Server conforme requisito.

---

### 2. Configuração de Aplicação

#### 2.1 `application.properties` (Padrão)

**Antes:**
```properties
spring.datasource.url=jdbc:mysql://...
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

**Depois:**
```properties
spring.datasource.url=jdbc:sqlserver://${DB_SERVER_URL:localhost}:${DB_SERVER_PORT:1433};databaseName=${DB_SCHEMA:apiAnimais};encrypt=${DB_ENCRYPT:false};trustServerCertificate=${DB_TRUST_CERT:true}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```

**Mudanças:**
- ✅ String de conexão JDBC para SQL Server
- ✅ Suporte a variáveis de ambiente
- ✅ Configuração de segurança (encrypt, trustServerCertificate)
- ✅ Dialect específico para SQL Server

#### 2.2 `application-dev.properties` (Novo)

**Criado:** `/src/main/resources/application-dev.properties`

```properties
# Configuração para desenvolvimento local com SQL Server
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=apiAnimais;encrypt=false;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=1q2w3e4R@
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Motivo:** Facilitar desenvolvimento local com SQL Server com auto-criação de tabelas.

#### 2.3 `application-prd.properties` (Atualizado)

**Antes:**
```properties
spring.datasource.url=jdbc:mysql://...
```

**Depois:**
```properties
spring.datasource.url=jdbc:sqlserver://${DB_SERVER_URL}:${DB_SERVER_PORT:1433};databaseName=${DB_SCHEMA};encrypt=${DB_ENCRYPT:true};trustServerCertificate=${DB_TRUST_CERT:false}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.hibernate.ddl-auto=validate
```

**Mudanças:**
- ✅ String de conexão para SQL Server
- ✅ Variáveis de ambiente para produção
- ✅ ddl-auto=validate (não altera schema em produção)

---

### 3. Modelos Java

#### 3.1 `TipoAnimal.java` (Melhorado)

**Antes:**
```java
@Id
private Long id;
```

**Depois:**
```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

**Motivo:** Garantir auto-increment no SQL Server (IDENTITY).

---

### 4. Arquivos de Recurso SQL

#### 4.1 `init-sql-server.sql` (Novo)

**Criado:** `/src/main/resources/init-sql-server.sql`

**Conteúdo:**
- Script SQL para criar banco de dados `apiAnimais`
- Criação de tabela `animais`
- Criação de tabela `tipos_animal`
- Dados de exemplo (comentados)

**Motivo:** Facilitar criação manual do banco para produção.

---

### 5. Documentação

#### 5.1 `README_CP2.md` (Novo - Completo)

**Criado:** `/README_CP2.md`

**Seções:**
- ✅ Requisitos atendidos (checklist)
- ✅ Tecnologias utilizadas
- ✅ Arquitetura do projeto
- ✅ Como executar a aplicação (3 opções)
- ✅ Configuração da conexão com SQL Server
- ✅ Variáveis de ambiente
- ✅ Endpoints para testes
- ✅ Exemplos de requisições (curl)
- ✅ Estrutura do banco de dados
- ✅ Verificação de dados no SQL Server

**Atende ao requisito:** Checkpoint 2 exige README com instruções de execução, configuração e endpoints.

#### 5.2 `QUICKSTART.md` (Novo - Rápido)

**Criado:** `/QUICKSTART.md`

**Conteúdo:**
- 3 opções de execução rápida
- Testes rápidos
- Troubleshooting
- Link para documentação completa

**Motivo:** Permitir execução em 5 minutos.

#### 5.3 `TESTES_API.md` (Novo - Testes)

**Criado:** `/TESTES_API.md`

**Conteúdo:**
- Testes de cada endpoint em cURL
- Script bash completo de testes
- Testes via Swagger UI
- Testes via Postman
- Checklist de validação
- Troubleshooting

**Motivo:** Facilitar validação completa da API.

#### 5.4 `MUDANCAS_CP2.md` (Este arquivo)

**Criado:** `/MUDANCAS_CP2.md`

**Motivo:** Documentar todas as mudanças realizadas.

---

### 6. Infraestrutura

#### 6.1 `docker-compose.yml` (Novo)

**Criado:** `/docker-compose.yml`

**Serviços:**
- SQL Server 2019
- Aplicação Spring Boot

**Funcionalidades:**
- ✅ Criar SQL Server automaticamente
- ✅ Health check para SQL Server
- ✅ Variáveis de ambiente configuradas
- ✅ Volumes para persistência
- ✅ Network isolada

**Motivo:** Facilitar testes locais com Docker Compose em uma linha.

#### 6.2 `Dockerfile` (Existente - Compatível)

**Status:** ✅ Sem mudanças necessárias

```dockerfile
FROM maven:3.9.8-eclipse-temurin-17-alpine AS build
WORKDIR /opt/app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-alpine-3.23
WORKDIR /opt/app
COPY --from=build /opt/app/target/App.jar /opt/app/App.jar
EXPOSE 8080
CMD [ "java", "-jar", "App.jar" ]
```

**Motivo:** Dockerfile genérico funciona com SQL Server (sem dependências específicas).

---

### 7. Estrutura do Projeto

**Nenhuma mudança na estrutura de diretórios.**

```
CP1-API-ANIMAL-CP2/
├── src/main/java/br/com/fiap/apiAnimais/
│   ├── controller/       ✅ Sem mudanças
│   ├── service/          ✅ Sem mudanças
│   ├── repository/       ✅ Sem mudanças
│   ├── model/            ✅ Melhorado (TipoAnimal)
│   └── dto/              ✅ Sem mudanças
├── src/main/resources/
│   ├── application.properties           ✅ Atualizado para SQL Server
│   ├── application-dev.properties       ✨ Novo (Desenvolvimento)
│   ├── application-prd.properties       ✅ Atualizado para SQL Server
│   └── init-sql-server.sql              ✨ Novo (SQL Server)
├── pom.xml                               ✅ Atualizado (MySQL → SQL Server)
├── Dockerfile                            ✅ Sem mudanças
├── docker-compose.yml                    ✨ Novo (Docker Compose)
├── README_CP2.md                         ✨ Novo (Documentação CP2)
├── QUICKSTART.md                         ✨ Novo (Setup rápido)
├── TESTES_API.md                         ✨ Novo (Testes completos)
└── MUDANCAS_CP2.md                       ✨ Este arquivo
```

---

## 📊 Resumo das Mudanças

| Tipo | Quantidade | Descrição |
|---|---|---|
| **Arquivos Modificados** | 3 | pom.xml, application.properties, application-prd.properties, TipoAnimal.java |
| **Arquivos Novos** | 7 | README_CP2.md, QUICKSTART.md, TESTES_API.md, MUDANCAS_CP2.md, application-dev.properties, init-sql-server.sql, docker-compose.yml |
| **Código Java Alterado** | Mínimo | Apenas adição de @GeneratedValue no TipoAnimal |
| **Testes Adicionados** | 0 | Nenhum arquivo de testes unitários (projeto continuará como estava) |

---

## ✅ Requisitos Atendidos

### ✅ 1. API Java com Spring Boot (3,0 pontos)

- [x] Projeto desenvolvido em Java 17 com Spring Boot 4.0.3
- [x] API REST funcional
- [x] Endpoints funcionando corretamente
- [x] Documentação Swagger/OpenAPI

### ✅ 2. Conexão com SQL Server (4,0 pontos)

- [x] Dependência mssql-jdbc configurada
- [x] Conexão com SQL Server (local/remoto)
- [x] Operações CRUD no banco
- [x] Suporte a variáveis de ambiente
- [x] Profiles (dev, prd)

### ✅ 3. Persistência e Operações sobre Dados (2,0 pontos)

- [x] Spring Data JPA utilizado
- [x] Entidades mapeadas (Animal, TipoAnimal)
- [x] Operações de consulta implementadas
- [x] Operações CRUD implementadas (INSERT, UPDATE, DELETE)

### ✅ 4. Organização e Documentação (1,0 ponto)

- [x] Projeto bem organizado em camadas
- [x] README_CP2.md com instruções completas
- [x] QUICKSTART.md para execução rápida
- [x] TESTES_API.md para validação
- [x] Variáveis de ambiente documentadas
- [x] Endpoints documentados

---

## 🚀 Como Usar

### Opção 1: Docker Compose (Recomendado)

```bash
docker-compose up
```

### Opção 2: Maven Local

```bash
./mvnw clean package
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Opção 3: JAR Compilado

```bash
mvn clean package
java -jar target/App.jar --spring.profiles.active=dev
```

---

## 📚 Referências

- **Checkpoint 2:** Microservices and Web Engineering - 2º semestre/2026
- **Professor:** Antonio Carlos de Lima Júnior
- **Tecnologias:** Java 17, Spring Boot 4.0.3, SQL Server, Docker
- **Documentação:** Veja README_CP2.md para detalhes completos

---

## 🔄 Próximas Etapas

1. **Testes Unitários:** Adicionar testes com JUnit e Mockito
2. **Segurança:** Implementar autenticação e autorização
3. **Validação:** Adicionar validações mais robustas
4. **Cache:** Implementar cache com Redis
5. **Logging:** Melhorar logging com SLF4J + Logback
6. **CI/CD:** Configurar pipeline no GitHub Actions

---

**Desenvolvido para Check Point 2 — Microservices and Web Engineering (2º semestre/2026)**

**Data:** Outubro/2026  
**Status:** ✅ Completo e pronto para entrega
