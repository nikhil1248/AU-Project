package test.daos;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.dao.AdminDao;
import com.accolite.model.Room;

public class AdminDaoTest {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	private Room room;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTemplate() {
		AdminDao dao = new AdminDao();
		dao.setJdbcTemplate(jdbcTemplate);
		
		assertEquals(jdbcTemplate, dao.getJdbcTemplate());
	}
	
	@Test
	public void testAddRoom() {
		AdminDao dao = new AdminDao();
		int id = 1;
		int locId = 1;
		boolean available = true;
		when(room.getLocationId()).thenReturn(locId);
		when(room.getRoomId()).thenReturn(id);
		when(room.isAvailable()).thenReturn(available);
		when(jdbcTemplate.update("Update", id, available, locId)).thenReturn(1);
		dao.setJdbcTemplate(jdbcTemplate);
		int rows = dao.addRoom(room);
		
		assertEquals(1, rows);
	}

}
