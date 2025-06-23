package com.ycbank.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/*@ConfigurationProperties(prefix = "accounts") // This annotation binds the properties defined in application.properties or application.yml with the prefix "accounts" to this DTO class.
public record AccountContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {

*//*    A record in Java 17 is a special type of class introduced to simplify the creation of immutable data carriers. Its main purpose is to reduce boilerplate code for classes that are primarily used to store data.

    Records automatically generate constructors, getters, equals(), hashCode(), and toString() methods.
    All fields are final and private.
    Records are ideal for DTOs, value objects, and simple data containers.*//*

}*/

//Converting Record to class to allow for mutability if needed in future
//Getter and Setter allows getting and setting values of the fields at runtime

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}