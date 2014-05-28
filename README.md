JogoDaForca
===========

Projeto Faculdade utilizando Socket + NetBeans + Java

<h3>Documentos .docx  explica como utilizar o sistema</h3>
<hr>

<h2>Projeto Jogo da Forca</h2>

Criar um jogo da forca, sendo que pode ser jogado entre 2 jogadores (clientes).<br>
O cliente seleciona se quer jogar somente com o computador ou com outro cliente.<br>
Caso o cliente escolha jogar somente com o computador , o computador será responsável por gerar palavras  a partir de um banco de dados pré- configurado , e o cliente tentará adivinhar a palavra.<br>
O banco será alimentado inicialmente por um conjunto de palavras. E cada palavra deverá conter pelo menos  uma dica.
Quando o cliente escolher jogar com outro “cliente” o cliente que conectou primeira irá criar uma palavra, e digitar uma dica sobre o que é aquela palavra. Essa dica será mostrada para o outro cliente e este tentará adivinhar qual a palavra que o outro cliente escolheu.<br>
Cada rodada o cliente poderá errar até 6 vezes (cabeça, corpo, perna direita, perna esquerda, braço esquerdo e braço direito).<br>
Caso o cliente acerte a palavra o cliente que acertou ganha 2 pontos.  E o  cliente que criou ganha 1 ponto. Caso erre ninguém pontua.<br>
A palavra digitada poderá conter no máximo 10 letras. <br>
A dica poderá ter tamanho máximo de 100 caracteres.<br>
Toda palavra criada pelo o usuário deverá ser salva no banco de dados para ser utilizado aleatoriamente novamente.<br>
