## Is Mutant


## Desafio 1 - Identificar sequência mutante

A identificação da sequencia de DNA é feita com Regex, tanto para a validação do sequencia correta quanto para identificar uma sequência inválida de caracteres.

O primeiro passo do algoritimo veifica  se no input do algoritimo existe algum caracter inválido utilizando o regex abaixo.
  
```
/(?!A|C|T|G)([A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])/g
```

Após verificar que todos os dados fornecidos são válidos o algoritimo verifica se existe a sequencia de DNA mutant na seguinte sequencia, horizontal, vertical e por fim na diagonal principal utilizando a Regex abaixo. 
```
/(C{4}|A{4}|T{4}|G{4})/g
```

## Desafios  2 e 3 

### Tecnologias escolhidas

- Spring Boot 2
- Java 8
- Spring Data
- MongoDB
- Docker
- Docker Compose
- EC2 (AWS)
- Route 53 (AWS)

### Arquitetura do sistema

A arquitetura do sistema utiliza os seguintes elementos 

- Aplicação Java Spring Boot  
- Banco de dados MongoDB
- Docker para empacotamento 
- Docker Compose para orquestração, documentação e gerenciamento do ambiente da aplicação
- Criação de uma Instancia EC2  para rodar o sistema
- Route 53 para routear um subdominio para a API

Para consumir a aplicação basta executa uma requisição REST método POST e GET nos endpoints abaixo

```
 http://api.raphaelinacio.com/mutant
 http://api.raphaelinacio.com/stats
```

## Executando a aplicação localmente

###  Dependências

Para executar essa aplicação é necessário instalar as seguintes ferramentas
- GIT
- Java 1.8
- Maven
- Docker
- Docker Compose

Após ter instalado com sucesso as dependências acima basta basta seguir os passos abaixo

 1. Clonar o repositório do projeto

```
sudo git clone https://github.com/raphaelInacio/is-mutant.git
```

 2. Criar um artefato maven

```
sudo mvn clean package
```

 3. Subir o ambiente da aplicação através do docker-compose

```
sudo docker-compose up 
```

3. Testar a API
```
 http://localhost:8080/stats
 http://localhost:8080/mutant
```


