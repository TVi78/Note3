package com.zadania.note3.data;

import com.zadania.note3.data.CardData;

public interface CardsSource {
    CardsSource init(CardSourceResponse cardSourceResponse);
    CardData getCardData(int position);
    int size();
    void deleteCardData(int position);
    void updateCardData(int position, CardData cardData);
    void addCardData(CardData cardData);
    void clearCardData();
}
