package ada.trenRezervasyon.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.trenRezervasyon.business.abstracts.WagonService;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.entities.concretes.Wagon;

@RestController
@RequestMapping("api/wagons")
public class WagonsController {

	private WagonService wagonService;

	@Autowired
	public WagonsController(WagonService wagonService) {
		this.wagonService = wagonService;
	}

	@PostMapping("/getAvailableSeatsInAllVagon")
	public Result howManySeatsAreAvailable(@RequestBody List<Wagon> wagons) {
		return this.wagonService.getAvailableSeatsInAllVagon(wagons);
	}
}
