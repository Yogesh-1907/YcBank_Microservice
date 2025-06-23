package com.ycbank.loans.repository;

import com.ycbank.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepositoy extends JpaRepository<Loans, Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);
    // Additional query methods can be defined here if needed

    Optional<Loans> findByLoanNumber(String loanNumber);

}
