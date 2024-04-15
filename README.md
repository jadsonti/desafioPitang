<h1>Desafio Pitang</h1>
<p>Prova prática Pitang</p>
<h2>Importante, assista ao vídeo!</h2>
<iframe src="https://player.vimeo.com/video/934681608?h=e03027af67" width="640" height="360" frameborder="0" allow="autoplay; fullscreen; picture-in-picture" allowfullscreen></iframe>
<p><a href="https://vimeo.com/934681608">Jadson Jos&eacute; - Desafio Pitang Java</a> from <a href="https://vimeo.com/user218165605">Jadson Jos&eacute;</a> on <a href="https://vimeo.com">Vimeo</a>.</p>


<h1>Execute o projeto em sua máquina, realizando os passos:</h1>
<b>1. Pré-requisitos</b><br>
Antes de começar, você precisará ter instalado em sua máquina:<br>
<ul>
<li>Git;</li> 
<li>Java (para o back-end, versão Java 21);</li> 
<li>Maven (para gerenciar dependências do back-end);</li> 
<li>Node.js e npm (para o front-end);</li> 
<li>Angular CLI (para o front-end) Angular 10;</li> 
<li>Um IDE ou editor de texto de preferência, como IntelliJ IDEA para Java e Visual Studio Code para Angular.</li> 
</ul>

<b>2. Clonando os Repositórios</b><br>
Clonar o Repositório Back-end<br>
<ul>
<li>Abra o terminal.</li> 
<li>Navegue até o diretório onde deseja clonar o projeto.</li> 
<li>Execute o comando:</li> 
<li>git clone https://github.com/jadsonti/desafioPitang.git</li>
</ul>

Clonar o Repositório Front-end
<ul>
<li>Ainda no terminal, navegue até o diretório onde deseja clonar o projeto front-end.</li> 
<li>Execute o comando:</li> 
<li>git clone https://github.com/jadsonti/desafioPitang-web.git</li>
</ul>

<b>3. Configurando e Executando o Back-end</b><br>
Navegue até a pasta do projeto clonado do back-end:

<i>cd [nome-da-pasta-back-end]</i>

Execute o Maven para instalar as dependências e construir o projeto:<br>
comandos:
<ul>
<li>mvn clean install</li>
<li><b>Inicie a aplicação:</b></li>
<li>mvn spring-boot:run</li>
Isso irá iniciar o servidor back-end, geralmente acessível via http://localhost:8080.
</ul>

<b>4. Configurando e Executando o Front-end</b><br>
Navegue até a pasta do projeto clonado do front-end:

<i>cd [nome-da-pasta-front-end]</i>

Execute o Maven para instalar as dependências e construir o projeto:<br>
comandos:
<ul>
<li>mvn clean install</li>
<li><b>Inicie a aplicação:</b></li>
<li>ng serve</li>
Por padrão, isso disponibilizará o front-end no endereço http://localhost:4200.
</ul>

<b>Acesso ao banco de dados</b><br>
link: <i>http://localhost:8080/h2-console</i><br>
<b>usuario: root</b><br>
<b>senha: jadson</b>

<h1>ESTÓRIAS DE USUÁRIO:</h1>

<h2>1. Implementação das operações CRUD para usuários sem autenticação:</h2>
<p>Como desenvolvedor, desejo implementar as operações CRUD (Create, Read, Update, Delete) para usuários sem autenticação, de modo a permitir o cadastro, consulta, atualização e exclusão de usuários sem a necessidade de autenticação.</p>
<p><b>Critérios de aceitação:</b></p>
<ul>
<li>A API deve permitir a criação de novos usuários sem exigir autenticação.</li> 

<li>A API deve permitir a consulta de usuários existentes sem exigir autenticação.</li> 

<li>A API deve permitir a atualização de informações de usuários existentes sem exigir autenticação.</li> 

<li>A API deve permitir a exclusão de usuários existentes sem exigir autenticação.</li> 

<li>A aplicação deve validar os campos necessários e formatos de dados ao criar ou atualizar usuários.</li> 

<li>A aplicação deve fornecer mensagens de erro claras para operações inválidas ou dados inválidos.</li> 
</ul>

<h2>2. Implementação da autenticação usando JWT:</h2>
<p>Como desenvolvedor, desejo implementar a autenticação usando JWT (JSON Web Token), de modo a fornecer um mecanismo seguro de autenticação para os usuários da aplicação.</p>
<p><b>Critérios de aceitação:</b></p>
<ul>
<li>A autenticação deve ser realizada por meio de um token JWT gerado após o login bem-sucedido.</li>  

<li>O token JWT deve ser necessário para acessar rotas que requerem autenticação.</li> 

<li>A aplicação deve validar a autenticidade e a validade do token JWT em cada solicitação a uma rota autenticada.</li>  

