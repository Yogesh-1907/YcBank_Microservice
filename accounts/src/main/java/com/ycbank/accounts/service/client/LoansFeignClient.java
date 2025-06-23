package com.ycbank.accounts.service.client;


import com.ycbank.accounts.dto.CardsDto;
import com.ycbank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback = LoansFallBack.class)  // Specify the name of the service to connect to registered in Eureka
public interface LoansFeignClient {

/*    OpenFeign:
    Declarative REST client.
    You define Java interfaces annotated with mappings; Feign generates the implementation.
    Integrates well with Spring Cloud for service discovery and load balancing.*/

    @GetMapping("/api/loans/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);

}

