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

import com.accolite.dao.BookingDao;
import com.accolite.model.Booking;
import com.accolite.service.BookingService;

public class BookingTest {

	@Mock
	private BookingDao daoMock;

	@InjectMocks
	private BookingService bookingService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetBookings() {
		List<Booking> all = new ArrayList<>();
		Booking booking = new Booking();
		booking.setId(1);
		booking.setTitle("booking1");
		booking.setDescription("sample booking");
		booking.setLocationID(1);
		booking.setRoomId(1);
		booking.setStart(new Date(2018, 1 , 26));
		booking.setEnd(new Date(2018, 1 , 26));

		all.add(booking);

		Mockito.when(daoMock.getAllBookings(1)).thenReturn(all);

		List<Booking> result1 = bookingService.getAllBookings(1);
		Mockito.verify(daoMock).getAllBookings(1);
		assertEquals(all, result1);
		
		List<Booking> result2 = daoMock.getAllBookings(1);
		assertEquals(all, result2);
		
	}

}
