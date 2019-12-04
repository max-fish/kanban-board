package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CardDetailModel {
    private CardModel card;

    public CardDetailModel(CardModel card){
        this.card = card;
    }

    public CardModel getCard(){ return card; }
}
