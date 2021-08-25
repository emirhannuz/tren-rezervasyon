package ada.trenRezervasyon.business.abstracts;

import java.util.List;
import java.util.Map;

import ada.trenRezervasyon.core.utilities.results.DataResult;
import ada.trenRezervasyon.entities.concretes.Vagon;

public interface VagonService {

	DataResult<Map<String, Integer>> getAvailableSeatsInAllVagon(List<Vagon> vagonlar);

	DataResult<Integer> totalAvailableSeatsOnTrain(List<Vagon> vagonlar);
}
