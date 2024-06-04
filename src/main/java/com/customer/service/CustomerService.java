package com.customer.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.customer.model.CustomerDetails;
import com.customer.repository.CustomerDetailsRepository;

@Service
public class CustomerService {

	private final CustomerDetailsRepository repository;

	public CustomerService(CustomerDetailsRepository repository) {
		this.repository = repository;
	}

	public void saveAll(List<CustomerDetails> records) {
		repository.saveAll(records);
	}

	// Get the number of unique customerId for each contractId
	public Map<Integer, Integer> getNumberOfUniqueCustomerIdsByContractId() {
		List<CustomerDetails> records = repository.findAll();
		Map<Integer, Integer> numberOfCustomers = new HashMap<>();
		Map<Integer, Set<Integer>> result = new HashMap<>();
		
		for (CustomerDetails record : records) {
			Integer contractId = record.getContractId();
			int customerId = record.getCustomerId();
			// If Map don't have contract key then it will initialize new set			
			  if (!result.containsKey(contractId)) {
				  result.put(contractId, new HashSet<>());
			  }			 
			result.get(contractId).add(customerId);						
		}		
		//Get the number of unique customer Ids for each contractId
		for (Integer contractId : result.keySet()) {
			numberOfCustomers.put(contractId, result.get(contractId).size());
		}
		
		return numberOfCustomers;
	}    

	// Get number of unique customerId for each geozone
	public Map<String, Integer> getNumberOfUniqueCustomerIdsByGeozone() {
		List<CustomerDetails> records = repository.findAll();
		Map<String, Set<Integer>> result = new HashMap<>();
		Map<String, Integer> numberOfCustomers = new HashMap<>();

		for (CustomerDetails record : records) {
			String geozone = record.getGeozone();
			int customerId = record.getCustomerId();

			if (!result.containsKey(geozone)) {
				result.put(geozone, new HashSet<>());
			}
			result.get(geozone).add(customerId);
		}
		
		//Get the number of unique customer Ids for each geozone
		for (String contractId : result.keySet()) {
			numberOfCustomers.put(contractId, result.get(contractId).size());
		}

		return numberOfCustomers;
	}

	// Get average build duration for each geozone
	public Map<String, Double> getAverageBuildDurationByGeozone() {
		List<CustomerDetails> records = repository.findAll();
		Map<String, Integer> totalDuration = new HashMap<>();
		Map<String, Integer> count = new HashMap<>();

		for (CustomerDetails record : records) {
			String geozone = record.getGeozone();
			int duration = record.getBuildDuration();

			if (!totalDuration.containsKey(geozone)) {
				totalDuration.put(geozone, 0);
				count.put(geozone, 0);
			}

			totalDuration.put(geozone, totalDuration.get(geozone) + duration);
			count.put(geozone, count.get(geozone) + 1);
		}

		Map<String, Double> result = new HashMap<>();
		for (String geozone : totalDuration.keySet()) {
			result.put(geozone, totalDuration.get(geozone) / (double) count.get(geozone));
		}

		return result;
	}

	//Get the list of unique customerId for each geozone
	public Map<String, Set<Integer>> getUniqueCustomerIdsForGeozone() {
		List<CustomerDetails> records = repository.findAll();
		Map<String, Set<Integer>> result = new HashMap<>();

		for (CustomerDetails record : records) {
			String geozone = record.getGeozone();
			int customerId = record.getCustomerId();

			if (!result.containsKey(geozone)) {
				result.put(geozone, new HashSet<>());
			}
			result.get(geozone).add(customerId);
		}

		return result;
	}
}
