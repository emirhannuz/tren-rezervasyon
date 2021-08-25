package ada.trenRezervasyon.business.abstracts;

import ada.trenRezervasyon.entities.concretes.Tren;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

public interface TrenService {

	RezervasyonOutput kisileriAyniVagonaYerlestir(Tren tren, int kisiSayisi);

	RezervasyonOutput kisileriFarkliVagonlaraYerlestir(Tren tren, int kisiSayisi);
}
