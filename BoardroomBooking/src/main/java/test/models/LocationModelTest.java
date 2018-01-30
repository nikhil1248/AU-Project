package test.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accolite.model.Location;

public class LocationModelTest {

	@Test
	public void testId() {
		Location location = new Location();
		int id = 1;
		location.setLocationId(id);
		
		assertEquals(id, location.getLocationId());
	}
	
	@Test
	public void testLocationName() {
		Location location = new Location();
		String name = "Delhi";
		location.setLocationName(name);
		
		assertEquals(name, location.getLocationName());
	}
	
}
