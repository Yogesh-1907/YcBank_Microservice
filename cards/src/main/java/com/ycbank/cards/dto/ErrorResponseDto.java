package com.ycbank.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
/*@Schema(
        description = "Error response DTO for API error handling"
)*/
public class ErrorResponseDto {

/*    @Schema(   // Swagger annotation for API documentation
            description = "API path where the error occurred"
    )*/
    private String apiPath;

    private HttpStatus errorCode;

    private String errorMessage;

    private LocalDate errorTime;

}
