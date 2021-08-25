package ada.trenRezervasyon.entities.dtos;

import ada.trenRezervasyon.entities.concretes.Yerlesim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RezervasyonOutput {

	private boolean rezervasyonYapilabilir;

	private Yerlesim[] yerlesimAyrinti;

}