<li>Usuários com tokens inválidos ou expirados devem receber uma mensagem de erro de autenticação.</li>  

<li>O sistema deve armazenar de forma segura as senhas dos usuários.</li>  
</ul>

<h2>3. Implementação das operações CRUD para carros (usuário autenticado):</h2>
<p>Como desenvolvedor, desejo implementar as operações CRUD para carros, de modo a permitir o cadastro, consulta, atualização e exclusão de carros apenas para usuários autenticados.</p>
<p><b>Critérios de aceitação:</b></p>
<ul>
<li>Apenas usuários autenticados podem realizar operações CRUD em carros.</li>  

<li>Cada carro deve estar associado a um usuário específico, e operações CRUD em um carro só podem ser realizadas pelo usuário a quem o carro pertence.</li>  

<li>A aplicação deve validar a unicidade da placa do carro para cada usuário.</li>   

<li>A aplicação deve fornecer mensagens de erro apropriadas para tentativas de operações inválidas.</li>   
</ul>

<h2>4. Implementação da lógica de ordenação dos carros e usuários conforme o requisito extra:</h2>
<p>Como desenvolvedor, desejo implementar a lógica de ordenação dos carros e usuários conforme o requisito extra, de modo a ordenar os carros de um usuário de acordo com o total de utilizações e os usuários de acordo com o somatório total de utilizações de todos os seus carros.</p>
<p><b>Critérios de aceitação:</b></p>
<ul>
<li>A aplicação deve fornecer uma funcionalidade para rastrear o número de vezes que cada carro é utilizado.</li>    

<li>Ao listar carros de um usuário, a aplicação deve ordená-los pelo total de utilizações, do mais usado para o menos usado.</li>    

<li>Ao listar usuários, a aplicação deve ordená-los pelo somatório total de utilizações de todos os seus carros, do mais ativo para o menos ativo.</li>    

<li>A aplicação deve fornecer feedback adequado e resultados precisos, refletindo a ordenação correta baseada em utilizações.</li>   
</ul>

<h1>SOLUÇÃO:</h1>

<p>Minha solução para o projeto desafiocar é baseada em uma arquitetura de microserviços implementada em Java com o framework Spring Boot. A escolha dessas tecnologias foi feita levando em consideração a facilidade de desenvolvimento, a robustez do ecossistema Spring e a capacidade de escalabilidade e manutenção oferecida pela arquitetura de microserviços.</p>

<h2>Tecnologias Utilizadas:</h2>

<ul>
  <li><strong>Java 21:</strong> Utilizamos a versão 21 do Java como a linguagem principal de desenvolvimento devido à sua estabilidade, performance e suporte contínuo.</li>
  <li><strong>Spring Boot:</strong> Optei pelo Spring Boot como o framework de desenvolvimento devido à sua facilidade de configuração, ampla gama de funcionalidades integradas (como segurança, persistência de dados, etc.) e suporte a padrões de projeto como injeção de dependência e inversão de controle.</li>
  <li><strong>Spring Data JPA:</strong> Utilizei o Spring Data JPA para facilitar o acesso e manipulação dos dados do banco de dados H2. Isso nos permite escrever consultas de forma mais simples e eficiente, além de oferecer integração transparente com o Spring Boot.</li>
  <li><strong>H2 Database:</strong> Escolhi o H2 como o banco de dados em memória para simplificar o desenvolvimento e os testes, garantindo que não seja necessário configurar um banco de dados externo durante o desenvolvimento.</li>
  <li><strong>Maven:</strong> Utilizei o Maven como gerenciador de dependências e para automatizar o processo de build do projeto, garantindo consistência e reprodutibilidade na construção da aplicação.</li>
</ul>

<h2>Padrões de Projeto:</h2>

<ul>
  <li><strong>Arquitetura de Microserviços:</strong> Optei por uma arquitetura de microserviços para garantir a modularidade, escalabilidade e flexibilidade da nossa aplicação. Cada estória de usuário será implementada como um microserviço separado, o que nos permite desenvolver, testar e implantar cada funcionalidade de forma independente.</li>
  <li><strong>Padrão MVC (Model-View-Controller):</strong> Adotei o padrão MVC para separar a lógica de negócios (Model), a apresentação (View) e o controle (Controller) da nossa aplicação. Isso nos permite manter um código mais organizado e coeso, facilitando a manutenção e extensão do sistema.</li>
  <li><strong>Padrão Repository:</strong> Implementei o padrão Repository para encapsular a lógica de acesso aos dados, garantindo uma separação clara entre a camada de persistência e as outras camadas da aplicação.</li>
</ul>

<p>Essa solução nos permite desenvolver uma aplicação robusta, escalável e de fácil manutenção, que atende aos requisitos funcionais e não funcionais do projeto desafiocar.</p>

