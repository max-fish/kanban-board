package data.log;

import data.model.CardModel;

public class CardCreateChange extends CardChange{

    public CardCreateChange(CardModel cardModel)
    {
        super(cardModel);
    }

    public void apply()
    {
        if(applied)
            return;

        cardModel.getParent().getParent().getDeletedCards().remove(cardModel);
        cardModel.getParent().getGUI().getController().makeNewCard(cardModel);
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        cardModel.getParent().getParent().getDeletedCards().add(cardModel);
        cardModel.getGUI().getController().removeCard(cardModel.getGUI());
        applied = false;
    }

    public String toString()
    {
        return "Card created";
    }
}
