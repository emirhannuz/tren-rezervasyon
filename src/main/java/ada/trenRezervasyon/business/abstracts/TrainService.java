package ada.trenRezervasyon.business.abstracts;

import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.dtos.ReservationOutput;

public interface TrainService {

	DataResult<ReservationOutput> placePeopleInSeats(Train train, int numberOfPeople,
			boolean canPeoplePlacedInDifferentWagons);


}
