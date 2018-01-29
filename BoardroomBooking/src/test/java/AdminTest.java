package test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.accolite.dao.AdminDao;
import com.accolite.dao.BookingDao;
import com.accolite.dao.RoomDao;
import com.accolite.dao.SpecialRequestDao;
import com.accolite.model.Room;
import com.accolite.model.SpecialRequest;
import com.accolite.service.AdminService;

public class AdminTest {

	@Mock
	private AdminDao daoMock;
	
	@Mock
	private RoomDao roomDaoMock;
	
	@Mock
	private SpecialRequestDao specialRequestDaoMock;
	
	@Mock
	private BookingDao bookingDaoMock;

	@InjectMocks
	private AdminService adminService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addRoomTest() {
		Room room =new Room();
		room.setAvailable(true);
		room.setLocationId(1);
		room.setRoomId(2);
		
		List<Room> all = new ArrayList<>();
		all.add(room);
		
		Mockito.when(roomDaoMock.getAllRooms(1)).thenReturn(all);
		
		adminService.addNewRoom(room);
		
		List<Room> result = roomDaoMock.getAllRooms(1);
		Mockito.verify(daoMock).addRoom(room);
		
		assertEquals(all, result);
	}
	
	@Test
	public void changeAvailabilityTest() {
		Room room = new Room();
		room.setAvailable(false);
		room.setLocationId(1);
		room.setRoomId(1);
		
		adminService.addNewRoom(room);
		adminService.changeAvailability(1);
		
		Mockito.verify(daoMock).addRoom(room);
		Mockito.verify(daoMock).toggleAvailability(1);
		Mockito.when(roomDaoMock.getRoomByID(1)).thenReturn(room);
		Room result = roomDaoMock.getRoomByID(1);
		
		assertEquals(true, result.isAvailable());
	}
	
	@Test
	public void respondRequestTest() {
		SpecialRequest specialRequest = new SpecialRequest();
		specialRequest.setId(1);
		specialRequest.setTitle("SpecialRequest1");
		specialRequest.setDescription("sample SpecialRequest");
		specialRequest.setLocationID(1);
		specialRequest.setRoomId(1);
		specialRequest.setStart(new Date(2018, 1 , 26));
		specialRequest.setEnd(new Date(2018, 1 , 26));
		specialRequest.setStatus(false);
		List<SpecialRequest> all = new ArrayList<>();
		all.add(specialRequest);
				
		adminService.respondSpecialRequest(specialRequest, true);
		
		Mockito.verify(bookingDaoMock).addBooking(specialRequest);
		
		assertEquals(true, specialRequest.getStatus());
	}

}
