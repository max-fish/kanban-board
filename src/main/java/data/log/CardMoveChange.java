package data.log;

import data.model.CardModel;
import data.model.ColumnModel;

public class CardMoveChange extends CardChange{
    private int prevPosition;
    private ColumnModel prevColumn;
    private int newPosition;
    private ColumnModel newColumn;

    public CardMoveChange(CardModel cardModel, int prevPosition, ColumnModel prevColumn,
                                                int newPosition, ColumnModel newColumn)
    {
        super(cardModel);
        this.prevPosition = prevPosition;
        this.prevColumn = prevColumn;
        this.newPosition = newPosition;
        this.newColumn = newColumn;
    }
}
