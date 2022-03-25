drop schema if exists sistemaOnibus;
CREATE SCHEMA sistemaOnibus;
USE sistemaOnibus;

CREATE USER IF NOT EXISTS 'usuarioAdmin'@'localhost' IDENTIFIED BY 'senhaAdmin';
grant all privileges on sistemaonibus.* to 'usuarioAdmin'@'localhost';

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
    username VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    UNIQUE KEY (username),
    FOREIGN KEY (cpf)
        REFERENCES Funcionario (cpf),
    PRIMARY KEY (cpf)
);
    
CREATE TABLE Onibus (
    placa CHAR(7) NOT NULL,
    ano INT,
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
    id INT NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE LinhaPonto (
	id INT NOT NULL AUTO_INCREMENT,
    num_linha INT NOT NULL,
    id_ponto INT NOT NULL,
    FOREIGN KEY (num_linha)
        REFERENCES Linha (numero) ON DELETE CASCADE,
    FOREIGN KEY (id_ponto)
        REFERENCES Ponto (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE HorarioEsperado (
    id INT NOT NULL AUTO_INCREMENT,
    id_linha_ponto INT NOT NULL,
    horario TIME NOT NULL,
    FOREIGN KEY (id_linha_ponto)
        REFERENCES LinhaPonto (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE TipoProblema (
    id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    UNIQUE KEY (tipo),
    PRIMARY KEY (id)
);

CREATE TABLE TipoEmergencia (
    id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    UNIQUE KEY (tipo),
    PRIMARY KEY (id)
);
    
CREATE TABLE Corrida (
    id INT NOT NULL AUTO_INCREMENT,
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
    PRIMARY KEY (id)
);
        
CREATE TABLE Problema (
    id INT NOT NULL AUTO_INCREMENT,
    id_tipo INT NOT NULL,
    descricao TEXT,
    data_horario DATETIME NOT NULL,
    placa_onibus CHAR(7) NOT NULL,
    cpf_motorista VARCHAR(14) NOT NULL,
    FOREIGN KEY (id_tipo)
        REFERENCES TipoProblema (id),
    FOREIGN KEY (placa_onibus)
        REFERENCES Onibus (placa),
    FOREIGN KEY (cpf_motorista)
        REFERENCES Motorista (cpf),
    PRIMARY KEY (id)
);
    
CREATE TABLE Emergencia (
    id INT NOT NULL AUTO_INCREMENT,
    id_tipo INT NOT NULL,
    id_corrida INT NOT NULL,
    data_horario DATETIME NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    FOREIGN KEY (id_corrida)
        REFERENCES Corrida (id),
    FOREIGN KEY (id_tipo)
        REFERENCES TipoEmergencia (id),
    PRIMARY KEY (id)
);