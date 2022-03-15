drop schema if exists sistemaOnibus;
CREATE SCHEMA sistemaOnibus;
USE sistemaOnibus;

CREATE TABLE Funcionario (
    cpf CHAR(14) NOT NULL,
    rg CHAR(12) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    telefone CHAR(13),
    endereco VARCHAR(50) NOT NULL,
    UNIQUE KEY (rg),
    PRIMARY KEY (cpf)
);

CREATE TABLE Motorista (
    cpf CHAR(14) NOT NULL,
    cnh CHAR(12) NOT NULL,
    UNIQUE KEY (cnh),
    FOREIGN KEY (cpf)
        REFERENCES Funcionario (cpf),
    PRIMARY KEY (cpf)
);

CREATE TABLE Administrador (
    cpf CHAR(14) NOT NULL,
    id_administrador INT NOT NULL,
    senha VARCHAR(20) NOT NULL,
    UNIQUE KEY (id_administrador),
    FOREIGN KEY (cpf)
        REFERENCES Funcionario (cpf),
    PRIMARY KEY (cpf)
);
    
CREATE TABLE Onibus (
    placa CHAR(7) NOT NULL,
    ano YEAR,
    quilometragem FLOAT,
    modelo VARCHAR(50),
    PRIMARY KEY (placa)
);

CREATE TABLE Linha (
    numero INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY (numero)
);

CREATE TABLE Ponto (
    id_ponto INT NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    PRIMARY KEY (id_ponto)
);

CREATE TABLE TipoProblema (
    id_tipo INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    UNIQUE KEY (tipo),
    PRIMARY KEY (id_tipo)
);

CREATE TABLE TipoEmergencia (
    id_tipo INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    UNIQUE KEY (tipo),
    PRIMARY KEY (id_tipo)
);
    
CREATE TABLE Corrida (
    id_corrida INT NOT NULL,
    cpf_motorista CHAR(14) NOT NULL,
    placa_onibus CHAR(7) NOT NULL,
    num_linha INT NOT NULL,
    inicio_corrida DATETIME NOT NULL,
    fim_corrida DATETIME NOT NULL,
    pass_nao_pagantes INT,
    pass_pagantes INT,
    consumo_combustivel FLOAT,
    distancia_percorrida FLOAT,
    FOREIGN KEY (cpf_motorista)
        REFERENCES Motorista (cpf),
    FOREIGN KEY (placa_onibus)
        REFERENCES Onibus (placa),
    FOREIGN KEY (num_linha)
        REFERENCES Linha (numero),
    PRIMARY KEY (id_corrida)
);
        
CREATE TABLE Problema (
    id_problema INT NOT NULL,
    id_tipo INT NOT NULL,
    descricao TEXT,
    data_horario DATETIME NOT NULL,
    placa_onibus CHAR(7) NOT NULL,
    cpf_motorista VARCHAR(14) NOT NULL,
    FOREIGN KEY (id_tipo)
        REFERENCES TipoProblema (id_tipo),
    FOREIGN KEY (placa_onibus)
        REFERENCES Onibus (placa),
    FOREIGN KEY (cpf_motorista)
        REFERENCES Motorista (cpf),
    PRIMARY KEY (id_problema)
);
    
CREATE TABLE Alerta (
    id_alerta INT NOT NULL,
    tipo INT NOT NULL,
    id_corrida INT NOT NULL,
    data_horario DATETIME NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    FOREIGN KEY (id_corrida)
        REFERENCES Corrida (id_corrida),
    FOREIGN KEY (tipo)
        REFERENCES TipoEmergencia (id_tipo),
    PRIMARY KEY (id_alerta)
);
        
CREATE TABLE HorarioEsperado (
    num_linha INT NOT NULL,
    id_ponto INT NOT NULL,
    horario TIME NOT NULL,
    FOREIGN KEY (num_linha)
        REFERENCES Linha (numero),
    FOREIGN KEY (id_ponto)
        REFERENCES Ponto (id_ponto),
    PRIMARY KEY (num_linha , id_ponto , horario)
);



