## Tren Rezervasyon Uygulaması

*Bir tren rezervasyonu uygulaması için, istenilen rezervasyonunun yapılıp yapılamayacağını ve yapılabiliyorsa hangi vagon için rezervasyon onaylanabileceğini belirleyen HTTP API.*

Projeyi test etmek için,

- Projeye entegre [swagger](https://tren-rezervasyon.herokuapp.com/swagger-ui.html) kullanabilir,

- Yada bu [endpoint](https://tren-rezervasyon.herokuapp.com/api/reservations/reserve)'e harici bir uygulama ile post isteği atabilirsiniz.

### API'a istek için örnek bir değer

```json
{
  "kisilerFarkliVagonlaraYerlestirilebilir": true,
  "rezervasyonYapilacakKisiSayisi": 4,
  "tren": {
    "ad": "Ege Ekspresi",
    "vagonlar": [
      {
        "ad": "Vagon 1",
        "doluKoltukAdet": 30,
        "kapasite": 60
      },
      {
        "ad": "Vagon 2",
        "doluKoltukAdet": 40,
        "kapasite": 80
      }
    ]
  }
}
```

### API'ın gönderilen isteğe cevabı

```json
{
  "rezervasyonYapilabilir": true,
  "yerlesimAyrinti": [
    {
      "vagonAdi": "Vagon 2",
      "kisiSayisi": 2
    },
    {
      "vagonAdi": "Vagon 1",
      "kisiSayisi": 2
    }
  ]
}
```
