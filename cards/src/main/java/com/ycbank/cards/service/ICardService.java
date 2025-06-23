package com.ycbank.cards.service;

import com.ycbank.cards.dto.CardsDto;

public interface ICardService {

  // ICardService is a Service Layer interface. It defines business logic operations
    // (like create, fetch, update, delete card) that use the repository to perform higher-level tasks,
    // often involving validation, mapping, or additional processing.

    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
