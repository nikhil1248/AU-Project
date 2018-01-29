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

import com.accolite.dao.SpecialRequestDao;
import com.accolite.model.SpecialRequest;
import com.accolite.service.SpecialRequestService;

public class SpecialRequestTest {
	@Mock
	private SpecialRequestDao daoMock;

	@InjectMocks
	private SpecialRequestService specialRequestService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetSpecialRequests() {
		List<SpecialRequest> all = new ArrayList<>();
		SpecialRequest specialRequest = new SpecialRequest();
		specialRequest.setId(1);
		specialRequest.setTitle("SpecialRequest1");
		specialRequest.setDescription("sample SpecialRequest");
		specialRequest.setLocationID(1);
		specialRequest.setRoomId(1);
		specialRequest.setStart(new Date(2018, 1 , 26));
		specialRequest.setEnd(new Date(2018, 1 , 26));
		specialRequest.setStatus(false);

		all.add(specialRequest);

		Mockito.when(daoMock.getAllRequests(1)).thenReturn(all);

		List<SpecialRequest> result = specialRequestService.getAllRequests(1);

		Mockito.verify(daoMock).getAllRequests(1);
		
		assertEquals(all, result);
	}
}
