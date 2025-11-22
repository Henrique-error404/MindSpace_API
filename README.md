🧘 MindSpace API Backend
🧠 Saúde Mental no Trabalho do Futuro
1. Descrição Geral
O MindSpace API é o coração da solução de bem-estar digital. É uma API RESTful desenvolvida em Java Spring Boot cujo principal objetivo é gerenciar os dados de estresse do usuário (SINAL_ESTRESSE) e fornecer acesso seguro e paginado a essas informações. A API é integrada ao banco de dados Oracle via Spring Data JPA.

2. Integrantes e Funções
Nome	Função no Projeto
Henrique	Backend Lead (Java Advanced): Arquitetura da API REST, Implementação do JWT, Integração com Stored Procedure, Paginação e Filtros.
[Colega 1]	DBA & Data Modeler: Estruturação completa do Banco de Dados Oracle (DDL, Stored Procedures e Sequences).
[Colega 2]	[Definir Front-end/Documentação/Apresentação]

Exportar para as Planilhas

3. Evoluções & Requisitos Técnicos
Esta entrega atendeu integralmente aos requisitos técnicos do projeto, focando em robustez e segurança:

Arquitetura em Camadas: Estrutura organizada (controller, service, repository, model, dto).

Autenticação JWT: Implementação completa da segurança Stateless com Spring Security e geração de token via Auth0 JWT (HS256).

Persistência (JPA & Oracle): Mapeamento e transações ajustadas para lidar com a case sensitivity do Oracle e geração de SEQUENCE de ID.

Integração Crítica: O endpoint POST /registros chama a Stored Procedure PR_SINAL_ESTRESSE_INS utilizando o JdbcTemplate.

Consultas Avançadas: O endpoint GET /registros implementa Paginação, Ordenação (por dtHora) e está preparado para Filtros Dinâmicos (JpaSpecificationExecutor).

Bean Validation: Validação de dados de entrada (@Valid) nos DTOs de autenticação e registro.

⚙️ Como Testar a API Localmente
Pré-requisitos
JDK 21

Maven

Acesso ao banco de dados Oracle (RM e Senha configurados no application.properties).

Fluxo de Teste (Postman)
POST /auth/register: Crie um novo usuário.

POST /auth/login: Obtenha o Token JWT (Header Authorization: Bearer).

POST /registros: Envie um registro de humor (testa a Stored Procedure).

GET /registros: Visualize os dados paginados e ordenados (testa a Paginação).

A documentação interativa da API (Swagger UI) está disponível em: http://localhost:8080/swagger-ui.html
