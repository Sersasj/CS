USE sistemaOnibus;

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