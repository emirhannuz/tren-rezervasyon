package ada.trenRezervasyon.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.WagonService;
import ada.trenRezervasyon.core.utilities.business.BusinessRules;
import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.core.utilities.results.ErrorDataResult;
import ada.trenRezervasyon.core.utilities.results.ErrorResult;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.core.utilities.results.SuccessDataResult;
import ada.trenRezervasyon.core.utilities.results.SuccessResult;
import ada.trenRezervasyon.entities.concretes.Wagon;

@Service
public class WagonManager implements WagonService {

	@Autowired
	public WagonManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataResult<Map<String, Integer>> getAvailableSeatsInAllVagon(List<Wagon> wagons) {
		Map<String, Integer> availableSeats = new HashMap<String, Integer>();
		for (Wagon wagon : wagons) {
			DataResult<Integer> result = this.howManySeatsAreAvailable(wagon);
			availableSeats.put(wagon.getName(), result.getData());
		}
		return new SuccessDataResult<Map<String, Integer>>(availableSeats);
	}

	@Override
	public DataResult<Integer> totalAvailableSeatsOnTrain(List<Wagon> wagons) {
		int totalAvailableSeats = 0;

		for (Wagon wagon : wagons) {
			totalAvailableSeats += this.howManySeatsAreAvailable(wagon).getData();
		}

		if (totalAvailableSeats == 0) {
			return new ErrorDataResult<Integer>("Trende boş yer yok.");
		}
		System.out.println(totalAvailableSeats);
		return new SuccessDataResult<Integer>(totalAvailableSeats);
	}

	/* private methods */

	private Result checkIfWagonAvailable(Wagon wagon) {
		if (wagon.getCapacity() * 7 / 10 < wagon.getNumberOfFullSeats()) {
			return new ErrorResult("Bu vagona rezervasyon yapılamaz.");
		}
		return new SuccessResult();
	}

	private DataResult<Integer> howManySeatsAreAvailable(Wagon wagon) {
		Result result = BusinessRules.Run(this.checkIfWagonAvailable(wagon));

		if (result != null) {
			return new ErrorDataResult<Integer>(0, result.getMessage());
		}

		int availableSeatsCount = wagon.getCapacity() * 7 / 10 - wagon.getNumberOfFullSeats();

		return new SuccessDataResult<Integer>(availableSeatsCount);
	}
}
