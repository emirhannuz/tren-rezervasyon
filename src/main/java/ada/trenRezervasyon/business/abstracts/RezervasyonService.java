package ada.trenRezervasyon.business.abstracts;

import ada.trenRezervasyon.entities.dtos.RezervasyonInput;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

public interface RezervasyonService {
	public RezervasyonOutput KisileriYerlestir(RezervasyonInput rezervasyonInput);
}
