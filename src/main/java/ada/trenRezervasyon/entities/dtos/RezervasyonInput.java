package ada.trenRezervasyon.entities.dtos;

import ada.trenRezervasyon.entities.concretes.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RezervasyonInput {
	
	private Train tren;
	
	private int rezervasyonYapilacakKisiSayisi;
	
	private boolean kisilerFarkliVagonlaraYerlestirilebilir;

}
