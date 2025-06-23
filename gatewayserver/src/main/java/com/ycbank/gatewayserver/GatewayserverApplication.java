package com.ycbank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}


	@Bean
	public RouteLocator ycbankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()      //Starts building a set of routes for the Spring Cloud Gateway.
				.route(p -> p
						.path("/ycbank/accounts/**")
						.filters(f -> f.rewritePath("/ycbank/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config-> config.setName("accountsCircuitBreaker")  // adding Circuit Breaker filter for all apis under /ycbank/accounts
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://ACCOUNTS"))   //rewrites the path to remove the prefix, and forwards to the ACCOUNTS service using load balancing.

				.route(p -> p
						.path("/ycbank/loans/**")
						.filters(f -> f.rewritePath("/ycbank/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.retry(retryConfig -> retryConfig.setRetries(3) // adding Retry filter for all apis under /ycbank/loans which will retry 3 times when a failure occurs
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(500), 2,true)))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/ycbank/cards/**")
						.filters(f -> f.rewritePath("/ycbank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> {    // adding Rate Limiter filter for all apis under /ycbank/cards
									config.setRateLimiter(redisRateLimiter())
											.setKeyResolver(keyResolver());
								}))
						.uri("lb://CARDS")).build();
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1,1,1); // 1 request per second, burst capacity of 1
	}

	@Bean
	KeyResolver keyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous"); // Use the 'user' header as the key for rate limiting
	}

}
