package data.log;

import data.model.CardModel;

public abstract class CardChange implements Change{
    protected transient CardModel cardModel;

    public CardChange(CardModel cardModel)
    {
        this.cardModel = cardModel;
    }

    public void revert()
    {

    }
}
