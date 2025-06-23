package com.ycbank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


// Dto class is used to transfer data between layers so that we can avoid exposing the entity class directly
//which reduces network load and also reduces the risk of exposing sensitive data
// It is a good practice to use Dto class to transfer data between layers
// Only the required fields are included in the Dto class which logically need to be transferred or send to client
@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null and empty")
    @Size(min=5, max=30, message = "Name should be between 5 to 30 characters")
    private String name;

    @NotEmpty (message = "Email cannot be null and empty")
    @Email(message = "Email should be a valid value")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be a valid 10 digit number")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
