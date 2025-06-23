package com.ycbank.accounts.service.impl;

import com.ycbank.accounts.dto.AccountsDto;
import com.ycbank.accounts.dto.CardsDto;
import com.ycbank.accounts.dto.CustomerDetailsDto;
import com.ycbank.accounts.dto.LoansDto;
import com.ycbank.accounts.entity.Accounts;
import com.ycbank.accounts.entity.Customer;
import com.ycbank.accounts.exception.ResourceNotFoundException;
import com.ycbank.accounts.mapper.AccountsMapper;
import com.ycbank.accounts.mapper.CustomerMapper;
import com.ycbank.accounts.repository.AccountsRepository;
import com.ycbank.accounts.repository.CustomerRepository;
import com.ycbank.accounts.service.ICustomerService;
import com.ycbank.accounts.service.client.CardsFeignClient;
import com.ycbank.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        if(loansDtoResponseEntity != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCard (mobileNumber);
        if(cardsDtoResponseEntity != null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}
