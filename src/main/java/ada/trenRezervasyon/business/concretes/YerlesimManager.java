package ada.trenRezervasyon.business.concretes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.trenRezervasyon.business.abstracts.VagonService;
import ada.trenRezervasyon.business.abstracts.YerlesimService;
import ada.trenRezervasyon.core.utilities.business.BusinessRules;
import ada.trenRezervasyon.core.utilities.results.Result;
import ada.trenRezervasyon.entities.concretes.Vagon;
import ada.trenRezervasyon.entities.concretes.Yerlesim;

@Service
public class YerlesimManager implements YerlesimService {

	private VagonService vagonService;

	@Autowired
	public YerlesimManager(VagonService vagonService) {
		this.vagonService = vagonService;
	}

	public List<Yerlesim> ayniVagonaYerlestir(List<Vagon> vagonlar, int kisiSayisi) {
		List<Yerlesim> yerlesimler = new ArrayList<Yerlesim>();

		Map<String, Integer> availableSeats = this.vagonService.getAvailableSeatsInAllVagon(vagonlar).getData();

		for (String vagonAdi : availableSeats.keySet()) {
			int koltukSayisi = availableSeats.get(vagonAdi);

			if (kisiSayisi <= koltukSayisi) {
				Yerlesim yerlesim = new Yerlesim(vagonAdi, kisiSayisi);
				yerlesimler.add(yerlesim);
				return yerlesimler;
			}

		}

		return yerlesimler;
	}

	public List<Yerlesim> farkliVagonlaraYerlestir(List<Vagon> vagonlar, int kisiSayisi) {
		List<Yerlesim> yerlesimler = new ArrayList<Yerlesim>();

		Map<String, Integer> availableSeats = this.vagonService.getAvailableSeatsInAllVagon(vagonlar).getData();

		Map<String, Integer> yerlestirilenVagonlar = new HashMap<String, Integer>();

		for (String vagonAdi : availableSeats.keySet()) {
			yerlestirilenVagonlar.put(vagonAdi, 0);
		}

		while (kisiSayisi > 0) {
			for (String vagonAdi : availableSeats.keySet()) {
				int koltukSayisi = availableSeats.get(vagonAdi);
				System.out.println("koltuk sayisi: " + koltukSayisi);
				if (koltukSayisi >= 1) {
					if (kisiSayisi <= 0)
						break;
					yerlestirilenVagonlar.put(vagonAdi, yerlestirilenVagonlar.get(vagonAdi) + 1);
					kisiSayisi--;
					availableSeats.put(vagonAdi, availableSeats.get(vagonAdi) - 1);
				}

			}

		}
		for (String vagonAdi : yerlestirilenVagonlar.keySet()) {
			int yerlestirilenVagonKisiSayisi = yerlestirilenVagonlar.get(vagonAdi);
			if (yerlestirilenVagonKisiSayisi > 0) {

				Yerlesim yerlesim = new Yerlesim(vagonAdi, yerlestirilenVagonKisiSayisi);
				yerlesimler.add(yerlesim);
			}
		}
		return yerlesimler;
	}
}
