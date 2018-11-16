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

### Arquitetura do sistema

![enter image description here](https://github.com/raphaelInacio/is-mutant/blob/master/arquitetura.jpg)

A arquitetura do sistema utiliza os seguintes elementos 

- Spring Boot 2
- Java 8
- Spring Data
- MongoDB
- Docker
- Docker Compose
- AWS
- EC2 (AWS)
- Route 53 (AWS)

A aplicação expõe dois endpoins conforme demonstrado abaixo

```
 POST: http://api.raphaelinacio.com/mutant
 GET: http://api.raphaelinacio.com/stats
```

## Executando a aplicação localmente

###  Dependências

Para executar a aplicação localmente é necessário instalar as seguintes dependências

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
 GET: http://localhost/stats
 POST: http://localhost/mutant
```


