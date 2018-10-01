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
@Qualifier("CSV3")
class CSVSausRepository3 implements SausRepository {
	private final Path pad; 
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVSausRepository3.class);

	CSVSausRepository3(@Value("${CSV3}") Path pad) {
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
		String[] onderdelen = regel.split(",");
		if (onderdelen.length < 2) {
			String fout = pad + ":" + regel + " bevat minder dan 2 onderdelen";
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
			String fout = pad + ":" + regel + " bevat verkeerde id";
			LOGGER.error(fout, ex);
			throw new SausRepositoryException(fout);
		}
	}
}