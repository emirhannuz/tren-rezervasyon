package ada.trenRezervasyon.business.abstracts;

import java.util.List;

import ada.trenRezervasyon.entities.concretes.Wagon;
import ada.trenRezervasyon.entities.concretes.Yerlesim;

public interface YerlesimService {
	public List<Yerlesim> ayniVagonaYerlestir(List<Wagon> vagonlar, int kisiSayisi);

	public List<Yerlesim> farkliVagonlaraYerlestir(List<Wagon> vagonlar, int kisiSayisi);
}
