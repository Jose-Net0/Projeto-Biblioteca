# 🚀 Modelo API REST - Projeto e Desenvolvimento de Sistemas

Este repositório contém a **estrutura base (esqueleto)** para o desenvolvimento do projeto prático da disciplina de **Projeto e Desenvolvimento de Sistemas** (IFTM).

O projeto já está configurado com as dependências necessárias, permitindo que você foque na implementação das Regras de Negócio e da API.

---

## 🛠️ Tecnologias Pré-configuradas

* **Java 17** (LTS)
* **Spring Boot 3.3.3**
* **Spring Data JPA** (Banco de Dados)
* **H2 Database** (Banco em memória - Padrão inicial)
* **MySQL Driver** (Pronto para conectar no futuro)
* **Swagger UI (SpringDoc)** (Interface visual para testar a API)
* **OpenPDF** (Gerador de relatórios)

---

## ⚙️ Pré-requisitos

Antes de começar, certifique-se de ter instalado:
1.  **JDK 17** (Java Development Kit).
2.  Uma IDE de sua preferência (**VS Code**, IntelliJ ou Eclipse).
    * *Dica: No VS Code, instale o "Extension Pack for Java".*
3.  **Git** (para clonar o repositório).

---

## 🏃‍♂️ Como Rodar o Projeto

### 1. Clone o repositório
Abra o terminal (Git Bash ou CMD) e digite:
```bash
git clone [https://github.com/brunoqp78/modelo_api_rest.git](https://github.com/brunoqp78/modelo_api_rest.git)

## 2. 📥 Como Importar na IDE

### Opção A: VS Code (Recomendado)
1.  Abra o VS Code.
2.  Vá em **File > Open Folder...** e selecione a pasta `modelo_api_rest` que você acabou de clonar.
3.  **Aguarde:** No canto inferior direito, o VS Code vai carregar o projeto Java. Espere até que todas as dependências do Maven sejam baixadas (pode levar uns minutos).

### Opção B: IntelliJ IDEA
1.  Vá em **File > Open**.
2.  Selecione o arquivo `pom.xml` dentro da pasta do projeto.
3.  Clique em **Open as Project**.

### Opção C: Eclipse
1.  Vá em **File > Import...**
2.  Selecione **Maven > Existing Maven Projects**.
3.  Aponte para a pasta `modelo_api_rest` e clique em Finish.

---

## 3. ▶️ Como Rodar o Projeto

1.  Na árvore de arquivos da esquerda, navegue até:
    `src/main/java/org/iftm/modelo_api_rest/ModeloApiRestApplication.java`
2.  Abra este arquivo.
3.  Clique no botão **Run** (ou "Play") que aparece acima do método `main`, ou clique com o botão direito no arquivo e escolha **Run Java**.

> **Dica:** Fique de olho no **Console** (parte de baixo da tela). Se aparecer um logo do "Spring" e a frase `Started ModeloApiRestApplication`, deu certo!

---

## 4. 🌐 Acessando o Sistema (Swagger UI)

Este projeto não possui telas HTML complexas. Nós usamos o **Swagger** para visualizar e testar o sistema.

1.  Com o projeto rodando, abra seu navegador (Chrome/Edge).
2.  Acesse o link:
    👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Aqui você verá seus *Controllers* e poderá testar botões como "Try it out" para salvar ou listar dados.

---

## 5. 📂 Onde devo programar?

A estrutura de pastas já está pronta. Siga esta organização para não se perder:

* 📂 `src/main/java/org/iftm/modelo_api_rest`
    * 📂 `entity` ➡️ **(Comece aqui)** Crie suas classes básicas (Ex: `Aluno.java`).
    * 📂 `repository` ➡️ Interfaces de conexão com o banco.
    * 📂 `service` ➡️ Regras de negócio e validações.
    * 📂 `controller` ➡️ Onde criamos os links da API (Endpoints).
    * 📂 `dto` ➡️ Classes para transferência de dados (segurança).

---

## 6. 🗄️ Banco de Dados (H2)

Para facilitar, estamos usando um **Banco em Memória (H2)**.
* **Importante:** Toda vez que você parar o projeto (Stop), os dados cadastrados **serão apagados**. Isso é normal para fase de testes.
* Caso queira ver as tabelas e dados brutos, acesse:
    * Link: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * JDBC URL: `jdbc:h2:mem:testdb`
    * User: `sa`
    * Password: *(deixe em branco)*

---

## 🆘 Problemas Comuns

* **Erro "Port 8080 already in use":**
    * Você já tem outro projeto rodando. Pare o anterior (botão Stop/Quadrado vermelho) antes de rodar este.
* **Erro no `pom.xml` ou Dependências:**
    * Clique com o botão direito no projeto > **Maven > Update Project** (ou Reload).
* **O Swagger dá erro 404:**
    * Verifique se o projeto realmente iniciou sem erros vermelhos no console.

---

## 👨‍🏫 Responsável

**Professor:** Dr. Bruno Queiroz Pinto
**Instituição:** IFTM - Campus Uberlândia Centro
