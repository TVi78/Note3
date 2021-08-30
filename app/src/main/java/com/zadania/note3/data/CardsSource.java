package com.zadania.note3.data;

import com.zadania.note3.data.CardData;

public interface CardsSource {
    CardData getCardData(int position);

    int size();
}
