package com.zadania.note3.observe;

import com.zadania.note3.data.CardData;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers;   // Все обозреватели

    public Publisher(List<Observer> observers) {
        this.observers = observers;
    }

    public Publisher() {
        observers = new ArrayList<>();
    }

    // Подписать
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    // Отписать
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    // Разослать событие
    public void notifySingle(CardData cardData) {
        for (Observer observer : observers) {
            observer.updateCardData(cardData);
            unsubscribe(observer);
        }
    }
}
