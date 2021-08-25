package ada.trenRezervasyon.business.abstracts;

import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

public interface TrainService {

	
	RezervasyonOutput placePeopleInSeats(Train train, int numberOfPeople, boolean canPeoplePlacedInDifferentWagons);
	
	RezervasyonOutput placePeopleInSameWagon(Train train, int numberOfPeople);

	RezervasyonOutput placePeopleInDifferentWagon(Train train, int numberOfPeople);
}
