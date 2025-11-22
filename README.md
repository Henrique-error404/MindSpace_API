# MindSpace API Backend 🧠

Saúde Mental no Trabalho — API REST para registro de sinais de estresse, autenticação e persistência em Oracle.  

---

## Sumário 🔍
- [Visão Geral](#visão-geral)  
- [Estrutura do Projeto](#estrutura-do-projeto)  
- [Recursos Implementados](#recursos-implementados)  
- [Pré-requisitos](#pré-requisitos)  
- [Configuração](#configuração)  
- [Executar](#executar)  
- [Endpoints Principais](#endpoints-principais)  
- [Integrantes](#integrantes)  
- [Observações Técnicas](#observações-técnicas)

---

## Visão Geral 🔎
API construída com Spring Boot seguindo arquitetura em camadas (controller → service → repository). Objetivo: registrar sinais de estresse, autenticar usuários com JWT e persistir dados em Oracle. 🙂

---

## Estrutura do projeto 🧩
- `controllers/` — endpoints REST  
- `services/` — regras de negócio  
- `repositories/` — JPA/Hibernate + `JdbcTemplate` para chamadas diretas a procedures  
- `dtos/` — validação (Bean Validation)

---

## Recursos Implementados ⚙️
- Autenticação com JWT (Spring Security + Auth0 JWT) 🔐  
- Hash de senhas com BCrypt 🔒  
- Chamada de Stored Procedure crítica via `JdbcTemplate` (POST `/registros` → `PR_SINAL_ESTRESSE_INS`) 🧪  
- Persistência JPA com mapeamento ajustado para padrão de nomes do Oracle (case sensitivity) 🗄️  
- Consultas avançadas: paginação, ordenação (por `dtHora`) e filtros dinâmicos em `GET /registros` 📊  
- Validação dos DTOs (Bean Validation) ✅  
- Documentação via Swagger UI 🌐

---

## Pré-requisitos 🛠️
- JDK 21  
- Maven  
- Instância Oracle acessível (credenciais no `application.properties`)

---

## Configuração ⚙️
No arquivo `application.properties` (ou `application.yml`) configure:
- `spring.datasource.url`  
- `spring.datasource.username`  
- `spring.datasource.password`  
- `spring.jpa.hibernate.naming.physical-strategy` (se aplicável)

---

## Executar ▶️
No terminal:
```bash
mvn clean install
mvn spring-boot:run
```

---

## Endpoints Principais 🔗
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/auth/register` | Cria novo usuário (senha com BCrypt) 👤 |
| POST   | `/auth/login`    | Autentica e retorna token JWT (Bearer) 🔑 |
| POST   | `/registros`     | Registra sinal de estresse (executa stored procedure `PR_SINAL_ESTRESSE_INS`) 🧪 |
| GET    | `/registros?page=0&sort=dtHora,desc` | Consulta paginada e ordenada por `dtHora` 📋 |

Swagger UI: http://localhost:8080/swagger-ui.html 🌍

---

## Integrantes 👥
| Nome     | RM            |
|----------|---------------|
| Henrique | RM560698      |
| Luan     | RM (pendente) |
| Japa     | RM (pendente) |

---

## Observações Técnicas 📝
- A procedure crítica é invocada diretamente via `JdbcTemplate` para garantir controle e performance transacional. ⚠️  
- Ajuste dos nomes das entidades (`@Entity`) para maiúsculas conforme convenção Oracle para evitar problemas de case sensitivity. 🔠  
- Garanta que o usuário do banco possua permissões para executar a procedure e manipular sequences. ✅

Exportar para as Planilhas (se necessário): mantenha os metadados de rota e contribuições em CSV/Excel a partir das tabelas acima. 📎
