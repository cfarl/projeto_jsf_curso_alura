
-- -------------------------------------------------------
-- Cria tabelas usadas pela aplicacao
-- -------------------------------------------------------
CREATE TABLE usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE autor (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

CREATE TABLE livro (
  id int NOT NULL AUTO_INCREMENT,
  dataLancamento date DEFAULT NULL,
  isbn varchar(255) DEFAULT NULL,
  preco double NOT NULL,
  titulo varchar(255) DEFAULT NULL,
  genero varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE livro_autor (
  Livro_id int NOT NULL,
  autores_id int(11) NOT NULL,
  CONSTRAINT FK9590997AD70C7E4A FOREIGN KEY (autores_id) REFERENCES autor (id),
  CONSTRAINT FK9590997AFA17418 FOREIGN KEY (Livro_id) REFERENCES livro (id)
) ;


-- -------------------------------------------------------
-- Carrega dados nas tabelas usadas pela aplicacao
-- -------------------------------------------------------
INSERT INTO usuario (email,senha) VALUES
	 ('luiz.curado@gmail.com','123');

INSERT INTO livro (dataLancamento,isbn,preco,titulo,genero) VALUES
	 ('1899-01-10','978-8-52-504464-8',24.9,'Dom Casmurro',NULL),
	 ('1881-01-01','978-8-50-815415-9',19.9,'Memorias Postumas de Bras Cubas',NULL),
	 ('1891-01-01','978-8-50-804084-1',16.9,'Quincas Borba',NULL),
	 ('1988-01-01','978-8-57-542758-3',19.9,'O Alquimista',NULL),
	 ('1990-01-01','978-8-50-567258-7',12.9,'Brida',NULL),
	 ('1992-01-01','978-8-52-812458-8',29.9,'Uma Noite na Taverna',NULL),
	 ('1987-01-01','978-8-51-892238-9',9.9,'O Diario de um Mago',NULL),
	 ('1937-01-01','978-8-50-831169-1',6.9,'Capitaes da Areia',NULL),
	 ('1966-01-01','978-8-53-592569-9',18.9,'Dona Flor e Seus Dois Maridos',NULL);

	 INSERT INTO autor (nome,email) VALUES
	 ('Machado de Assis',NULL),
	 ('Jorge Amado',NULL),
	 ('Paulo Coelho','paulo@gmail.com'),
	 ('Clarice Linspector',NULL),
	 ('Álvares Azevedo',NULL) ;

	 INSERT INTO livro_autor (Livro_id,autores_id) VALUES
	 (1,1),
	 (2,1),
	 (3,1),
	 (4,3),
	 (5,3),
	 (6,3),
	 (7,3),
	 (8,2),
	 (9,2);

