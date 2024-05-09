# cria alguns departamentos e funcionários
curl -d '{"nome":"RH"}' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos
curl -d '{"nome":"Vendas"}' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos
curl -d '{"nome":"Engenharia"}' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos
curl -d '{"nome":"Fulano", "salario":8000 }' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos/0/funcionarios
curl -d '{"nome":"Sicrano", "salario":9500 }' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos/1/funcionarios
curl -d '{"nome":"Beltrano", "salario":9000 }' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos/1/funcionarios
curl -d '{"nome":"Maria", "salario":10000 }' \
    -H 'Content-Type: application/json' http://localhost:8080/departamentos/2/funcionarios
