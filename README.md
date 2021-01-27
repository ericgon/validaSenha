# ValidaSenha-api

Uma API em Java e SpringBoot para validação de padrão de senha.

## Como a API deve funcionar?

Essa API recebe uma senha via método POST e retorna TRUE caso a senha atenda aos requisitos ou FALSE caso não atenda.
 
`POST/v1/senha` - Valida uma senha. 

Estrutura de entrada
**Body:**

<code>
{
"senha":"Senha123#",
}
</code>

**Where:**

`senha`: senha a ser validada;

Deve retornar com body com true ou false conforme a validação:

## Executando a aplicação

Para executar a aplicação é necessário baixar o código do repositório https://github.com/ericgon/validaSenha.git
Importar como projeto Maven no eclipse
Dentro do eclipse executar como um projeto SpringBoot
Abrir o Postman e realizar uma chamada POST na url: http://localhost:8080/v1/senha 
Metodo Post com o Body: 
{
    "senha":"1Asdehoiu87"
}


## Lógica de Construção

A validação da Senha ocorre de forma separada para cada regra, tornando o código mais legível, de manutenção mais fácil. Novas regras podem ser inseridas ou removidas com mais rapidez e facilidade seguindo assim o conceito SOLID. Caso tudo fosse validado em um único REGEX por exemplo a leitura do código ficaria comprometida assim como sua manutenção e melhorias futuras.

Antes de iniciar a validação é removido os espaços da senha recebida.

A primeira validação é o tamanho da senha, caso o tamanho seja inferior a 9 não é necessário gastar tempo e processamento com o restante das validações.

Algumas validações são feitas individualmente via REGEX (1 letra maiúscula, 1 letra minúscula, 1 caracter numérico).

Para validar se a senha tem pelo menos um dos elementos do conjunto de caracter especial foi utilizado uma lista com todos os caracteres especiais. A senha é percorrida char a char e no primeiro resultado positivo é retornado TRUE não sendo necessário percorrer o restante da senha.

Para validar se existe algum caracter repetido foi utilizado um TreeMap onde cada char da senha é uma chave. Enquanto a senha é percorrida se for encontrado uma chave repetida ou seja um valor repetido a função já retorna false não sendo necessário validar o restante da senha. Durante essa validação as letras são convertidas para minúscula sempre. 
Não estava claro se era permitido a mesma letra minúscula e maiúscula na senha portanto assumi que não seria permitido pois entendi que o objetivo era ter uma senha o mais forte possível sempre.

Após a validação do tamanho da senha todas as outras validações são chamadas na mesma sentença, caso qualquer uma delas retorne false o retorno final será false, caso todas retornem true, o retorno final será true.

