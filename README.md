# Desafio Design Patterns — Bootcamp Santander DIO

API REST em Spring Boot para buscar livros na Google Books e registrar indicações, com frontend web simples para testes.

## Pré-requisitos

- **Java 21**
- **Docker Desktop** (rodando antes de subir a API)
- **Node.js** (apenas para servir o frontend localmente)

## 1. Google Books API Key

A busca de livros usa a [Google Books API](https://developers.google.com/books). Sem chave, o limite diário de requisições esgota rapidamente.

1. Acesse o [Google Cloud Console](https://console.cloud.google.com/)
2. Crie um projeto (ou use um existente)
3. Ative a **Books API**
4. Em **APIs & Services → Credentials**, crie uma **API Key**

### Configuração local

Em `src/main/resources/application.properties`:

```properties
google.books.api-key=SUA_CHAVE_AQUI
```

### Deploy no Railway

Não commite a chave no repositório. Configure a variável de ambiente:

| Variável | Valor |
|----------|-------|
| `GOOGLE_BOOKS_API_KEY` | sua API key da Google Books |

O Spring Boot mapeia automaticamente para `google.books.api-key`.

## 2. Subir a API (backend)

Com o **Docker Desktop aberto**, na raiz do projeto:

```bash
./gradlew bootRun
```

No Windows:

```powershell
.\gradlew.bat bootRun
```

O Spring Boot sobe o PostgreSQL automaticamente via `compose.yaml` e inicia a API em **http://localhost:8080**.

Aguarde a mensagem:

```
Started DesafioDesignPatternsBootcampSantanderDioApplication
```

> O processo fica rodando — isso é normal. Para parar, use `Ctrl+C`.

### Endpoints principais

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/livros?titulo=...` | Busca por título |
| GET | `/livros?autor=...` | Busca por autor |
| GET | `/indicacoes` | Lista indicações |
| POST | `/indicacoes` | Cadastra indicação |

## 3. Subir o frontend

Em **outro terminal**, na raiz do projeto:

```bash
npx serve frontend
```

Acesse a URL exibida no terminal (ex.: **http://localhost:3000**).

> Não abra o `index.html` direto no navegador (`file://`). Use sempre um servidor local.

O frontend aponta para a API em `http://localhost:8080` (configurável em `frontend/js/app.js`).

## 4. Testar

1. Busque um livro por título ou autor
2. Clique em um resultado
3. Preencha nome, nota e motivo
4. Salve a indicação — ela aparecerá na lista abaixo

## Estrutura do projeto

```
├── frontend/          # HTML, CSS e JS (interface de testes)
├── src/main/java/     # API Spring Boot
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/  # Scripts Flyway
└── compose.yaml       # PostgreSQL (gerenciado pelo Spring Boot)
```

## Problemas comuns

| Problema | Solução |
|----------|---------|
| `failed to connect to the docker API` | Abra o Docker Desktop e aguarde iniciar |
| `Port 8080 was already in use` | Encerre o processo na porta 8080 ou mude `server.port` |
| Erro na busca / limite Google Books | Configure `google.books.api-key` ou `GOOGLE_BOOKS_API_KEY` |
| Frontend não conecta na API | Use `npx serve frontend` e confirme que a API está em `:8080` |
