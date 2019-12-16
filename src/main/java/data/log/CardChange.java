package data.log;

import data.model.CardModel;

public abstract class CardChange extends Change{
    protected transient CardModel cardModel;
    protected int cardId;

    public CardChange(CardModel cardModel)
    {
        this.cardModel = cardModel;
        cardId = cardModel.getID();
    }

    public void init()
    {
        this.cardModel = CardModel.getCardModelById(cardId);
    }
}
