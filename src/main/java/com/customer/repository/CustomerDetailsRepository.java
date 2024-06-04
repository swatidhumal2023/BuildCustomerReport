package com.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.model.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {
}
