package com.ycbank.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be a valid 10 digit number")
    private Long accountNumber;

    @NotEmpty(message = "account type cannot be empty or null")
    private String accountType;

    @NotEmpty(message = "branch address cannot be empty or null")
    private String branchAddress;


}
