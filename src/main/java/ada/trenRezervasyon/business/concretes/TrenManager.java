package ada.trenRezervasyon.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.TrenService;
import ada.trenRezervasyon.business.abstracts.VagonService;
import ada.trenRezervasyon.business.abstracts.YerlesimService;
import ada.trenRezervasyon.core.utilities.business.BusinessRules;
import ada.trenRezervasyon.core.utilities.results.ErrorResult;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.core.utilities.results.SuccessResult;
import ada.trenRezervasyon.entities.concretes.Tren;
import ada.trenRezervasyon.entities.concretes.Vagon;
import ada.trenRezervasyon.entities.concretes.Yerlesim;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

@Service
public class TrenManager implements TrenService {

	private VagonService vagonService;
	private YerlesimService yerlesimService;

	@Autowired
	public TrenManager(VagonService vagonService, YerlesimService yerlesimService) {
		this.vagonService = vagonService;
		this.yerlesimService = yerlesimService;
	}

	@Override
	public RezervasyonOutput kisileriAyniVagonaYerlestir(Tren tren, int kisiSayisi) {
		RezervasyonOutput rezervasyonOutput = new RezervasyonOutput();
		List<Yerlesim> yerlesim = new ArrayList<Yerlesim>();

		List<Vagon> vagonlar = tren.getVagonlar();
		Result result = BusinessRules.Run(this.checkIfNoReservationableSeatAvailable(
				this.vagonService.getAvailableSeatsInAllVagon(tren.getVagonlar()).getData()),
				this.checkIfTotalPeopleMoreThanTotalSeats(vagonlar, kisiSayisi));

		if (result != null) {
			rezervasyonOutput.setRezervasyonYapilabilir(false);
			rezervasyonOutput.setYerlesimAyrinti(yerlesim);
			return rezervasyonOutput;
		}

		yerlesim = this.yerlesimService.ayniVagonaYerlestir(vagonlar, kisiSayisi);
		rezervasyonOutput.setRezervasyonYapilabilir(true);
		rezervasyonOutput.setYerlesimAyrinti(yerlesim);
		return rezervasyonOutput;
	}

	@Override
	public RezervasyonOutput kisileriFarkliVagonlaraYerlestir(Tren tren, int kisiSayisi) {
		RezervasyonOutput rezervasyonOutput = new RezervasyonOutput();
		List<Yerlesim> yerlesim = new ArrayList<Yerlesim>();
		List<Vagon> vagonlar = tren.getVagonlar();

		Result result = BusinessRules.Run(
				this.checkIfNoReservationableSeatAvailable(
						this.vagonService.getAvailableSeatsInAllVagon(vagonlar).getData()),
				this.checkIfTotalPeopleMoreThanTotalSeats(vagonlar, kisiSayisi));
		if (result != null) {
			rezervasyonOutput.setRezervasyonYapilabilir(false);
			rezervasyonOutput.setYerlesimAyrinti(yerlesim);
			System.out.println(result.getMessage());
			return rezervasyonOutput;
		}

		yerlesim = this.yerlesimService.farkliVagonlaraYerlestir(vagonlar, kisiSayisi);
		rezervasyonOutput.setRezervasyonYapilabilir(true);
		rezervasyonOutput.setYerlesimAyrinti(yerlesim);
		return rezervasyonOutput;
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

	private Result checkIfTotalPeopleMoreThanTotalSeats(List<Vagon> vagonlar, int kisiSayisi) {

		if (this.vagonService.totalAvailableSeatsOnTrain(vagonlar).getData() < kisiSayisi) {
			return new ErrorResult("Kişilerin sığabileceği kadar yer mevcut değil.");
		}

		return new SuccessResult();
	}

}
