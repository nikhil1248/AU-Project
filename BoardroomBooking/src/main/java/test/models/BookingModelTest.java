package test.models;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.accolite.model.Booking;

public class BookingModelTest {

	@Test
	public void testGetID() {
		Booking booking = new Booking();
		int bookingId = 1;
		booking.setId(bookingId);

		assertEquals(bookingId, booking.getId());
	}

	@Test
	public void testGetTitle() {
		Booking booking = new Booking();
		String title = "Meeting";
		booking.setTitle(title);

		assertEquals(title, booking.getTitle());
	}
	
	@Test
	public void testGetRoomID() {
		Booking booking = new Booking();
		int roomId = 1;
		booking.setRoomId(roomId);

		assertEquals(roomId, booking.getRoomId());
	}
	
	@Test
	public void testGetStart() {
		Booking booking = new Booking();
		Date start = new Date();
		booking.setStart(start);

		assertEquals(start, booking.getStart());
	}
	
	@Test
	public void testGetEnd() {
		Booking booking = new Booking();
		Date end = new Date();
		booking.setEnd(end);

		assertEquals(end, booking.getEnd());
	}
	
	@Test
	public void testGetDescription() {
		Booking booking = new Booking();
		String description = "A Full day event booking";
		booking.setDescription(description);

		assertEquals(description, booking.getDescription());
	}
	
	@Test
	public void testGetLocationID() {
		Booking booking = new Booking();
		int locationId = 1;
		booking.setLocationId(locationId);

		assertEquals(locationId, booking.getLocationId());
	}
	
	@Test
	public void testGetUserID() {
		Booking booking = new Booking();
		int userId = 1;
		booking.setUserId(userId);

		assertEquals(userId, booking.getUserId());
	}
}
