package ada.trenRezervasyon.business.abstracts;

import java.util.List;
import java.util.Map;

import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.entities.concretes.Wagon;

public interface WagonService {

	DataResult<Map<String, Integer>> getAvailableSeatsInAllVagon(List<Wagon> wagons);

	DataResult<Integer> totalAvailableSeatsOnTrain(List<Wagon> wagons);
}
