package com.customer.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

	/*
	 * @PostMapping("/upload") public void uploadCustomerDetailss(@RequestBody
	 * List<CustomerDetails> records) { service.saveAll(records); }
	 */

    @GetMapping("/report/getNumberOfUniqueCustomerIdsByContractId")    
    public Map<Integer, Integer> getNumberOfUniqueCustomerIdsByContractId() {
        return service.getNumberOfUniqueCustomerIdsByContractId();
    }

    @GetMapping("/report/getNumberOfUniqueCustomerIdsByGeozone")
    public Map<String, Integer> getNumberOfUniqueCustomerIdsByGeozone() {
        return service.getNumberOfUniqueCustomerIdsByGeozone();
    }

    @GetMapping("/report/getAverageBuildDurationByGeozone")
    public Map<String, Double> getAverageBuildDurationByGeozone() {
        return service.getAverageBuildDurationByGeozone();
    }

    @GetMapping("/report/getUniqueCustomerIdsForGeozone")
    public Map<String, Set<Integer>> getUniqueCustomerIdsForGeozone() {
        return service.getUniqueCustomerIdsForGeozone();
    }
}
