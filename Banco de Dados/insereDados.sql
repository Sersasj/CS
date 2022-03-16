USE sistemaOnibus;

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