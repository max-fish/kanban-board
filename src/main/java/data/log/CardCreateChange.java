package data.log;

import data.model.CardModel;

public class CardCreateChange extends CardChange{

    public CardCreateChange(CardModel cardModel)
    {
        super(cardModel);
    }

    public String toString()
    {
        return "Card created";
    }
}
