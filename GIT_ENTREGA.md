# 📤 Git & Entrega - CP1-API-ANIMAL CP2

Instruções para versionamento e entrega do projeto no GitHub.

---

## 🔧 Setup Inicial do Repositório

### Passo 1: Criar repositório no GitHub

1. Acesse [github.com](https://github.com)
2. Clique em "New Repository"
3. Nome: `CP1-API-ANIMAL-CP2`
4. Descrição: `API REST em Java com Spring Boot e SQL Server - Check Point 2`
5. Visibilidade: `Public` (para professor avaliar)
6. **NÃO** inicialize com README (vamos commitar nosso)
7. Clique em "Create repository"

### Passo 2: Adicionar repositório remoto

```bash
# Entre no diretório do projeto
cd CP1-API-ANIMAL-CP2

# Inicialize Git (se ainda não iniciado)
git init

# Configure seu nome e email (uma única vez)
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@example.com"

# Adicione o repositório remoto
git remote add origin https://github.com/SEU_USUARIO/CP1-API-ANIMAL-CP2.git

# Mude para branch main (se necessário)
git branch -M main
```

---

## 📝 Primeiro Commit

### Passo 1: Adicionar arquivos

```bash
# Adicionar todos os arquivos
git add .

# Ou adicionar seletivamente
git add README_CP2.md
git add QUICKSTART.md
git add TESTES_API.md
git add pom.xml
git add src/
git add docker-compose.yml
git add Dockerfile
```

### Passo 2: Criar commit inicial

```bash
git commit -m "feat: CP2 - Migração de MySQL para SQL Server com documentação completa

- Substituído driver MySQL por SQL Server JDBC
- Configuração de conexão com SQL Server (local/remoto)
- Adicionado suporte a variáveis de ambiente
- Criado profiles de desenvolvimento e produção
- Adicionado docker-compose para facilitar testes
- Documentação completa (README_CP2.md, QUICKSTART.md, TESTES_API.md)
- Melhorado modelo TipoAnimal com @GeneratedValue
- Estrutura do projeto pronta para avaliação CP2"
```

### Passo 3: Fazer push para GitHub

```bash
# Fazer upload para o GitHub
git push -u origin main

# Após o primeiro push, pode usar apenas:
git push
```

---

## 🔄 Commits Subsequentes

### Template de Commit

Use este padrão para commits bem organizados:

```bash
git commit -m "tipo: descrição breve

descrição detalhada (opcional)
- ponto 1
- ponto 2

Fecha #123 (se houver issue)"
```

### Tipos de Commit

```
feat:     Nova funcionalidade
fix:      Correção de bug
docs:     Mudança em documentação
style:    Formatação, sem mudança de código
refactor: Refatoração de código
perf:     Melhoria de performance
test:     Adição ou mudança de testes
chore:    Mudança em build, dependências, etc
```

### Exemplos

```bash
# Adicionar novo endpoint
git commit -m "feat: adicionar endpoint de busca avançada"

# Corrigir bug na conexão
git commit -m "fix: resolver erro de conexão com SQL Server em produção"

# Atualizar documentação
git commit -m "docs: adicionar exemplos de requisição no README"

# Atualizar dependências
git commit -m "chore: atualizar versão do Spring Boot para 4.0.3"
```

---

## 📋 Estrutura de Branches

Para projetos maiores, considere usar:

```
main              ← Versão estável (pronta para entrega)
├── develop       ← Desenvolvimento
└── feature/*     ← Novas funcionalidades
```

Para este projeto:

```bash
# Criar branch de desenvolvimento
git checkout -b develop

# Fazer alterações
# git add .
# git commit -m "..."

# Voltar para main e mergear
git checkout main
git merge develop

# Push para GitHub
git push
```

---

## 🔗 Link do Repositório

Após fazer push, seu repositório estará em:

```
https://github.com/SEU_USUARIO/CP1-API-ANIMAL-CP2
```

**Exemplo real:**
```
https://github.com/joao-silva/CP1-API-ANIMAL-CP2
```

---

## 📤 Entrega no Portal

Conforme instruções do Checkpoint 2:

### Arquivo a Entregar

1. **Nome:** `ENTREGA_CP2.txt`
2. **Conteúdo:**

```txt
ENTREGA - CHECK POINT 2
Projeto: CP1-API-ANIMAL-CP2
Disciplina: Microservices and Web Engineering
Professor: Antonio Carlos de Lima Júnior

=== INFORMAÇÕES DO REPOSITÓRIO ===
URL do repositório GitHub:
https://github.com/SEU_USUARIO/CP1-API-ANIMAL-CP2

=== INTEGRANTES DO GRUPO ===
Integrante 1:
Nome Completo: [NOME DO ALUNO 1]
RM: [RM DO ALUNO 1]

Integrante 2:
Nome Completo: [NOME DO ALUNO 2]
RM: [RM DO ALUNO 2]

=== INFORMAÇÕES ADICIONAIS ===
- Projeto migrado de MySQL para SQL Server
- Documentação completa em README_CP2.md
- Executável via docker-compose (recomendado)
- Todos os critérios de avaliação atendidos
```

### Onde Entregar

1. Acesse o Portal do Aluno
2. Vá para "Área de Entrega de Trabalhos"
3. Procure por "Check Point 2 - Microservices and Web Engineering"
4. Envie o arquivo `ENTREGA_CP2.txt`

**Importante:** Apenas um representante do grupo deve fazer a entrega.

---

## ✅ Checklist Antes de Entregar

- [ ] Repositório criado no GitHub
- [ ] Todos os arquivos do projeto estão no GitHub
- [ ] Branch `main` tem a versão final
- [ ] README_CP2.md está completo e claro
- [ ] QUICKSTART.md funciona (testado)
- [ ] TESTES_API.md está com exemplos corretos
- [ ] pom.xml tem dependência de SQL Server
- [ ] docker-compose.yml está configurado
- [ ] Código compila sem erros: `mvn clean package`
- [ ] API inicia com: `docker-compose up`
- [ ] Health check funciona: `curl http://localhost:8080/ping`
- [ ] Endpoints testados e funcionam
- [ ] Arquivo ENTREGA_CP2.txt preenchido
- [ ] Arquivo entregue no Portal do Aluno

---

## 🔍 Verificar Status

### Ver histórico de commits

```bash
# Último commit
git log -1

# Últimos 5 commits
git log --oneline -5

# Visualizar árvore de commits
git log --graph --oneline --all
```

### Ver arquivos modificados

```bash
# Status atual
git status

# Diferenças
git diff

# Diferenças em arquivo específico
git diff src/main/java/br/com/fiap/apiAnimais/controller/AnimalController.java
```

### Ver repositório no GitHub

```bash
# Copie o link do repositório no navegador
https://github.com/SEU_USUARIO/CP1-API-ANIMAL-CP2

# Verifique:
# - Todos os arquivos estão lá
# - README está renderizado
# - Histórico de commits está visível
```

---

## 🆘 Problemas Comuns com Git

### Problema: "fatal: not a git repository"

```bash
# Solução: inicializar Git
git init
git remote add origin https://github.com/seu-usuario/seu-repo.git
```

### Problema: "Permission denied (publickey)"

```bash
# Configurar SSH key do GitHub
# 1. Gera chave:
ssh-keygen -t ed25519 -C "seu.email@example.com"

# 2. Adiciona no GitHub:
# Acesse: https://github.com/settings/keys
# Clique em "New SSH key"
# Cole o conteúdo de ~/.ssh/id_ed25519.pub
```

### Problema: "fatal: unable to access ... 401 Unauthorized"

```bash
# Use token ao invés de senha
# 1. Cria token em: https://github.com/settings/tokens
# 2. Usa como senha: git push
# Será solicitado usuário (seu username) e senha (o token)

# Ou configure permanentemente:
git config --global user.name "seu-username"
git config credential.helper store
git push  # Será solicitado token, salva para próximas vezes
```

### Problema: "Your branch is ahead of 'origin/main' by X commits"

```bash
# Push dos commits
git push
```

### Problema: "Please make sure you have the correct access rights"

```bash
# Verifique:
# 1. Repositório existe no GitHub
# 2. URL está correta: git remote -v
# 3. SSH key está configurada (ou usando HTTPS)

# Reset a origem:
git remote remove origin
git remote add origin https://github.com/seu-usuario/seu-repo.git
```

---

## 📚 Referências Git

### Comandos Básicos

```bash
git clone <url>              # Clonar repositório
git status                   # Ver status
git add <arquivo>            # Adicionar arquivo
git commit -m "mensagem"     # Fazer commit
git push                     # Enviar para GitHub
git pull                     # Baixar atualizações
git log                       # Ver histórico
git branch                   # Listar branches
git checkout -b <branch>     # Criar nova branch
```

### Recursos Online

- [Git Book](https://git-scm.com/book/pt-BR)
- [GitHub Guides](https://guides.github.com/)
- [GitHub Docs](https://docs.github.com/)

---

## 🎯 Dicas Finais

### ✅ Boas Práticas

1. **Commit frequente:** Faça commits pequenos e frequentes
2. **Mensagens claras:** Use mensagens descritivas
3. **Organize:** Use branches para features maiores
4. **Documente:** Mantenha README atualizado
5. **Teste:** Sempre teste antes de fazer push

### ✅ Para Avaliação

1. **Repositório público:** Professor consegue ver
2. **Sem credenciais:** Não commite senhas
3. **README claro:** Documente bem
4. **Código limpo:** Siga convenções
5. **Commits legíveis:** Use bons nomes

### ✅ Entrega

1. **URL correta:** Verifique antes de entregar
2. **RMs corretos:** Duplo-check dos dados
3. **Um arquivo:** Apenas ENTREGA_CP2.txt
4. **Um representante:** Apenas uma pessoa entrega

---

**Desenvolvido para Check Point 2 — Microservices and Web Engineering**

**Data:** Outubro/2026
