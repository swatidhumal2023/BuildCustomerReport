package com.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.customer.model.CustomerDetails;
import com.customer.repository.CustomerDetailsRepository;
import com.customer.service.CustomerService;

class BuildCustomerReportApplicationTests {

	@Mock
	private CustomerDetailsRepository repository;

	@InjectMocks
	private CustomerService service;

	private List<CustomerDetails> records;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		records = Arrays.asList(
				new CustomerDetails(1L, 2343225, 2345, "us_east", "RedTeam", "ProjectApple", 3445),				
				new CustomerDetails(2L, 1223456, 2345, "us_west", "BlueTeam", "ProjectBanana", 2211),
				new CustomerDetails(3L, 3244332, 2346, "us_east", "RedTeam", "ProjectApple", 3456),
				new CustomerDetails(4L, 1233456, 2346, "us_west", "BlueTeam", "ProjectBanana", 3312),
				new CustomerDetails(5L, 3244132, 2347, "us_east", "RedTeam", "ProjectCarrot", 3445),
				new CustomerDetails(6L, 1233457, 2347, "us_west", "BlueTeam", "ProjectBanana", 2445),
				new CustomerDetails(7L, 2343225, 2345, "us_east", "RedTeam", "ProjectApple1", 0));

		when(repository.findAll()).thenReturn(records);
	}

	@Test
	public void testGetNumberOfUniqueCustomerIdsByContractId() {
		Map<Integer, Integer> result = service.getNumberOfUniqueCustomerIdsByContractId();
		assertEquals(3, result.size());
		assertEquals(2, result.get(2345).intValue());		
	}

	@Test
	public void testGetUniqueCustomerIdsByGeozone() {
		Map<String, Integer> result = service.getNumberOfUniqueCustomerIdsByGeozone();
		assertEquals(2, result.size());
		assertEquals(3, result.get("us_east").intValue());
	}

	@Test
	public void testGetAverageBuildDurationByGeozone() {
		Map<String, Double> result = service.getAverageBuildDurationByGeozone();

		assertEquals(2, result.size());
		assertEquals(2586.5, result.get("us_east"), 0.01);
		//assertEquals(2656.00, result.get("us_west"), 0.01);
	}

	@Test
	public void testGetUniqueCustomerIdsForGeozone() {
		Map<String, Set<Integer>> result = service.getUniqueCustomerIdsForGeozone();

		assertEquals(2, result.size());
		assertEquals(Set.of(2343225, 3244332, 3244132), result.get("us_east"));
		//assertEquals(Set.of(1223456, 1233456, 1233457), result.get("us_west"));
	}

}
