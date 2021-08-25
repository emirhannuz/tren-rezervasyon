package ada.trenRezervasyon.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.TrainService;
import ada.trenRezervasyon.business.abstracts.WagonService;
import ada.trenRezervasyon.business.abstracts.YerlesimService;
import ada.trenRezervasyon.core.utilities.business.BusinessRules;
import ada.trenRezervasyon.core.utilities.results.ErrorResult;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.core.utilities.results.SuccessResult;
import ada.trenRezervasyon.entities.concretes.Train;
import ada.trenRezervasyon.entities.concretes.Wagon;
import ada.trenRezervasyon.entities.concretes.Yerlesim;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

@Service
public class TrainManager implements TrainService {

	private WagonService vagonService;
	private YerlesimService yerlesimService;

	@Autowired
	public TrainManager(WagonService vagonService, YerlesimService yerlesimService) {
		this.vagonService = vagonService;
		this.yerlesimService = yerlesimService;
	}

	@Override
	public RezervasyonOutput placePeopleInSeats(Train train, int numberOfPeople,
			boolean canPersonsPlacedInDifferentWagons) {

		RezervasyonOutput rezervasyonOutput = new RezervasyonOutput();
		List<Yerlesim> yerlesim = new ArrayList<Yerlesim>();
		List<Wagon> wagons = train.getWagons();

		Result result = BusinessRules.Run(
				this.checkIfNoReservationableSeatAvailable(
						this.vagonService.getAvailableSeatsInAllVagon(wagons).getData()),
				this.checkIfTotalPeopleMoreThanTotalSeats(wagons, numberOfPeople));

		if (result != null) {
			rezervasyonOutput.setRezervasyonYapilabilir(false);
			rezervasyonOutput.setYerlesimAyrinti(yerlesim);
			return rezervasyonOutput;
		}

		rezervasyonOutput = canPersonsPlacedInDifferentWagons ? 
				this.placePeopleInDifferentWagon(train, numberOfPeople)
				: this.placePeopleInSameWagon(train, numberOfPeople);

		return rezervasyonOutput;
	}

	@Override
	public RezervasyonOutput placePeopleInSameWagon(Train train, int numberOfPeople) {
		RezervasyonOutput reservationOutput = new RezervasyonOutput();
		List<Yerlesim> yerlesim = new ArrayList<Yerlesim>();
		List<Wagon> wagons = train.getWagons();

		yerlesim = this.yerlesimService.ayniVagonaYerlestir(wagons, numberOfPeople);
		reservationOutput.setRezervasyonYapilabilir(true);
		reservationOutput.setYerlesimAyrinti(yerlesim);
		return reservationOutput;
	}

	@Override
	public RezervasyonOutput placePeopleInDifferentWagon(Train train, int numberOfPeople) {
		RezervasyonOutput reservationOutput = new RezervasyonOutput();
		List<Yerlesim> yerlesim = new ArrayList<Yerlesim>();
		List<Wagon> vagonlar = train.getWagons();

		yerlesim = this.yerlesimService.farkliVagonlaraYerlestir(vagonlar, numberOfPeople);
		reservationOutput.setRezervasyonYapilabilir(true);
		reservationOutput.setYerlesimAyrinti(yerlesim);
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

	private Result checkIfTotalPeopleMoreThanTotalSeats(List<Wagon> vagonlar, int kisiSayisi) {

		if (this.vagonService.totalAvailableSeatsOnTrain(vagonlar).getData() < kisiSayisi) {
			return new ErrorResult("Kişilerin sığabileceği kadar yer mevcut değil.");
		}

		return new SuccessResult();
	}

}
