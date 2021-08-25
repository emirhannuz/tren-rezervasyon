## Tren Rezervasyon Uygulaması

*Bir tren rezervasyonu uygulaması için, istenilen rezervasyonunun yapılıp yapılamayacağını ve yapılabiliyorsa hangi vagon için rezervasyon onaylanabileceğini belirleyen **spring framework** ile geliştirilmiş HTTP API.*

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

### Gereksinimler

- [Bir tren içinde birden fazla vagon bulunabilir.](./img/1.png)
- [Her vagonun farklı kişi kapasitesi olabilir.](./img/2.png)
- [Online rezervasyonlarda, bir vagonun doluluk kapasitesi %70'i geçmemelidir. Yani vagon kapasitesi 100 ise ve 70 koltuk dolu ise, o vagona rezervasyon yapılamaz.](./img/3.png)
- [Bir rezervasyon isteği içinde birden fazla kişi olabilir.](./img/5.png)
-  [Rezervasyon isteği yapılırken, kişilerin farklı vagonlara yerleşip yerleştirilemeyeceği belirtilir. Bazı rezervasyonlarda tüm yolcuların aynı vagonda olması istenilirken, bazılarında farklı vagonlar da kabul edilebilir.](./img/5.png)
- [Rezervasyon yapılabilir durumdaysa, API hangi vagonlara kaçar kişi yerleşeceği bilgisini dönecektir.](./img/6.png)

---

 * Java version 11
 * Spring Boot version 2.5.4
