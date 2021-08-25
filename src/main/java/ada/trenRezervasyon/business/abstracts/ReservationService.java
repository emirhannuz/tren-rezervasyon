package ada.trenRezervasyon.business.abstracts;

import ada.trenRezervasyon.entities.dtos.ReservationInput;
import ada.trenRezervasyon.entities.dtos.ReservationOutput;

public interface ReservationService {
	public ReservationOutput reserve(ReservationInput reservationInput);
}
