package be.vdab.frituurfrida.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.frituurfrida.entities.GastenBoekEntry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcGastenBoekRepository.class)
@Sql("/insertGastenBoekEntry.sql")
public class JdbcGastenBoekRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String GASTENBOEK = "gastenboek";
	@Autowired
	private JdbcGastenBoekRepository repository;

	@Test
	public void create() {
		int aantalEntries = super.countRowsInTable(GASTENBOEK);
		GastenBoekEntry entry = new GastenBoekEntry("test2", LocalDateTime.now(), "test");
		repository.create(entry);
		assertNotEquals(0, entry.getId());
		assertEquals(aantalEntries + 1, this.countRowsInTable(GASTENBOEK));
		assertEquals(1, super.countRowsInTableWhere(GASTENBOEK, "id=" + entry.getId()));
	}

	@Test
	public void findAll() {
		List<GastenBoekEntry> entries = repository.findAll();
		assertEquals(super.countRowsInTable(GASTENBOEK), entries.size());
		LocalDateTime vorige = LocalDateTime.MIN;
		for (GastenBoekEntry entry : entries) {
			assertTrue(entry.getDatumTijd().compareTo(vorige) >= 0);
			vorige = entry.getDatumTijd();
		}
	}
}