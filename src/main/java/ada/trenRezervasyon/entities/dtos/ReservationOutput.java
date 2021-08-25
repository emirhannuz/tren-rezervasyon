package ada.trenRezervasyon.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ada.trenRezervasyon.entities.concretes.Placement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationOutput {

	@JsonProperty("rezervasyonYapilabilir")
	private boolean reservationable;

	@JsonProperty("yerlesimAyrinti")
	private List<Placement> placementDetails;

}
