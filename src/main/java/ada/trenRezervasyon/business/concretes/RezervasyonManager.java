package ada.trenRezervasyon.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.RezervasyonService;
import ada.trenRezervasyon.business.abstracts.TrenService;
import ada.trenRezervasyon.entities.concretes.Tren;
import ada.trenRezervasyon.entities.dtos.RezervasyonInput;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;


@Service
public class RezervasyonManager implements RezervasyonService {

	private TrenService trenService;

	@Autowired
	public RezervasyonManager(TrenService trenService) {
		this.trenService = trenService;
	}

	public RezervasyonOutput KisileriYerlestir(RezervasyonInput rezervasyonInput) {
		RezervasyonOutput output;

		Tren tren = rezervasyonInput.getTren();
		int kisiSayisi = rezervasyonInput.getRezervasyonYapilacakKisiSayisi();

		if (!rezervasyonInput.isKisilerFarkliVagonlaraYerlestirilebilir()) {
			output = trenService.kisileriAyniVagonaYerlestir(tren, kisiSayisi);
		} else {
			output = trenService.kisileriFarkliVagonlaraYerlestir(tren, kisiSayisi);
		}

		return output;
	}

}
