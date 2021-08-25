package ada.trenRezervasyon.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tren {

	private String ad;

	private Vagon[] vagonlar;
}
