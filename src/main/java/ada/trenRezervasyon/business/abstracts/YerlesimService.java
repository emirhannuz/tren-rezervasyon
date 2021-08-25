package ada.trenRezervasyon.business.abstracts;

import java.util.List;

import ada.trenRezervasyon.entities.concretes.Vagon;
import ada.trenRezervasyon.entities.concretes.Yerlesim;

public interface YerlesimService {
	public List<Yerlesim> ayniVagonaYerlestir(List<Vagon> vagonlar, int kisiSayisi);

	public List<Yerlesim> farkliVagonlaraYerlestir(List<Vagon> vagonlar, int kisiSayisi);
}
