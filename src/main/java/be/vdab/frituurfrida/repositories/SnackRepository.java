package be.vdab.frituurfrida.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.frituurfrida.entities.Snack;

public interface SnackRepository {
	Optional<Snack> read(long id);
	void update(Snack snack);
	List<Snack> findByBeginNaam(String beginNaam);
}
