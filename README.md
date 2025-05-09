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
yg buat list ada di /api/list

# Alur kerja

1. client connect ke websocket /ws
2. client send data ke /api/send
3. [producer 1](/src/main/java/training/kafka/final_task/kafka/producer/SesuatuProducer.java#L27) dapet data dari client, [kirim ke topic `sesuatu`](/src/main/java/training/kafka/final_task/controller/ApiController.java#L30)
4. [consumer 1](/src/main/java/training/kafka/final_task/kafka/consumer/SesuatuConsumer.java#L30) baca dari topik `sesuatu`, [hitung hasil dan simpan ke DB](/src/main/java/training/kafka/final_task/kafka/consumer/SesuatuConsumer.java#L40)
5. [producer 2](/src/main/java/training/kafka/final_task/kafka/producer/SesuatuProducer.java#L41) dapet info kalo ada data baru, [kirim ke topic `results`](/src/main/java/training/kafka/final_task/kafka/consumer/SesuatuConsumer.java#L43)
6. [consumer 2](/src/main/java/training/kafka/final_task/kafka/consumer/SesuatuConsumer.java#L51) baca dari topic `results`, kirim hasil terbaru ke client pake websocket
7. data di client updated ✅