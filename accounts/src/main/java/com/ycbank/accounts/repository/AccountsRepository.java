package com.ycbank.accounts.repository;


import com.ycbank.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
  // This interface will automatically provide CRUD operations for the Account entity
    Optional<Accounts> findByCustomerId(Long customerId); //Non-Primary key based method


    @Transactional // This annotation is used to indicate that the method should be executed within a transaction
    @Modifying  // This annotation is used to indicate that the method modifies the database
    void deleteByCustomerId(Long customerId);
}
