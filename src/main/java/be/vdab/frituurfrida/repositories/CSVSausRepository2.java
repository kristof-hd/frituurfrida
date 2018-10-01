package be.vdab.frituurfrida.repositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;

@Component
@Qualifier("CSV2")
class CSVSausRepository2 implements SausRepository {
	private static final Path PAD = Paths.get("/data/sauzen.csv");
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVSausRepository2.class);

	@Override
	public List<Saus> findAll() {
		List<Saus> sauzen = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(PAD)) {
			for (String regel; (regel = reader.readLine()) != null;) {
				if (!regel.isEmpty()) { // blanko regel overslaan
					sauzen.add(maakSaus(regel));
				}
			}
		} catch (IOException ex) {
			String fout = "Fout bij lezen " + PAD;
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
		return sauzen;
	}

	private Saus maakSaus(String regel) {
		String[] onderdelen = regel.split(",");
		if (onderdelen.length < 2) {
			String fout = PAD + ":" + regel + " bevat minder dan 2 onderdelen";
			LOGGER.error(fout);
			throw new SausRepositoryException(fout);
		}
		try {
			Saus saus = new Saus(Long.parseLong(onderdelen[0]), onderdelen[1]);
			for (int index = 2; index < onderdelen.length; index++) {
				saus.addIngredient(onderdelen[index]);
			}
			return saus;
		} catch (NumberFormatException ex) {
			String fout = PAD + ":" + regel + " bevat verkeerde id";
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
	}
}