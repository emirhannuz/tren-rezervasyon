package ada.trenRezervasyon.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.TrainService;
import ada.trenRezervasyon.business.abstracts.WagonService;
import ada.trenRezervasyon.business.abstracts.PlacementService;
import ada.trenRezervasyon.core.utilities.business.BusinessRules;
import ada.trenRezervasyon.core.utilities.results.ErrorResult;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.core.utilities.results.SuccessResult;
import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.concretes.Wagon;
import ada.trenRezervasyon.entities.concretes.Placement;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

@Service
public class TrainManager implements TrainService {

	private WagonService wagonService;
	private PlacementService placementService;

	@Autowired
	public TrainManager(WagonService wagonService, PlacementService placementService) {
		this.wagonService = wagonService;
		this.placementService = placementService;
	}

	@Override
	public RezervasyonOutput placePeopleInSeats(Train train, int numberOfPeople,
			boolean canPeoplePlacedInDifferentWagons) {

		RezervasyonOutput reservationOutput = new RezervasyonOutput();
		List<Placement> placements = new ArrayList<Placement>();
		List<Wagon> wagons = train.getWagons();

		Result result = BusinessRules.Run(
				this.checkIfNoReservationableSeatAvailable(
						this.wagonService.getAvailableSeatsInAllVagon(wagons).getData()),
				this.checkIfTotalPeopleMoreThanTotalSeats(wagons, numberOfPeople));

		if (result != null) {
			reservationOutput.setRezervasyonYapilabilir(false);
			reservationOutput.setYerlesimAyrinti(placements);
			return reservationOutput;
		}

		reservationOutput = canPeoplePlacedInDifferentWagons ? this.placePeopleInDifferentWagon(train, numberOfPeople)
				: this.placePeopleInSameWagon(train, numberOfPeople);

		return reservationOutput;
	}

	@Override
	public RezervasyonOutput placePeopleInSameWagon(Train train, int numberOfPeople) {
		RezervasyonOutput reservationOutput = new RezervasyonOutput();
		List<Placement> placements = new ArrayList<Placement>();
		List<Wagon> wagons = train.getWagons();

		placements = this.placementService.placeInSameWagon(wagons, numberOfPeople);
		reservationOutput.setRezervasyonYapilabilir(true);
		reservationOutput.setYerlesimAyrinti(placements);
		return reservationOutput;
	}

	@Override
	public RezervasyonOutput placePeopleInDifferentWagon(Train train, int numberOfPeople) {
		RezervasyonOutput reservationOutput = new RezervasyonOutput();
		List<Placement> placements = new ArrayList<Placement>();
		List<Wagon> wagons = train.getWagons();

		placements = this.placementService.placeInDifferentWagon(wagons, numberOfPeople);
		reservationOutput.setRezervasyonYapilabilir(true);
		reservationOutput.setYerlesimAyrinti(placements);
		return reservationOutput;
	}

	/* private methods */

	private Result checkIfNoReservationableSeatAvailable(Map<String, Integer> seats) {
		for (int totalAvailableSeat : seats.values()) {
			if (totalAvailableSeat > 0) {
				return new SuccessResult();
			}
		}
		return new ErrorResult("Müsait koltuk bulunamadı.");
	}

	private Result checkIfTotalPeopleMoreThanTotalSeats(List<Wagon> wagons, int numberOfPeople) {

		if (this.wagonService.totalAvailableSeatsOnTrain(wagons).getData() < numberOfPeople) {
			return new ErrorResult("Kişilerin sığabileceği kadar yer mevcut değil.");
		}

		return new SuccessResult();
	}

}
