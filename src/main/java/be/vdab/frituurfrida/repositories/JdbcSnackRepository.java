package be.vdab.frituurfrida.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.frituurfrida.entities.Snack;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;

@Repository
class JdbcSnackRepository implements SnackRepository {
	private final JdbcTemplate template;

	JdbcSnackRepository(JdbcTemplate template) {
		this.template = template;
	}

	private static final String UPDATE_SNACK = "update snacks set naam=?, prijs=? where id=?";

	@Override
	public void update(Snack snack) {
		if (template.update(UPDATE_SNACK, snack.getNaam(), snack.getPrijs(), snack.getId()) == 0) {
			throw new SnackNietGevondenException();
		}
	}

	private static final String SELECT_BY_BEGIN_LETTER = "select id, naam, prijs from snacks where naam like ?";
	private final RowMapper<Snack> snackRowMapper = (resultSet, rowNum) -> new Snack(resultSet.getLong("id"),
			resultSet.getString("naam"), resultSet.getBigDecimal("prijs"));

	@Override
	public List<Snack> findByBeginNaam(String beginNaam) {
		return template.query(SELECT_BY_BEGIN_LETTER, snackRowMapper, beginNaam + '%');
	}

	private static final String READ = "select id, naam, prijs from snacks where id=?";

	@Override
	public Optional<Snack> read(long id) {
		try {
			return Optional.of(template.queryForObject(READ, snackRowMapper, id));
		} catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}
}