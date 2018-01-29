
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.accolite.dao.BookingDao;
import com.accolite.dao.RoomDao;
import com.accolite.dao.SpecialRequestDao;
import com.accolite.dao.UserDao;
import com.accolite.model.User;
import com.accolite.service.UserService;

public class UserTest {
	
	@Mock
	private UserDao daoMock;
	
	@Mock
	private RoomDao roomDaoMock;
	
	@Mock
	private SpecialRequestDao specialRequestDaoMock;
	
	@Mock
	private BookingDao bookingDaoMock;

	
	@InjectMocks
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void loginTest() {
		User user = new User();
		user.setAdmin(false);
		user.setLocationId(1);
		user.setPassword("pass");
		user.setUserId(1);
		user.setUsername("Aashna");
		
		userService.userRegister(user);
		
		User result = userService.userLogin(user);
		Mockito.verify(daoMock).registerUser(user);
		Mockito.verify(daoMock).validateDetails(user);
		
		assertEquals(user, result);
	}
}
