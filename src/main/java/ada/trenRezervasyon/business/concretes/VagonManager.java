package ada.trenRezervasyon.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.VagonService;
import ada.trenRezervasyon.core.utilities.business.BusinessRules;
import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.core.utilities.results.ErrorDataResult;
import ada.trenRezervasyon.core.utilities.results.ErrorResult;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.core.utilities.results.SuccessDataResult;
import ada.trenRezervasyon.core.utilities.results.SuccessResult;
import ada.trenRezervasyon.entities.concretes.Vagon;

@Service
public class VagonManager implements VagonService {

	@Autowired
	public VagonManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataResult<Map<String, Integer>> getAvailableSeatsInAllVagon(List<Vagon> vagonlar) {
		Map<String, Integer> availableSeats = new HashMap<String, Integer>();
		for (Vagon vagon : vagonlar) {
			DataResult<Integer> result = this.howManySeatsAreAvailable(vagon);
			availableSeats.put(vagon.getAd(), result.getData());
		}
		return new SuccessDataResult<Map<String, Integer>>(availableSeats);
	}

	@Override
	public DataResult<Integer> totalAvailableSeatsOnTrain(List<Vagon> vagonlar) {
		int totalAvailableSeats = 0;

		for (Vagon vagon : vagonlar) {
			totalAvailableSeats += this.howManySeatsAreAvailable(vagon).getData();
		}
		
		if (totalAvailableSeats == 0) {
			return new ErrorDataResult<Integer>("Trende boş yer yok");
		}
		System.out.println(totalAvailableSeats);
		return new SuccessDataResult<Integer>(totalAvailableSeats);
	}

	/* private methods */

	private Result checkIfVagonAvailable(Vagon vagon) {
		if (vagon.getKapasite() * 7 / 10 < vagon.getDoluKoltukAdet()) {
			return new ErrorResult("Bu vagona rezervasyon yapılamaz.");
		}
		return new SuccessResult();
	}


	private DataResult<Integer> howManySeatsAreAvailable(Vagon vagon) {
		Result result = BusinessRules.Run(this.checkIfVagonAvailable(vagon));

		if (result != null) {
			return new ErrorDataResult<Integer>(0, result.getMessage());
		}

		int availableSeatsCount = vagon.getKapasite() * 7 / 10 - vagon.getDoluKoltukAdet();

		return new SuccessDataResult<Integer>(availableSeatsCount);
	}
}
