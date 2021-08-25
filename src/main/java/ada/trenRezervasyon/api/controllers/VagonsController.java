package ada.trenRezervasyon.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.trenRezervasyon.business.abstracts.VagonService;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.entities.concretes.Vagon;

@RestController
@RequestMapping("api/vagons")
public class VagonsController {

	private VagonService vagonService;

	@Autowired
	public VagonsController(VagonService vagonService) {
		this.vagonService = vagonService;
	}
	
	@PostMapping("/getAvailableSeatsInAllVagon")
	public Result howManySeatsAreAvailable(@RequestBody List<Vagon> vagon) {
		return this.vagonService.getAvailableSeatsInAllVagon(vagon);
	}
}