INSERT INTO Funcionario VALUES ("111.111.111-11", "11.222.333-4", "Joao Lima", "44 99876-5432", "Av. Brasil, 111");
INSERT INTO Motorista VALUES ("111.111.111-11", "123123123123");
INSERT INTO Funcionario VALUES ("222.222.222-22", "22.333.444-5", "Artur Ferrari", "44 3210-3210", "R. Exemplo, 321");
INSERT INTO Motorista VALUES ("222.222.222-22", "234234234234");
INSERT INTO Funcionario VALUES ("333.333.333-33", "33.444.555-6", "Samir Helberto", null, "R. Generica, 999");
INSERT INTO Motorista VALUES ("333.333.333-33", "345345345345");

INSERT INTO Funcionario VALUES ("444.444.444-44", "44.555.666-7", "Gabriel Melo", "44 3322-1100", "R. Abacaxi, 4321");
INSERT INTO Administrador VALUES ("444.444.444-44", 101, 'egnirc');
INSERT INTO Funcionario VALUES ("555.555.555-55", "55.666.777-8", "Jose Silva", "44 99999-8888", "R. Banana II, 1234");
INSERT INTO Administrador VALUES ("555.555.555-55", 102, 'skate123');


insert into Onibus values("AAA1111", 2010, 15000.00, "Mercedes-Benz");
insert into Onibus values("BBB2222", 1990, 37032.12, "Volvo");
insert into Onibus values("CCC3333", 2000, 23456.78, "Mercedes-Benz");
insert into Onibus values("DDD4444", 2015, 10000.00, "Mercedes-Benz");
insert into Onibus values("EEE5555", 1995, 30000.00, "Volvo");


insert into Linha values(001, "Interbairros");
insert into Linha values(123, "Zona Norte");
insert into Linha values(321, "Zona Sul");
insert into Linha values(789, "Zona Leste");
insert into Linha values(987, "Zona Oeste");


insert into ponto values(1, -23.418595, -51.938139);
insert into ponto values(2, -23.399186, -51.926940);
insert into ponto values(3, -23.389879, -51.929730);
insert into ponto values(4, -23.403575, -51.934491);
insert into ponto values(5, -23.380627, -51.934024);
insert into ponto values(6, -23.432784, -51.928067);
insert into ponto values(7, -23.410662, -51.938222);


insert into tipoProblema values(1, "Motor");
insert into tipoProblema values(2, "Cambio de Marcha");
insert into tipoProblema values(3, "Eletrico");
insert into tipoProblema values(4, "Pneus");
insert into tipoProblema values(5, "Bateria");
insert into tipoProblema values(6, "Outros");


insert into tipoEmergencia values(1, "Assalto");
insert into tipoEmergencia values(2, "Emergencia Mecanica");
insert into tipoEmergencia values(3, "Outros");


insert into HorarioEsperado values(001, 1, '09:00:00');
insert into HorarioEsperado values(001, 1, '17:00:00');
insert into HorarioEsperado values(001, 4, '09:20:00');
insert into HorarioEsperado values(001, 4, '17:20:00');
insert into HorarioEsperado values(001, 7, '09:45:00');
insert into HorarioEsperado values(001, 7, '17:45:00');

insert into HorarioEsperado values(123, 1, '07:30:00');
insert into HorarioEsperado values(123, 1, '18:00:00');
insert into HorarioEsperado values(123, 2, '08:15:00');
insert into HorarioEsperado values(123, 2, '18:45:00');
insert into HorarioEsperado values(123, 3, '08:30:00');
insert into HorarioEsperado values(123, 3, '19:00:00');

insert into HorarioEsperado values(321, 1, '12:00:00');
insert into HorarioEsperado values(321, 6, '12:50:00');

insert into HorarioEsperado values(789, 1, '18:30:00');
insert into HorarioEsperado values(789, 5, '19:00:00');

insert into HorarioEsperado values(987, 1, '07:45:00');
insert into HorarioEsperado values(987, 1, '18:00:00');
insert into HorarioEsperado values(987, 7, '08:15:00');
insert into HorarioEsperado values(987, 7, '18:30:00');


