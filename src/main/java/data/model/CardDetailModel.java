package data.model;

import data.model.CardModel;

public class CardDetailModel {
    private CardModel card;

    public CardDetailModel(CardModel card){
        this.card = card;
    }

    public CardModel getCardModel(){ return card; }
}
