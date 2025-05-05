<h1 align="center">Kafka</h1>

<p align="center">
  <img src="https://media1.tenor.com/m/_RiBHVVH-wIAAAAd/kafka-kafka-pat.gif" alt="Kafka"/>
</p>
<p align="center">
<b> bukan kafka yg ini</b>
</p>

# Cara pake

1. start servicesnya
    > docker compose up -d
2. buka `http://localhost:8181`
3. masukin angka terus calculate


atau kalo pake http client (postman, bruno, curl, dll) request body-nya:

```json
{
    "numbers": [1, 2, 3, 4, 5.6],
    "operation": "add" // "add", "subtract", "divide", "multiply"
}
```
terus send ke POST /api/send