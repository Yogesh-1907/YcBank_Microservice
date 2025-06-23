package com.ycbank.loans.service;

import com.ycbank.loans.dto.LoansDto;

public interface ILoanService {

// We are creating a service interface for Loan operations based on mobile number.
// as we have a unique mobile number for each customer while JpaRepository provides methods for CRUD operations based on ID.


     void createLoan(String mobileNumber);

     LoansDto fetchLoan(String mobileNumber);

     boolean updateLoan(LoansDto loansDto);

     boolean deleteLoan(String mobileNumber);

}
