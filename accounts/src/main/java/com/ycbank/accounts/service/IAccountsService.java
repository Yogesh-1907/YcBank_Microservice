package com.ycbank.accounts.service;


import com.ycbank.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * This method is used to create a new account for a customer.
     *
     * @param customerDto the customer details(CustomerDto object)
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
