# MJV Projeto Final

#### Autor

- [Mateus Oliveira de Souza](https://github.com/mateuskih)

Repositório contendo o código fonte do projeto Ecommerce Venda de Jogos

##### Tecnologias

* Java
* JPA Hibernate
* Postgre SQL
* Spring Boot
* Swagger
* Vuejs
* Bootstrap

## Instalação em ambiente developer

Necessário [Eclipse](https://www.eclipse.org/downloads/) e  [Node.js](https://nodejs.org/)

Back-end
```sh
Import project eclipse
```

Front-end
```sh
/front
npm install
npm run serve
```

##### Endpoints
* Clientes
![](https://i.imgur.com/OokKQlD.png)
* Produtos
![](https://i.imgur.com/QNH3NdL.png)
* Categorias
![](https://i.imgur.com/FtwSmqz.png)
* Pedidos
![](https://i.imgur.com/HgqNiBC.png)

##### Apresentação

1. Cadastro de categorias
2. Cadastro de produtos
3. Cadastro de clientes
4. Inclusão de pedidos
5. Demonstração consumindo a API

##### JSON Cadastros

* Cadastrando um cliente Teste
```
{
  "nome": "Teste",
  "cpf": "706.432.060-60",
  "rg": "43.775.363-3",
  "email": "teste@teste.com",
  "senha": "teste",
  "whatsapp": 41998989898,
  "endereco": {
    "estado": "Parana",
    "cidade": "Curitiba",
    "logradouro": "das Flores",
    "numero": "8118",
    "bairro": "São Francisco"
  }
}
```

* Cadastrando uma Categoria
```
{
  "nome": "Futebol",
  "descricao": "Jogos físicos e digitais de futebol para todas as plataformas",
  "imagemUrl": "https://likebol.com/wp-content/uploads/2020/01/futebol-capas-1100594_widelg.png"
}
```

* Cadastrando um Produto
```
{
  "nome": "Pro Evolution Soccer 2016",
  "descricao": "Pro Evolution Soccer 2016, jogo de futebol em mídia física desenvolvido pela Konami para PlayStation 4",
  "preco": 99.99,
  "imagemUrl": "https://www.riosgames.com.br/image/cache/catalog/Capas-PS5/Esporte/Pes-2016-ps5-cover-793x1000.jpg",
  "categoriaId": 4
}

{
  "nome": "FIFA 16",
  "descricao": "FIFA 16 jogo de simulação de futebol lançado pela EA Sports para Microsoft Windows em mídia digital",
  "preco": 150.00,
  "imagemUrl": "https://www.fifauteam.com/wp-content/uploads/2015/07/A638-2.jpg",
  "categoriaId": 4
}
```

* Efetuando um login e pegando o token
```
{
  "email": "teste@teste.com",
  "senha": "teste"
}
```

* Criando um pedido e adicionando produtos com o token
```
{
  "produtoId": 9,
  "quantidade": 2
}

{
  "produtoId": 10,
  "quantidade": 1
}
```
* Visualizando o pedido pelo Token

* Setando o pedido como entregue
```
    status: entregue,
    token: ...
```

* Visualizando o pedido pelo Token


## License

MIT

**Free Software, Hell Yeah!**


