package be.vdab.frituurfrida.services;

import java.util.List;

import be.vdab.frituurfrida.entities.GastenBoekEntry;

public interface GastenBoekService {
	void create(GastenBoekEntry entry);
	List<GastenBoekEntry> findAll(); 
	void delete(long[] ids); 
}
