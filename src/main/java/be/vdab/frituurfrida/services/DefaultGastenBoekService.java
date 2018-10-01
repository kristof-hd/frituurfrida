package be.vdab.frituurfrida.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.frituurfrida.entities.GastenBoekEntry;
import be.vdab.frituurfrida.repositories.GastenBoekRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultGastenBoekService implements GastenBoekService {
	private final GastenBoekRepository gastenBoekRepository;

	DefaultGastenBoekService(GastenBoekRepository gastenBoekRepository) {
		this.gastenBoekRepository = gastenBoekRepository;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(GastenBoekEntry entry) {
		gastenBoekRepository.create(entry);
	}

	@Override
	public List<GastenBoekEntry> findAll() {
		return gastenBoekRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void delete(long[] ids) {
		Arrays.stream(ids).forEach(id -> gastenBoekRepository.delete(id));
	}
}