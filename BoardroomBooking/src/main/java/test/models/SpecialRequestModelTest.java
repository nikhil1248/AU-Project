package test.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accolite.model.SpecialRequest;

public class SpecialRequestModelTest {

	@Test
	public void testStatus() {
		SpecialRequest request = new SpecialRequest();
		boolean isActive = true;
		request.setStatus(isActive);
		
		assertEquals(isActive, request.getStatus());
	}
	
}
