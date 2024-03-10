<h1 align="center"> Tech Challenge Fase 2 - ParkingMeter </h1>

# Índice 

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Swagger](#swagger-disponibilizado)
* [Integrantes do Grupo](#pessoas-desenvolvedoras)


Segue o projeto desenvolvido para a pós-graduação FIAP em Arquitetura e desenvolvimento Java .Desafio proposto: refazer uma solução de parquímetro  utilizando conceitos abordados durante as aulas .
API de gerenciamento de vagas de estacionamento em uma cidade;

# 💻 Como rodar o projeto localmente
Este projeto foi escrito em java 21, portanto o usuário deverá ter esta versão do java instalada, o gerenciador de pacotes maven versão 3.2.3 e uma versão mais atualizada do docker, no caso usamos a versão 25.0.4. 
Passos:
1 - Instalar as dependẽncias com o maven
2 - Criar o container executando o comando `docker compose up -d` para iniciar o servidor mongo e criar um volume para o banco de dados ser salvo
3 - Rodar o projeto executando o comando `nvm spring-boot:run` ou com o método da sua escolha
Obs: Para ficar mais facil e acessivel rodar o projeto localmente optamos por não distribuir os dados em cluster, mas a ideia é que em produção ele seja criado desta forma para uma maior escalabilidade da aplicação.

# :hammer: Funcionalidades do projeto
- `Funcionalidade 1`: Nosso sistema esta apto a cadastrar os veículos (gerando assim um Id único ) deve ser informado a placa do seu veículo e e o nosso sitema deve gravara data  e hora em que o veículo foi cadastrado . Todos os campos são obrigatórios. 
- `Funcionalidade 2a`: Registro do período em que o veículo ficou estacionado e o usuario precisa fornecer o horário de saída para que  então siga á funcionalidade 3 . O horario de saída fornecido pelo usuário deverá ser ser salvo.
- `Funcionalidade 3`: Para realizar uma cobrança , nossos sitema precisa contabilizar o tempo em que o veículo ficou estacionado e o usuario precisa fornecer o horário de saída para que seja disponbilizado o preço a ser pago. O valor pago pelo usuário deverá ser ser salvo.

# Tecnologias Utilizadas
  - Mongo DB 
  - RabbitMQ 
  - Docker 
  - Java

# Link Swagger : 



# Integrantes 
 * Andre S Ferreira
 * Eduardo Vilhena
 * Giulliana Munhoz
 * Rafael Amaral
 * Tiago Santana
