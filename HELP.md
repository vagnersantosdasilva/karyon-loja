# Desafio Karyon 


## Build do projeto

mvn clean install -DskipTests=true

## EndPoints
- OBS :Todas as chamadas dos endpoints devem conter no header o atributo "Authorization" contendo o token de acesso no formato
Bearer 

### Obter uma empresa :

#### Método :GET
`http://localhost:8081/api/empresa/1`



### Obter todas as empresas

#### Método :GET
`http://localhost:8081/api/empresa`


### Criar uma nova empresa

#### Método :Post

`http://localhost:8081/api/empresa`

####Exemplo Body :

`{ "id": 0,    "cnpj": "12345678910127",    "nomeFantasia": "Nome fantasia7",    "razaoSocial": "Razão Social1",    "lojaFisicas": [ ] }`

### Atualizar um registro de empresa

#### Método :PUT

`http://localhost:8081/api/empresa`
#### Exemplo Body : 
{
    "nomeFantasia": "string",
    "razaoSocial": "string"
}

### Excluir uma empresa

#### Método DELETE

`http://localhost:8081/api/empresa/2`


### Obter uma loja física :
#### Método :GET
`http://localhost:8081/api/lojafisica/1`



### Obter todas as lojas físicas
#### Método :GET
`http://localhost:8081/api/lojafisica`


### Criar uma nova loja física

#### Método :Post

`http://localhost:8081/api/lojafisica`

#### Exemplo Body :

`{ "id":0, "loja": "xasdsd", "enderecoCompleto": "string", "contato": "string", "tel": "string", "cel": "string", "whatsapp": "string", "empresa": { "id": 4, "cnpj": "12345678913127", "nomeFantasia": "Nome fantasia7", "razaoSocial": "Razão Social1" }`

- OBS : Para criar uma nova loja fisica é necessário que já exista uma empresa para ser vinculada .

### Atualizar um registro de loja física

#### Método :PUT

`http://localhost:8081/api/lojafisica/4`

#### Exemplo Body :
`{
"loja": "string",
"enderecoCompleto": "string",
"contato": "string",
"tel": "string",
"cel": "string",
"whatsapp": "string"
}`

### Excluir uma loja física 

#### Método DELETE 

`http://localhost:8081/api/lojafisica/4`

