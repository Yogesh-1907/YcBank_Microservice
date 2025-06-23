package com.ycbank.cards.repository;

import com.ycbank.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByMobileNumber(String mobileNumber);

    Optional<Cards> findByCardNumber(String cardNumber);
   // CardsRepository (which extends JpaRepository<Cards, Long>) is a Data Access Layer interface.
    // It provides CRUD operations and query methods for the Cards entity, interacting directly with the database.
}
