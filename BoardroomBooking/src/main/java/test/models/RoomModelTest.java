package test.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accolite.model.Room;

public class RoomModelTest {

	@Test
	public void testRoomId() {
		Room room = new Room();
		int roomId = 1;
		room.setRoomId(roomId);
		
		assertEquals(roomId, room.getRoomId());
	}
	
	@Test
	public void testLocationId() {
		Room room = new Room();
		int locationId = 1;
		room.setLocationId(locationId);
		
		assertEquals(locationId, room.getLocationId());
	}
	
	@Test
	public void testAvialability() {
		Room room = new Room();
		boolean isAvailable = false;
		room.setAvailable(isAvailable);
		
		assertEquals(isAvailable, room.isAvailable());
	}
	
}
