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

CREATE TABLE LinhaPonto (
	id_linha_ponto INT NOT NULL AUTO_INCREMENT,
    num_linha INT NOT NULL,
    id_ponto INT NOT NULL,
    FOREIGN KEY (num_linha)
        REFERENCES Linha (numero) ON DELETE CASCADE,
    FOREIGN KEY (id_ponto)
        REFERENCES Ponto (id_ponto) ON DELETE CASCADE,
    PRIMARY KEY (id_linha_ponto)
);

CREATE TABLE HorarioEsperado (
    id_horario_esperado INT NOT NULL AUTO_INCREMENT,
    id_linha_ponto INT NOT NULL,
    horario TIME NOT NULL,
    FOREIGN KEY (id_linha_ponto)
        REFERENCES LinhaPonto (id_linha_ponto) ON DELETE CASCADE,
    PRIMARY KEY (id_horario_esperado)
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
    id_corrida INT NOT NULL AUTO_INCREMENT,
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
    id_problema INT NOT NULL AUTO_INCREMENT,
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
    id_alerta INT NOT NULL AUTO_INCREMENT,
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