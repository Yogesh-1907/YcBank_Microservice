package com.ycbank.accounts.service.client;

import com.ycbank.accounts.dto.CardsDto;
import com.ycbank.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient {

    @Override
    public ResponseEntity<CardsDto> fetchCard(String mobileNumber) {
        return null;
    }

/*  In real-world projects, fallback methods typically:
    Return a default or empty response object.
    Return cached or previously known data.
    Log the error for monitoring and debugging.
    Return a user-friendly error message or status.
    Trigger alerts or metrics for system health monitoring.*/

}
