package com.ycbank.accounts.mapper;

import com.ycbank.accounts.dto.AccountsDto;
import com.ycbank.accounts.entity.Accounts;

public class AccountsMapper {

    //This method is used to map the Accounts entity to AccountsDto
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    //This method is used to map the AccountsDto to Accounts entity
    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
