# 📚 Biblioteca Unicesumar

Sistema web desenvolvido em Java para gerenciamento de livros acadêmicos. Permite realizar operações completas de cadastro, listagem, edição e exclusão de livros, com integração ao banco de dados PostgreSQL.

---

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Apache Tomcat 9.0.98**
- **PostgreSQL**
- **JSP (JavaServer Pages)**
- **Servlets**
- **Maven** (gerenciador de dependências e build)
- **SLF4J + Logback** (para logs customizados)
- **JDBC (Java Database Connectivity)**

---

## 🧩 Arquitetura

- **MVC (Model-View-Controller)**:
  - **Model**: Representa os dados (livros) e regras de acesso ao banco.
  - **View**: JSPs que exibem as páginas HTML ao usuário.
  - **Controller**: Servlets que controlam o fluxo da aplicação.

---

## ✅ Funcionalidades

- 🔍 Listagem de livros cadastrados  
- ➕ Cadastro de novos livros  
- ✏️ Edição dos dados de um livro existente  
- ❌ Exclusão de livros  
- 🔁 Atualização automática da base (verificação/criação de tabelas ao iniciar)  
- 🧾 Logs detalhados (info/debug)

---

## 💾 Banco de Dados

- **PostgreSQL** com conexão via **JDBC**
- Tabela criada automaticamente ao iniciar o sistema (caso não exista)
- Requisitos:
  - Banco de dados criado previamente
  - Nome, usuário e senha configurados no contexto do Tomcat (context.xml)

---

## 🚀 Como executar

1. Clone o projeto:
   ```bash
    git clone https://github.com/seu-usuario/biblioteca-unicesumar.git
   ```

2. Importe o projeto no seu IDE (Eclipse, IntelliJ ou VS Code)

3. Crie o banco de dados com o nome biblioteca_unicesumar_db, faça a conexão com o usuário e a senha corretos. (obs: usuário e senha especificados no context.xml)

4. A tabela se criará automaticamente

5. Compile a aplicação, gerando o .WAR corretamente utilizando `mvn clean package`

6. Copie o .war gferado para a pasta webapps do tomcat (baixe-o se não tiver instalado)

7. Acesse a pasta do tomcat -> bin -> startup.bat, executando o arquivo o servidor iniciará.

8. Acesse no navegador: http://localhost:8080/biblioteca-unicesumar/

---

## 🎨 Layout

Simples e funcional, utilizando HTML5 + CSS básico nas páginas JSP para foco na usabilidade.

---

## 📜Licença

Este projeto está sob a licença MIT. Sinta-se livre para estudar, modificar e distribuir.

## ✍️ Autor
Alex Martini
Desenvolvedor Java Web • Estudante Unicesumar

---