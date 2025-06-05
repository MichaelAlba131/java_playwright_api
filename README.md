<!DOCTYPE html>
<html lang="pt-br">
<body>

<h1>🧪 Java Playwright API Test Automation</h1>

<div>
  <span class="badge">🎯 Maven</span>
  <span class="badge">☕ Java 17</span>
  <span class="badge">🎭 Playwright 1.44.0</span>
  <span class="badge">🥒 Cucumber 7.15.0</span>
  <span class="badge">📊 Allure Reports 2.24.0</span>
</div>

<h2>✨ Visão Geral</h2>
<p>
Este projeto foi desenvolvido por um QA Engineer Sênior e visa entregar uma estrutura robusta de automação de testes de APIs utilizando:
</p>
<ul>
  <li><strong>Java 17</strong></li>
  <li><strong>Playwright</strong> para requisições HTTP</li>
  <li><strong>Cucumber</strong> para BDD e escrita de cenários</li>
  <li><strong>JUnit 5</strong> para execução dos testes</li>
  <li><strong>Allure</strong> para geração de relatórios detalhados</li>
  <li>Execução <strong>paralela</strong> de cenários para ganho de performance</li>
</ul>

<hr>

<h2>🚀 Começando</h2>

<h3>⚙️ Requisitos</h3>
<ul>
  <li>Java 17+</li>
  <li>Maven 3.9+</li>
  <li>Git</li>
</ul>

<h3>🗂️ Estrutura do Projeto</h3>
<pre>
📦 src
 ┣ 📂 main
 ┗ 📂 test
    ┣ 📂 java
    ┃  ┣ 📂 runner           # Runner dos testes
    ┃  ┣ 📂 steps            # Step Definitions (Cucumber)
    ┃  ┗ 📂 interactions     # Classes utilitárias de interação
    ┗ 📂 resources
       ┗ 📂 features         # Features do Cucumber
</pre>

<hr>

<h2>🛠️ Como Executar</h2>

<pre><code class="language-bash"># Clone o projeto
git clone &lt;seu-repositorio&gt;
cd java_playwright_api

# Execute os testes (os relatórios HTML e Allure serão gerados automaticamente)
mvn clean test
</code></pre>

<ul>
  <li>Os relatórios HTML estarão em: <code>target/cucumber/html/index.html</code></li>
  <li>O relatório do Allure pode ser servido via:
<pre><code class="language-bash">allure serve target/allure-results
</code></pre>
  </li>
</ul>

<hr>

<h2>💎 Principais Funcionalidades</h2>
<ul>
  <li><strong>Framework 100% orientado a boas práticas de QA</strong></li>
  <li>Configuração simples e objetiva para novos endpoints</li>
  <li>Geração de relatórios detalhados Allure e Cucumber</li>
  <li>Suporte a paralelismo de execução de testes</li>
  <li>Validação dinâmica de respostas, headers, status code e payloads</li>
</ul>

<hr>

<h2>🔍 Exemplos de Uso</h2>

<h3>Exemplo de cenário <code>.feature</code></h3>
<pre><code class="language-gherkin">Feature: Validação de Listagem de Usuários

  Scenario: Listar todos os usuários
    Given the base URL is "https://api.exemplo.com"
    And I set the request headers:
      | Content-Type | application/json |
    When I send a "GET" request to "/usuarios"
    Then the response status code should be 200
</code></pre>

<h3>Exemplo de Step Customizado</h3>
<p>
Os steps já implementados suportam:
<ul>
    <li>Definição dinâmica de URL base</li>
    <li>Adição de headers, parâmetros e corpo da requisição</li>
    <li>Validação de códigos de status e campos específicos no payload de resposta</li>
</ul>
</p>

<hr>

<h2>⚡ Configuração de Paralelismo</h2>
<p>
O projeto já está pronto para executar cenários em paralelo, otimizando o tempo de execução de grandes suítes de testes.<br>
Confira e ajuste a configuração em:
</p>
<ul>
  <li><code>src/test/resources/junit-platform.properties</code></li>
</ul>

<hr>

<h2>📚 Relatórios</h2>
<ul>
  <li><strong>Cucumber HTML:</strong> Navegue até <code>target/cucumber/html/index.html</code> após a execução.</li>
  <li><strong>Allure:</strong> Para abrir o relatório Allure, use o comando:
<pre><code class="language-bash">allure serve target/allure-results
</code></pre>
</li>
</ul>

<hr>
<h2>🚀 Instale o Allure CLI</h2>

<strong>macOS:</strong>
```bash
  brew install allure
```

<strong>Linux:</strong>
```bash
  sdk install allure
```
<span style="font-size:smaller;">Ou veja outras opções de instalação em <a href="https://docs.qameta.io/allure/#_installing_a_commandline" target="_blank">https://docs.qameta.io/allure/#_installing_a_commandline</a></span>
</p>

<p><strong>Windows:</strong><br>
Baixe o Allure no <a href="https://github.com/allure-framework/allure2/releases/latest" target="_blank">site oficial</a> e adicione a pasta <code>/bin</code> ao <strong>Path</strong> do sistema.
</p>

***

###  🏃‍♂️ Execução do TestRunner
Para garantir que o Allure Report seja gerado corretamente, é necessário executar os testes a partir da classe `TestRunner`. Isso assegura que todas as informações relevantes dos testes sejam coletadas e armazenadas nos resultados do Allure.

### 📄 Geração do Relatório
Após a execução dos testes, você pode gerar o relatório do Allure utilizando o seguinte comando no terminal:

```bash
  allure serve report PATH/allure-results
```

Para gerar o arquivo HTML do report basta executar no terminal o comando abaixo:
```bash
  allure generate allure-results --clean -o allure-report
```


<hr>

<h2>📝 Licença</h2>
<p>
Distribuído sob a licença MIT. Veja o arquivo <code>LICENSE</code> para mais informações.
</p>

</body>
</html>
