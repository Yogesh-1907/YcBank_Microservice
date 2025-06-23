package com.ycbank.accounts.mapper;

import com.ycbank.accounts.dto.CustomerDetailsDto;
import com.ycbank.accounts.dto.CustomerDto;
import com.ycbank.accounts.entity.Customer;

public class CustomerMapper {

    // This method is used to map the Customer entity to CustomerDto
    //Retried from DB and set the values to CustomerDto to send to the client
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }

    // This method is used to map the CustomerDto to Customer entity
    //Extracted from client input and set the values to Customer entity to save in DB
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
