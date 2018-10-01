package be.vdab.frituurfrida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import be.vdab.frituurfrida.entities.Saus;
import be.vdab.frituurfrida.repositories.SausRepository;

@Service
class DefaultSausService implements SausService {
	private final SausRepository sausRepository;

	DefaultSausService(@Qualifier("properties3") SausRepository sausRepository) {
		this.sausRepository = sausRepository;
	}

	@Override
	public List<Saus> findAll() {
		return sausRepository.findAll();
	}
}