package com.ycbank.cards.service.impl;

import com.ycbank.cards.constants.CardsConstants;
import com.ycbank.cards.dto.CardsDto;
import com.ycbank.cards.entity.Cards;
import com.ycbank.cards.exception.CardAlreadyExistException;
import com.ycbank.cards.exception.ResourceNotFoundException;
import com.ycbank.cards.mapper.CardsMapper;
import com.ycbank.cards.repository.CardsRepository;
import com.ycbank.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

  private CardsRepository cardsRepository;


    /**
     * @param mobileNumber
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> existingCard = cardsRepository.findByMobileNumber(mobileNumber);
        if(existingCard.isPresent()) {
            throw new RuntimeException("Card already exists for this mobile number");
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 10000000000000l + new Random().nextInt(900000000);
        newCard.setCardNumber(String.valueOf(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CREDIT_LIMIT);
        newCard.setAvailableAmount(CardsConstants.NEW_CREDIT_LIMIT);
        newCard.setAmountUsed("0");
        return newCard;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("card", "mobileNumber", mobileNumber)
        );

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    /**
     * @param cardsDto
     * @return
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new CardAlreadyExistException("Card", "cardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto, card);
        cardsRepository.save(card);
        return true;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
       Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("card", "mobileNumber", mobileNumber)
        );
        cardsRepository.delete(cards);
        return true;
    }
}
