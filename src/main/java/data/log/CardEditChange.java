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

    public CardEditChange(CardModel cardModel, String prevName, String newName)
    {
        super(cardModel);
        this.prevName = prevName;
        this.newName = newName;
        this.prevDescription = cardModel.getTitle();
        this.newDescription = cardModel.getTitle();
        this.prevStoryPoint = cardModel.getStoryPoint();
        this.newStoryPoint = cardModel.getStoryPoint();
    }

    public void revert()
    {

    }

    public String toString()
    {
        return "Card " + cardModel.getTitle() + " edited";
    }
}
