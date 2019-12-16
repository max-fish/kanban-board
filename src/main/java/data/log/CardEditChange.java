package data.log;

import data.model.CardModel;

public class CardEditChange extends CardChange{
    private String prevName;
    private String newName;
    private String prevDescription;
    private String newDescription;
    private int prevStoryPoint;
    private int newStoryPoint;

    public CardEditChange(CardModel cardModel, String prevName, String newName,
                                                String prevDescription, String newDescription,
                                                int prevStoryPoint, int newStoryPoint)
    {
        super(cardModel);
        this.prevName = prevName;
        this.newName = newName;
        this.prevDescription = prevDescription;
        this.newDescription = newDescription;
        this.prevStoryPoint = prevStoryPoint;
        this.newStoryPoint = newStoryPoint;
    }

    public void apply()
    {
        if(applied)
            return;

        cardModel.setTitle(newName);
        cardModel.setDescription(newDescription);
        cardModel.setStoryPoint(newStoryPoint);

        cardModel.getGUI().getController().getTitle().setText(newName);
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        cardModel.setTitle(prevName);
        cardModel.setDescription(prevDescription);
        cardModel.setStoryPoint(prevStoryPoint);

        cardModel.getGUI().getController().getTitle().setText(prevName);
        applied = false;
    }

    public String toString()
    {
        return "Card " + cardModel.getTitle() + " edited";
    }
}
