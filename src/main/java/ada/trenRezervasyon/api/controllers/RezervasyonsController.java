package ada.trenRezervasyon.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.trenRezervasyon.business.abstracts.RezervasyonService;
import ada.trenRezervasyon.entities.dtos.RezervasyonInput;
import ada.trenRezervasyon.entities.dtos.RezervasyonOutput;

@RestController
@RequestMapping("/api/rezervasyons")
public class RezervasyonsController {

	private RezervasyonService rezervasyonService;

	@Autowired
	public RezervasyonsController(RezervasyonService rezervasyonService) {
		this.rezervasyonService = rezervasyonService;
	}

	@PostMapping("/rezerve-et")
	public RezervasyonOutput KisileriYerlestir(@RequestBody RezervasyonInput rezervasyonInput) {
		return this.rezervasyonService.KisileriYerlestir(rezervasyonInput);
	}
}
