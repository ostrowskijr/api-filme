<div>
    <h1 align="center">API-Movies</h1>
</div>

## Sobre

- API-Movies - Possibilitar a leitura da lista de indicados e vencedores da
categoria Pior Filme do Golden Raspberry Awards.

## Tecnologias Utilizas
- [VScode](https://code.visualstudio.com/)
- [Spring-Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)

## Como Fazer Download do Projeto
- Para realizar download e execuão do projeto em sua máquina favor seguir os seguintes procedimentos.
```bash
    # No console de seu computador navegar até a pasta onde desejar fazer o download do projeto e executar o comando abaixo:
    $ get clone https://github.com/ostrowskijr/api-movies.git

    # Após finalizar o clone do projeto acessar a pasta do projeto
    $ cd /api-movies

    # Se estiver utilizando o Vscode basta executar o comando abaixo para abrir o projeto na IDE.
    $ code .

    # O próximo passo é realizar o Donwload das dependências do projeto do repositório maven.
    $ mvn install

    # Após a finalizar as instalção podemos executar o projeto com o comando abaixo no terminal.
    $ mvn spring-boot:run

    # Após o projeto subir no servidor o mesmo vai estar acessivel através da url abaixo:
    $ http://localhost:8080/
```
## Executando testes de Integração 
- Para executar os testes de integração do projetos basta seguir os passos descritos baixo:
```bash
    # Através do console é possivel executar os teste com o comando abaixo:
    $ mvn test
```
Será lançado o resultado do testes no terminal conforme exemplo abaixo:

<div>
    <img src="src/main/resources/img/test.png">    
</div>
Ao final do teste será exibido o resultado do total de testes executados, conforme imagem abaixo:

<div>
    <img src="src/main/resources/img/test1.png">    
</div>

## Acessando a Rota do Desafio proposto.
- O resultado do desafio proposto está acessivel através da rota abaixo http://localhost:8080/winners, pode ser acessado através do navegador ou aplicativo de teste API de sua preferência como Postman ou Insominia.
    - Segue abaixo resultado da chamda no Google Chrome, o imagem já vem formatada devido extensão instalado no navegador para exebição de dados em formato Json.
<div>
    <img src="src/main/resources/img/json.png">
</div>

### Desenvolvedor 
- Luis Antonio Ostrowski Jr - 05/2021.