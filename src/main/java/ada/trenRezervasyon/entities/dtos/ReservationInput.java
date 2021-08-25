package ada.trenRezervasyon.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import ada.trenRezervasyon.entities.concretes.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInput {

	@JsonProperty("tren")
	private Train train;

	@JsonProperty("rezervasyonYapilacakKisiSayisi")
	private int numberOfPeopleToReservation;

	@JsonProperty("kisilerFarkliVagonlaraYerlestirilebilir")
	private boolean canPeoplePlacedInDifferentWagons;

}
