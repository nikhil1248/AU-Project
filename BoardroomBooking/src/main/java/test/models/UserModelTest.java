package test.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accolite.model.User;

public class UserModelTest {

	@Test
	public void testUserID() {
		User user = new User();
		int id = 1;
		user.setUserId(id);
		
		assertEquals(id, user.getUserId());
	}
	
	@Test
	public void testLocationID() {
		User user = new User();
		int locationId = 1;
		user.setLocationId(locationId);
		
		assertEquals(locationId, user.getLocationId());
	}
	
	@Test
	public void testUsername() {
		User user = new User();
		String name = "Admin";
		user.setUsername(name);
		
		assertEquals(name, user.getUsername());
	}
	
	@Test
	public void testPassword() {
		User user = new User();
		String pass = "pass";
		user.setPassword(pass);
		
		assertEquals(pass, user.getPassword());
	}
	
	@Test
	public void testIfAdmin() {
		User user = new User();
		boolean isAdmin = false;
		user.setAdmin(isAdmin);
		
		assertEquals(isAdmin, user.isAdmin());
	}
	
}
