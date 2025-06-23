package com.ycbank.accounts;

import com.ycbank.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients // This annotation enables the use of OpenFeign clients for inter-service communication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDto.class}) // This annotation enables the use of properties defined in application.properties or application.yml
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Micro-Service REST API DOC",
				version = "1.0",
				description = "API documentation for the Accounts Service in the YCBank application. " +
						"This service handles customer account management, including account creation, retrieval, and deletion.",
				contact = @Contact(
					name = "YCBank",
					email = "ycbank@support.com"
				)

		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
