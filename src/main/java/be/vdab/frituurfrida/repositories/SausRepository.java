package be.vdab.frituurfrida.repositories;

import java.util.List;
import be.vdab.frituurfrida.entities.Saus;

public interface SausRepository {
	List<Saus> findAll();
}
