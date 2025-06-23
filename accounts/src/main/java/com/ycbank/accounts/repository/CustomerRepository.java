package com.ycbank.accounts.repository;


import com.ycbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // This interface will automatically provide CRUD operations for the Customer entity
    // No need to write any implementation code

    Optional<Customer> findByMobileNumber(String mobileNumber); //Non-Primary key based method
}
