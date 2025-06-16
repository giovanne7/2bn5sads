# 🔐 API de Autenticação com Spring Boot e JWT

**Objetivo**  
Desenvolver uma API em Java usando Spring Boot que implemente autenticação JWT e controle de acesso por papéis (admin e user), conforme especificado na prova prática.

---

## 📝 Descrição do Projeto

Esta aplicação permite:

- **Cadastro** de usuários (com definição de papel “USER” ou “ADMIN”).
- **Login** via JWT, gerando um token que garante acesso às rotas protegidas.
- **Controle de acesso** baseado em roles, de modo que:
    - Apenas **ADMIN** pode listar, editar ou excluir qualquer usuário.
    - **USER** pode visualizar e editar somente seu próprio perfil.

---

## 🚀 Funcionalidades Implementadas

1. **/auth/register** (POST)
    - Registra novo usuário com nome, e‑mail, senha e role.
2. **/auth/login** (POST)
    - Autentica e retorna `{ "token": "…" }`.
3. **/user/** (GET, PUT)
    - Acesso a perfil próprio (autenticado).
4. **/admin/** (GET, PUT, DELETE)
    - Acesso restrito a administradores.

---

## ⚙️ Tecnologias e Ferramentas

- **Java 17**
- **Spring Boot** + Spring Security + JJWT
- **Lombok** para reduzir boilerplate
- **H2 (em memória)** — substituível por MySQL/PostgreSQL
- **Maven** como gerenciador de dependências
- **Git** com commits frequentes e mensagens claras

---

## 🏗️ Estrutura do Repositório

src/
├── main/
│ ├── java/com/seuprojeto/
│ │ ├── config/ # SecurityConfig, filtros, etc.
│ │ ├── controller/ # REST controllers
│ │ ├── dto/ # Data Transfer Objects
│ │ ├── model/ # Entidades JPA
│ │ ├── repository/ # Interfaces JPA
│ │ ├── security/ # TokenHelper, filtros JWT
│ │ └── service/ # Regras de negócio
│ └── resources/
│ └── application.properties
└── test/ # Testes unitários/integrados

yaml
Copiar
Editar

---

## ⚙️ Configuração do Ambiente

1. Instalar **JDK 17+**
2. Clonar repositório e abrir no IntelliJ (projeto Maven)
3. Definir no `application.properties`:
   ```properties
   server.port=8081
   jwt.secret=<sua-chave-base64>
   spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
   spring.h2.console.enabled=true
Executar a classe JwtEllielApplication

▶️ Como Testar
Cadastrar

http
Copiar
Editar
POST http://localhost:8081/auth/register
Content-Type: application/json

{
"email": "usuario@exemplo.com",
"senha": "123456",
"role": "USER"
}
Fazer login

http
Copiar
Editar
POST http://localhost:8081/auth/login
Content-Type: application/json

{
"email": "usuario@exemplo.com",
"senha": "123456"
}
→ copia o "token" da resposta.

Chamar rota protegida

sql
Copiar
Editar
GET http://localhost:8081/user/profile
Authorization: Bearer <token>

👤 Desenvolvedor
Nome: Giovanne Leite

RA: 23269587-2

Curso: Análise e Desenvolvimento de Sistemas 5º semestre