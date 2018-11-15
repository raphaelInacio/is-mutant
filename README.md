## Is Mutant


## Solução

A identificação da sequencia de DNA é feita com Regex, tanto para a validação do sequencia correta quanto para identificar uma sequência inválida de caracteres.

O primeiro passo do algoritimo veifica  se no input do algoritimo existe algum caracter inválido utilizando o regex abaixo.
  
```
/(?!A|C|T|G)([A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])/g
```

Após verificar que todos os dados fornecidos são válidos o algoritimo verifica se existe a sequencia de DNA mutant na seguinte sequencia, horizontal, vertical e por fim na diagonal principal utilizando a Regex abaixo. 
```
/(C{4}|A{4}|T{4}|G{4})/g
```

## Tecnologias escolhidas
- Spring Boot 2
- Java 8
- Spring Data
- MongoDB
- Docker
- Docker Compose

## Arquitetura

A arquitetura do sistema é composto por 3 elementos
- Aplicação Java Spring Boot com tomcat 
- Banco de dados MongoDB
- Docker para empacotamento 
- Docker Compose para orquestração, documentação e gerenciamento do ambiente da aplicação

## Executando a aplicação

###  Dependências

Para executar essa aplicação é necessário instalar as seguintes ferramentas
- Docker
- Docker Compose
- GIT

Após ter instalado com sucesso as dependências acima basta basta seguir os passos abaixo

 1. Clonar o repositório do projeto

```
git clone ....
```

 2. Dentro do repositório do projeto executar o docker-compose

```
docker-compose up 
```

3. Testar a API
```
curl -X POST \
  http://localhost:8080/mutant \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: f2d5f631-ddd1-283f-927c-769a9cbc0fd8' \
  -d '{"dna" : ["ATTAG",
        "ATTAG",
        "ATTCA",
        "ATCCA" ]}'
```


