package ada.trenRezervasyon.entities.dtos;

import java.util.List;

import ada.trenRezervasyon.entities.concretes.Placement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RezervasyonOutput {

	private boolean rezervasyonYapilabilir;

	private List<Placement> yerlesimAyrinti;

}