insert into Corrida values(1003, "111.111.111-11", "AAA1111", 1, '2021-01-01 09:02:20', '2021-01-01 09:48:10', 30, 60, 1.28, 7.15);
insert into Corrida values(1005, "111.111.111-11", "AAA1111", 1, '2021-01-01 16:59:45', '2021-01-01 17:46:30', 40, 80, 1.34, 7.20);
insert into Corrida values(1006, "111.111.111-11", "BBB2222", 123, '2021-01-01 18:01:00', '2021-01-01 19:03:30', 45, 90, 1.30, 6.42);
insert into Corrida values(1001, "222.222.222-22", "BBB2222", 123, '2021-01-01 07:30:30', '2021-01-01 08:57:25', 27, 32, 1.21, 6.40);
insert into Corrida values(1004, "222.222.222-22", "CCC3333", 321, '2021-01-01 12:00:30', '2021-01-01 13:03:10', 35, 30, 1.14, 5.40);
insert into Corrida values(1008, "222.222.222-22", "DDD4444", 789, '2021-01-01 18:32:25', '2021-01-01 19:04:25', 30, 65, 1.58, 7.90);
insert into Corrida values(1002, "333.333.333-33", "EEE5555", 987, '2021-01-01 07:44:55', '2021-01-01 08:11:10', 20, 35, 1.06, 4.87);
insert into Corrida values(1007, "333.333.333-33", "EEE5555", 987, '2021-01-01 18:03:55', '2021-01-01 18:37:35', 35, 48, 1.08, 5.27);

insert into Problema values(1, 6, "Parachoque amassado", '2021-01-01 13:03:50', "CCC3333", "222.222.222-22");
insert into Problema values(2, 3, "Farol esquerdo falhando", '2021-01-01 19:04:30', "BBB2222", "111.111.111-11");
insert into Problema values(3, 4, "Pneus ficando carecas", '2021-01-01 19:05:15', "DDD4444", "222.222.222-22");

insert into Alerta values(1, 2, 1004, '2021-01-01 12:42:14', -23.425808, -51.938264);
insert into Alerta values(2, 1, 1007, '2021-01-01 18:23:25', -23.405614, -51.936603);


# 1 Consulta utilizando projeção
# Esta consulta seleciona o nome e o CPF de todos os funcionários.
SELECT 
    nome, cpf
FROM
    Funcionario;


# 2 Consulta utilizando seleção e projeção
# Esta consulta seleciona o CPF do funcionário que tem o nome Joao Lima.
SELECT 
    cpf
FROM
    Funcionario
WHERE
    nome = 'Joao Lima';


# 3 Consulta que aplica a união (UNION) entre duas relações
# Esta consulta faz a união da CNH dos motoristas com o id dos administradores.
SELECT 
    cnh
FROM
    Motorista 
UNION SELECT 
    id_administrador
FROM
    Administrador;


# 4 Consulta que aplica a diferença (EXCEPT) entre duas relações
# Esta consulta mostra somente o CPF dos motoristas que não submeteram problemas.
SELECT 
    cpf
FROM
    Motorista
WHERE
    NOT EXISTS( SELECT 
            cpf_motorista
        FROM
            Problema
        WHERE
            Motorista.cpf = Problema.cpf_motorista);


# 5 Consulta que aplica a intersecao (INTERSECT) entre duas relações
# Esta consulta retorna o id dos pontos em que ambas as linhas 001 e 987 passam.
SELECT 
    cpf
FROM
    Motorista
WHERE
    EXISTS( SELECT 
            cpf_motorista
        FROM
            Problema
        WHERE
            Motorista.cpf = Problema.cpf_motorista);


# 6 Consulta que faz uso de uma subconsulta que retorna um único valor
# Esta consulta retorna o nome do funcionário que submeteu o problema com id 1.
SELECT 
    nome
FROM
    Funcionario
WHERE
    cpf IN (SELECT 
            cpf_motorista
        FROM
            Problema
        WHERE
            id_problema = 1);


# 7 - Consultas que utilizam sub consultas que retornam um conjunto de valores utilizando:
# a) IN ou NOT IN 
# Esta consulta retorna o nome dos funcionários que iniciaram uma corrida entre as 7h e 8h.
SELECT 
    nome
FROM
    Funcionario
WHERE
    cpf IN (SELECT 
            cpf_motorista
        FROM
            Corrida
        WHERE
            inicio_corrida BETWEEN '2021-01-01 07:00:00' AND '2021-01-01 08:00:00');


# 7 - b) any
# Esta consulta retorna o número das linhas das corridas que tiveram mais de 50 passageiros pagantes.
SELECT 
	num_linha 
FROM 
	Corrida
WHERE id_corrida = ANY (SELECT id_corrida FROM Corrida WHERE pass_pagantes > 50);


# 7 - c) EXIST ou NOT EXIST 
# Esta consulta retorna o CPF dos motoristas das corridas em que foram emitidas emergências.
SELECT 
    id_corrida
