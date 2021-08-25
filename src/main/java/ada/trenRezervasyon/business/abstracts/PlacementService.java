package ada.trenRezervasyon.business.abstracts;

import java.util.List;

import ada.trenRezervasyon.entities.concretes.Wagon;
import ada.trenRezervasyon.entities.concretes.Placement;

public interface PlacementService {
	
	public List<Placement> placeInSameWagon(List<Wagon> wagons, int numberOfPeople);

	public List<Placement> placeInDifferentWagon(List<Wagon> wagons, int numberOfPeople);
}
