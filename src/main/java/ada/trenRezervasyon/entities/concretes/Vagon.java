package ada.trenRezervasyon.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vagon {

	private String ad;
	private int kapasite;
	private int doluKoltukAdet;
}
