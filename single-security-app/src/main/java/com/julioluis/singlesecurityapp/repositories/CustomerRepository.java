package com.julioluis.singlesecurityapp.repositories;

import com.julioluis.singlesecurityapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findCustomerByUsername(String username);
}
