package ada.trenRezervasyon.entities.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Placement {

	@JsonProperty("vagonAdi")
	private String wagonName;

	@JsonProperty("kisiSayisi")
	private int numberOfPeople;

}
