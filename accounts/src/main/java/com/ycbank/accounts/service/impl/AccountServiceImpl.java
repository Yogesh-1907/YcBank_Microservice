package com.ycbank.accounts.service.impl;

import com.ycbank.accounts.constants.AccountsConstants;
import com.ycbank.accounts.dto.AccountsDto;
import com.ycbank.accounts.dto.CustomerDto;
import com.ycbank.accounts.entity.Accounts;
import com.ycbank.accounts.entity.Customer;
import com.ycbank.accounts.exception.CustomerAlreadyExistsException;
import com.ycbank.accounts.exception.ResourceNotFoundException;
import com.ycbank.accounts.mapper.AccountsMapper;
import com.ycbank.accounts.mapper.CustomerMapper;
import com.ycbank.accounts.repository.AccountsRepository;
import com.ycbank.accounts.repository.CustomerRepository;
import com.ycbank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    //Here we are accepting the customerDto object whic is DTO object
    //but we need to store entity object in the database
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setCreatedBy("System");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
// Spring Data JPA handles the following steps automatically:
// 1. Creating a connection with the database
// 2. Beginning a transaction
// 3. Creating and executing the SQL statement
// 4. Saving the data to the database
// 5. Committing the transaction and closing the connection

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("System");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
               ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
       );

       Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "mobileNumber", customer.getCustomerId().toString())
       );

      CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
      customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
      return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        //First set boolean isUpdated to false
        boolean isUpdated = false;
        //Then check if the accountsDto object is null or not by fetching it from customerDto
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto!=null) {
            //find accounts from db by account number using accountsDto details from input
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            //mapping the accountsDto object to accounts entity
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts); //save updated accounts entity

            //Then find customer from db by customerId using accounts entity
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
           //
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer); //save updated customer entity
            isUpdated=true;  // set isUpdated to true if both accounts and customer are updated

        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
