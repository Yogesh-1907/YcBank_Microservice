package com.ycbank.gatewayserver.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error Occurred. Please try after some time or contact support team!!");
    }
    //In real world application, you can redirect to a support page or provide more detailed instructions.
    //or trigger an alert to the support team.
}
