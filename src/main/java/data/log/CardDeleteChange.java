package data.log;

import data.model.CardModel;

public class CardDeleteChange extends CardChange{
    private int lastPosition;

    public CardDeleteChange(CardModel cardModel, int lastPosition)
    {
        super(cardModel);
        this.lastPosition = lastPosition;
    }
}
