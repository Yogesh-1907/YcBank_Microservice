package com.ycbank.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDetailsDto {

    @NotEmpty(message = "Name cannot be null and empty")
    @Size(min=5, max=30, message = "Name should be between 5 to 30 characters")
    private String name;

    @NotEmpty (message = "Email cannot be null and empty")
    @Email(message = "Email should be a valid value")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be a valid 10 digit number")
    private String mobileNumber;

    private AccountsDto accountsDto;

    private LoansDto loansDto;

    private CardsDto cardsDto;
}