FROM
    Corrida
WHERE
    EXISTS( SELECT 
            id_corrida
        FROM
            Alerta
        WHERE
            Corrida.id_corrida = Alerta.id_corrida);


# 7 - d) All
# Esta consulta retorna o id das corridas que tiveram número de passageiros pagantes acima da média.
SELECT 
	id_corrida 
FROM 
	Corrida
WHERE pass_pagantes > all (SELECT avg(pass_pagantes) FROM Corrida);


# 8 - Consulta que faz uso de uma subconsulta que retorna duas colunas
# Esta consulta retorna o modelo e o ano dos ônibus que tiveram um problema.
SELECT 
    modelo, ano
FROM
    Onibus
WHERE
    placa IN (SELECT 
            placa_onibus
        FROM
            Problema);


# 9 - Consulta que utiliza uma junção externa à direita
# Esta consulta o id de todas as corridas registradas, junto ao nome e cpf dos motoristas dessas corridas.
SELECT 
    nome, cpf, Corrida.id_corrida
FROM
    Funcionario
        RIGHT OUTER JOIN
    Corrida ON Funcionario.cpf = Corrida.cpf_motorista;


# 10 - Consulta que utiliza uma junção externa à esquerda
# Esta consulta retorna nome e cpf de todos os funcionários junto ao id de todas as corridas que foram feitas
SELECT 
    nome, cpf, Corrida.id_corrida
FROM
    Funcionario
        LEFT OUTER JOIN
    Corrida ON Funcionario.cpf = Corrida.cpf_motorista;


# 11 - Consulta que utiliza uma ou mais funções de agregação
# Esta consulta retorna o total de passageiros pagantes e não pagantes das corridas registradas.
SELECT 
    SUM(pass_pagantes), SUM(pass_nao_pagantes)
FROM
    Corrida;


# 12 - Consulta que faz uso de uma funções de agregação com cláusula GROUP BY
# Esta consulta retorna a média da distância percorrida por linha.
SELECT 
    num_linha, avg(distancia_percorrida)
FROM
    Corrida
GROUP BY num_linha;


# 13 - Consulta que faz uso de duas função de agregação com cláusula GROUP BY
# Esta consulta retorna o total de passageiros pagantes e não pagantes por linha.
SELECT 
    num_linha, SUM(pass_pagantes), SUM(pass_nao_pagantes)
FROM
    Corrida
GROUP BY num_linha;


# 14 - Consulta que faz uso de uma função agregada com cláusula GROUP BY e HAVING
# Esta consulta retorna o número da linha e a média de passageiros pagantes das linhas que tiveram uma média de passageiros pagantes maior que 50.
SELECT 
    num_linha, avg(pass_pagantes)
FROM
    Corrida
GROUP BY num_linha
having (avg(pass_pagantes) > 50);


# 15 - Consulta utilizando uma junção interna (duas relações envolvidas na consulta)
# Esta consulta retorna todas as informações dos administradores.
SELECT 
    *
FROM
    Administrador
        INNER JOIN
    Funcionario ON Funcionario.cpf = Administrador.cpf;

# 16 - Consulta utilizando uma junção interna (três relações envolvidas na consulta)
# Esta consulta retorna o tipo e descrição de todos os problemas registrados, junto com a placa e modelo dos ônibus associados.
SELECT 
    TipoProblema.tipo,
    Problema.descricao,
    Onibus.placa,
    Onibus.modelo
FROM
    ((TipoProblema
    INNER JOIN Problema ON Problema.id_tipo = TipoProblema.id_tipo)
    INNER JOIN Onibus ON Onibus.placa = Problema.placa_onibus);


# 17 - Consulta utilizando uma junção interna (quatro relações envolvidas na consulta)
# Esta consulta retorna o nome e telefone dos motoristas envolvidos em uma emergência, junto à localização e a placa do ônibus associados à emergência.
SELECT 
    Funcionario.telefone,
    Funcionario.nome,
    Corrida.placa_onibus,
    Alerta.latitude,
    Alerta.longitude
FROM
    (((Funcionario
    INNER JOIN Motorista ON Funcionario.cpf = Motorista.cpf)
    INNER JOIN Corrida ON Motorista.cpf = Corrida.cpf_motorista)
    INNER JOIN Alerta ON Alerta.id_corrida = Corrida.id_corrida);