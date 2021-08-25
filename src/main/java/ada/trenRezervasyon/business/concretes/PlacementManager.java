package ada.trenRezervasyon.business.concretes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.WagonService;
import ada.trenRezervasyon.business.abstracts.PlacementService;
import ada.trenRezervasyon.entities.concretes.Wagon;
import ada.trenRezervasyon.entities.concretes.Placement;

@Service
public class PlacementManager implements PlacementService {

	private WagonService wagonService;

	@Autowired
	public PlacementManager(WagonService wagonService) {
		this.wagonService = wagonService;
	}

	public List<Placement> placeInSameWagon(List<Wagon> wagons, int numberOfPeople) {

		List<Placement> placements = new ArrayList<Placement>();
		Map<String, Integer> availableSeats = this.wagonService.getAvailableSeatsInAllVagon(wagons).getData();

		for (String wagonName : availableSeats.keySet()) {
			int numberOfSeat = availableSeats.get(wagonName);

			if (numberOfPeople <= numberOfSeat) {
				Placement placement = new Placement(wagonName, numberOfPeople);
				placements.add(placement);
				return placements;
			}

		}

		return placements;
	}

	public List<Placement> placeInDifferentWagon(List<Wagon> wagons, int numberOfPeople) {

		List<Placement> placements = new ArrayList<Placement>();
		Map<String, Integer> availableSeats = this.wagonService.getAvailableSeatsInAllVagon(wagons).getData();
		Map<String, Integer> placedWagons = new HashMap<String, Integer>();

		for (String wagonName : availableSeats.keySet()) {
			placedWagons.put(wagonName, 0);
		}

		while (numberOfPeople > 0) {
			for (String wagonName : availableSeats.keySet()) {
				int numberOfSeat = availableSeats.get(wagonName);

				if (numberOfSeat >= 1) {
					if (numberOfPeople <= 0)
						break;
					placedWagons.put(wagonName, placedWagons.get(wagonName) + 1);
					numberOfPeople--;
					availableSeats.put(wagonName, availableSeats.get(wagonName) - 1);
				}

			}

		}

		for (String wagonName : placedWagons.keySet()) {
			int numberOfPeoplePlacedInWagon = placedWagons.get(wagonName);
			if (numberOfPeoplePlacedInWagon > 0) {

				Placement placement = new Placement(wagonName, numberOfPeoplePlacedInWagon);
				placements.add(placement);
			}
		}
		return placements;
	}
}
