<h1>desafiocar</h1>
<p>Prova prática Pitang</p>

<h1>ESTÓRIAS DE USUÁRIO:</h1>

<h2>1. Implementação das operações CRUD para usuários sem autenticação:</h2>
<p>Como desenvolvedor, desejo implementar as operações CRUD (Create, Read, Update, Delete) para usuários sem autenticação, de modo a permitir o cadastro, consulta, atualização e exclusão de usuários sem a necessidade de autenticação.</p>

<h2>2. Implementação da autenticação usando JWT:</h2>
<p>Como desenvolvedor, desejo implementar a autenticação usando JWT (JSON Web Token), de modo a fornecer um mecanismo seguro de autenticação para os usuários da aplicação.</p>

<h2>3. Implementação das operações CRUD para usuários autenticados:</h2>
<p>Como desenvolvedor, desejo implementar as operações CRUD para usuários autenticados, de modo a permitir o cadastro, consulta, atualização e exclusão de usuários apenas para usuários autenticados.</p>

<h2>4. Implementação das operações CRUD para carros (usuário autenticado):</h2>
<p>Como desenvolvedor, desejo implementar as operações CRUD para carros, de modo a permitir o cadastro, consulta, atualização e exclusão de carros apenas para usuários autenticados.</p>

<h2>5. Implementação da lógica de ordenação dos carros e usuários conforme o requisito extra:</h2>
<p>Como desenvolvedor, desejo implementar a lógica de ordenação dos carros e usuários conforme o requisito extra, de modo a ordenar os carros de um usuário de acordo com o total de utilizações e os usuários de acordo com o somatório total de utilizações de todos os seus carros.</p>

<h1>SOLUÇÃO:</h1>

<p>Minha solução para o projeto desafiocar é baseada em uma arquitetura de microserviços implementada em Java com o framework Spring Boot. A escolha dessas tecnologias foi feita levando em consideração a facilidade de desenvolvimento, a robustez do ecossistema Spring e a capacidade de escalabilidade e manutenção oferecida pela arquitetura de microserviços.</p>

<h2>Tecnologias Utilizadas:</h2>

<ul>
  <li><strong>Java 17:</strong> Utilizamos a versão 11 do Java como a linguagem principal de desenvolvimento devido à sua estabilidade, performance e suporte contínuo.</li>
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

