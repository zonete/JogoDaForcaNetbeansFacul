DROP TABLE IF EXISTS `palavras`;
CREATE TABLE `palavras` (
  `palavra` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `dica` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`palavra`,`dica`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

insert  into `palavras`(`palavra`,`dica`) values ('alicate','ferramenta'),('ATOMO','O QUE EXISTE DE MENOR'),('batedeira','utensilio de cozinha'),('beterraba','alimento'),('calopsita','AVE'),('cavalo','animal'),('CERVEJA','BEBIDA'),('chaves','seriado'),('harpa','instrumento musical'),('lesma','molusco'),('matinal','perÃ­odo'),('PANELA','COZINHA'),('peixeboi','mamifero'),('PERU','PAIS'),('pinga','agua pura'),('pularcorda','brincadeira'),('REALISMO','classe literÃ¡ria'),('SINGAPURA','CAPITAL DE PAÃS'),('sopa','comida'),('STEREOLOVE','Nome de uma mÃºsica'),('tequila','bebida');
