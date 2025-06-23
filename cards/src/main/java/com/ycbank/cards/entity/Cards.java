package com.ycbank.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @ToString @NoArgsConstructor
public class Cards extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto-increment strategy means the database will automatically generate a unique value for this field.
    private Long cardId;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private String totalLimit;

    private String amountUsed;

    private String availableAmount;


}
