package com.ycbank.accounts.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto-incrementing primary key
    @Column(name="customer_id") // Maps this field to the "customer_id" column in the database)
    private Long customerId;

    private String name;

    private String email;

    @Column(name="mobile_number")
    private String mobileNumber;

}
