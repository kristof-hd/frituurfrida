package be.vdab.frituurfrida.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.frituurfrida.entities.GastenBoekEntry;

@Repository
class JdbcGastenBoekRepository implements GastenBoekRepository {
	private final SimpleJdbcInsert insert;
	private final JdbcTemplate template;

	private static final String SQL_SELECT_ALL = "select id,naam,datumtijd,bericht from gastenboek order by datumtijd desc";
	private final RowMapper<GastenBoekEntry> entryRowMapper = (resultSet, rowNum) -> new GastenBoekEntry(
			resultSet.getLong("id"), resultSet.getString("naam"), resultSet.getTimestamp("datumtijd").toLocalDateTime(),
			resultSet.getString("bericht"));

	private static final String SQL_DELETE="delete from gastenboek where id=?";
	
	JdbcGastenBoekRepository(JdbcTemplate template) {
		this.template = template;
		this.insert = new SimpleJdbcInsert(template);
		insert.withTableName("gastenboek");
		insert.usingGeneratedKeyColumns("id");
	}

	@Override
	public void create(GastenBoekEntry entry) {
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("naam", entry.getNaam());
		kolomWaarden.put("datumtijd", entry.getDatumTijd());
		kolomWaarden.put("bericht", entry.getBericht());
		Number id = insert.executeAndReturnKey(kolomWaarden);
		entry.setId(id.longValue());
	}

	@Override
	public List<GastenBoekEntry> findAll() {
		return template.query(SQL_SELECT_ALL, entryRowMapper);
	}
	
	@Override
	public void delete(long id) {
		template.update(SQL_DELETE, id); 
	}
	
}