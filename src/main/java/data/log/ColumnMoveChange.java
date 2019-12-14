package data.log;

import data.model.ColumnModel;

public class ColumnMoveChange extends ColumnChange{
    private int prevPosition;
    private int newPosition;

    public ColumnMoveChange(ColumnModel columnModel, int prevPosition, int newPosition)
    {
        super(columnModel);
        this.prevPosition = prevPosition;
        this.newPosition = newPosition;
    }

    public String toString()
    {
        return "Column " + columnModel.getName() + " moved";
    }
}
