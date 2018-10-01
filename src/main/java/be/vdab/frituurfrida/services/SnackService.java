package be.vdab.frituurfrida.services;

import java.util.List;
import java.util.Optional;

import be.vdab.frituurfrida.entities.Snack;

public interface SnackService {
	Optional<Snack> read(long id);
	void update(Snack snack);
	List<Snack> findByBeginNaam(String beginNaam);
}
