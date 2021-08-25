package ada.trenRezervasyon.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.ReservationService;
import ada.trenRezervasyon.business.abstracts.TrainService;
import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.dtos.ReservationInput;
import ada.trenRezervasyon.entities.dtos.ReservationOutput;

@Service
public class ReservationManager implements ReservationService {

	private TrainService trainService;

	@Autowired
	public ReservationManager(TrainService trainService) {
		this.trainService = trainService;
	}

	public ReservationOutput reserve(ReservationInput reservationInput) {
		Train train = reservationInput.getTrain();
		int numberOfPeople = reservationInput.getNumberOfPeopleToReservation();
		boolean canPeoplePlacedInDifferentWagons = reservationInput.isCanPeoplePlacedInDifferentWagons();

		DataResult<ReservationOutput> reservationOutput = this.trainService.placePeopleInSeats(train, numberOfPeople,
				canPeoplePlacedInDifferentWagons);

		return reservationOutput.getData();
	}

}
