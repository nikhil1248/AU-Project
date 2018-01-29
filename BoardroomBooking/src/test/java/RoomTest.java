
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.accolite.dao.RoomDao;
import com.accolite.model.Room;
import com.accolite.service.RoomService;

public class RoomTest {
	
	@Mock
	private RoomDao daoMock;

	@InjectMocks
	private RoomService roomService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllRooms() {
		List<Room> all = new ArrayList<>();
		Room room = new Room();
		room.setAvailable(true);
		room.setLocationId(1);
		room.setRoomId(1);

		all.add(room);

		Mockito.when(daoMock.getAllRooms(1)).thenReturn(all);

		List<Room> result = roomService.getAllRooms(1);

		Mockito.verify(daoMock).getAllRooms(1);
		
		assertEquals(all, result);
	}

}
