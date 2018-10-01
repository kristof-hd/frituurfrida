package be.vdab.frituurfrida.repositories;

import java.util.List;

import be.vdab.frituurfrida.entities.GastenBoekEntry;

public interface GastenBoekRepository {

	void create(GastenBoekEntry entry);
	List<GastenBoekEntry> findAll(); 
	void delete(long id);
	
}
