package data.log;

import data.model.CardModel;

public abstract class CardChange implements Change{
    protected CardModel cardModel;

    public CardChange(CardModel cardModel)
    {
        this.cardModel = cardModel;
    }
}
