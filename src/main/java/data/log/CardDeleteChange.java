package data.log;

import data.model.CardModel;

public class CardDeleteChange extends CardChange{
    private int lastPosition;

    public CardDeleteChange(CardModel cardModel, int lastPosition)
    {
        super(cardModel);
        this.lastPosition = lastPosition;
    }

    public void apply()
    {
        if(applied)
            return;

        cardModel.getParent().getParent().getDeletedCards().add(cardModel);
        cardModel.getGUI().getController().removeCard(cardModel.getGUI());
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        cardModel.getParent().getParent().getDeletedCards().remove(cardModel);
        cardModel.getParent().getGUI().getController().insertCard(cardModel, lastPosition);
        applied = false;
    }

    public String toString()
    {
        return "Card " + cardModel.getTitle() + " deleted";
    }
}
