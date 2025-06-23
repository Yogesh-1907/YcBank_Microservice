package com.ycbank.loans.service;

import com.ycbank.loans.constants.LoanConstants;
import com.ycbank.loans.dto.LoansDto;
import com.ycbank.loans.entity.Loans;
import com.ycbank.loans.exception.LoanAlreadyExistsException;
import com.ycbank.loans.exception.ResourceNotFoundException;
import com.ycbank.loans.mapper.LoansMapper;
import com.ycbank.loans.repository.LoansRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoansRepositoy loansRepositoy;

    /**
     * @param mobileNumber
     */
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> existingLoan = loansRepositoy.findByMobileNumber(mobileNumber);
        if (existingLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with mobilenumber " + mobileNumber);
        }
        loansRepositoy.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.New_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.New_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {

        Loans loans = loansRepositoy.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.toLoanDto(loans, new LoansDto());

    }

    /**
     * @param loansDto
     * @return
     */
    @Override

    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepositoy.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.toEntity(loans , loansDto);
        loansRepositoy.save(loans);
        return  true;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepositoy.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepositoy.deleteById(loans.getLoanId());
        return true;
    }

}
