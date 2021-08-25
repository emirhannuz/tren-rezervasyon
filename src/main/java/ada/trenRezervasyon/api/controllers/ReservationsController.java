package ada.trenRezervasyon.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.trenRezervasyon.business.abstracts.ReservationService;
import ada.trenRezervasyon.entities.dtos.ReservationInput;
import ada.trenRezervasyon.entities.dtos.ReservationOutput;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {

	private ReservationService ReservationService;

	@Autowired
	public ReservationsController(ReservationService ReservationService) {
		this.ReservationService = ReservationService;
	}

	@PostMapping("/reserve")
	public ReservationOutput reserve(@RequestBody ReservationInput reservationInput) {
		return this.ReservationService.reserve(reservationInput);
	}
}
