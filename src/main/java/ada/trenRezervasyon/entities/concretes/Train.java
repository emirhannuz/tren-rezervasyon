package ada.trenRezervasyon.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {

	@JsonProperty("ad")
	private String name;

	@JsonProperty("vagonlar")
	private List<Vagon> wagons;
}
