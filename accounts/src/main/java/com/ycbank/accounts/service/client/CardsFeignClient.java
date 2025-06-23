package com.ycbank.accounts.service.client;


import com.ycbank.accounts.dto.CardsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFallBack.class)  // Specify the name of the service to connect to registered in Eureka
public interface CardsFeignClient {

/*    OpenFeign:
    Declarative REST client.
    You define Java interfaces annotated with mappings; Feign generates the implementation.
    Integrates well with Spring Cloud for service discovery and load balancing.*/

    @GetMapping("/api/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber);

}

