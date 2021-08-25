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
import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.core.utilities.results.ErrorResult;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.core.utilities.results.SuccessDataResult;
import ada.trenRezervasyon.core.utilities.results.SuccessResult;
import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.concretes.Wagon;
import ada.trenRezervasyon.entities.concretes.Placement;
import ada.trenRezervasyon.entities.dtos.ReservationOutput;

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
	public DataResult<ReservationOutput> placePeopleInSeats(Train train, int numberOfPeople,
			boolean canPeoplePlacedInDifferentWagons) {

		ReservationOutput reservationOutput = new ReservationOutput();
		List<Placement> placements = new ArrayList<Placement>();
		List<Wagon> wagons = train.getWagons();

		Result result = BusinessRules.Run(
				this.checkIfNoReservationableSeatAvailable(
						this.wagonService.getAvailableSeatsInAllVagon(wagons).getData()),
				this.checkIfTotalPeopleMoreThanTotalSeats(wagons, numberOfPeople));

		if (result != null) {
			reservationOutput.setReservationable(false);
			reservationOutput.setPlacementDetails(placements);
			return new SuccessDataResult<ReservationOutput>(reservationOutput, result.getMessage());
		}

		reservationOutput = canPeoplePlacedInDifferentWagons
				? this.placePeopleInDifferentWagon(train, numberOfPeople).getData()
				: this.placePeopleInSameWagon(train, numberOfPeople).getData();

		return new SuccessDataResult<ReservationOutput>(reservationOutput, "Rezervasyon başarılı.");
	}

	
	
	private DataResult<ReservationOutput> placePeopleInSameWagon(Train train, int numberOfPeople) {
		ReservationOutput reservationOutput = new ReservationOutput();
		List<Placement> placements = new ArrayList<Placement>();
		List<Wagon> wagons = train.getWagons();

		placements = this.placementService.placeInSameWagon(wagons, numberOfPeople);
		reservationOutput.setReservationable(true);
		reservationOutput.setPlacementDetails(placements);
		return new SuccessDataResult<ReservationOutput>(reservationOutput);
	}

	private DataResult<ReservationOutput> placePeopleInDifferentWagon(Train train, int numberOfPeople) {
		ReservationOutput reservationOutput = new ReservationOutput();
		List<Placement> placements = new ArrayList<Placement>();
		List<Wagon> wagons = train.getWagons();

		placements = this.placementService.placeInDifferentWagon(wagons, numberOfPeople);
		reservationOutput.setReservationable(true);
		reservationOutput.setPlacementDetails(placements);
		return new SuccessDataResult<ReservationOutput>(reservationOutput);
	}

	private Result checkIfNoReservationableSeatAvailable(Map<String, Integer> seats) {
		for (int totalAvailableSeat : seats.values()) {
			if (totalAvailableSeat > 0) {
				return new SuccessResult();
			}
		}
		return new ErrorResult("Belirtilen vagonlar için online rezervasyon yapılamaz.");
	}

	private Result checkIfTotalPeopleMoreThanTotalSeats(List<Wagon> wagons, int numberOfPeople) {

		if (this.wagonService.totalAvailableSeatsOnTrain(wagons).getData() < numberOfPeople) {
			return new ErrorResult("Kişileri yerleştirebilecek kadar yer mevcut değil.");
		}

		return new SuccessResult();
	}

}
