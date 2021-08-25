package ada.trenRezervasyon.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.RezervasyonService;
import ada.trenRezervasyon.business.abstracts.TrainService;
import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.dtos.RezervasyonInput;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

@Service
public class RezervasyonManager implements RezervasyonService {

	private TrainService trenService;

	@Autowired
	public RezervasyonManager(TrainService trenService) {
		this.trenService = trenService;
	}

	public RezervasyonOutput reserve(RezervasyonInput rezervasyonInput) {
		RezervasyonOutput output;

		Train train = rezervasyonInput.getTren();
		int numberOfPeople = rezervasyonInput.getRezervasyonYapilacakKisiSayisi();
		boolean canPersonsPlacedInDifferentWagons = rezervasyonInput.isKisilerFarkliVagonlaraYerlestirilebilir();

		output = this.trenService.placePeopleInSeats(train, numberOfPeople, canPersonsPlacedInDifferentWagons);

		return output;
	}

}
