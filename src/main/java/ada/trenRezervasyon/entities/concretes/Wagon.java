package ada.trenRezervasyon.entities.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wagon {

	@JsonProperty("ad")
	private String name;
	
	@JsonProperty("kapasite")
	private int capacity ;
	
	@JsonProperty("doluKoltukAdet")
	private int numberOfFullSeats;
}
