package be.vdab.frituurfrida.repositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;

@Component
@Qualifier("properties3")
class PropertiesSausRepository3 implements SausRepository {
	private final Path pad; 
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesSausRepository3.class);

	PropertiesSausRepository3(@Value("${properties3}") Path pad) {
		this.pad=pad; 
	}
	
	@Override
	public List<Saus> findAll() {
		List<Saus> sauzen = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(pad)) {
			for (String regel; (regel = reader.readLine()) != null;) {
				if (!regel.isEmpty()) { // blanko regel overslaan
					sauzen.add(maakSaus(regel));
				}
			}
		} catch (IOException ex) {
			String fout = "Fout bij lezen " + pad;
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
		return sauzen;
	}

	private Saus maakSaus(String regel) {
		String[] onderdelen = regel.split(":");
		if (onderdelen.length < 2) {
			String fout = pad + ":" + regel + " bevat minder dan 2 onderdelen";
			LOGGER.error(fout);
			throw new SausRepositoryException(fout);
		}
		try {
			String[] onderdelen2 = onderdelen[1].split(",");
			Saus saus = new Saus(Long.parseLong(onderdelen[0]), onderdelen2[0]);
			for (int index = 1; index < onderdelen2.length; index++) {
				saus.addIngredient(onderdelen2[index]);
			}
			return saus;
		} catch (NumberFormatException ex) {
			String fout = pad + ":" + regel + " bevat verkeerde id";
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
	}
}
