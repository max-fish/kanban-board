package data.log;

import data.model.ColumnModel;

public class ColumnDeleteChange extends ColumnChange{
    private int lastPosition;

    public ColumnDeleteChange(ColumnModel columnModel, int lastPosition)
    {
        super(columnModel);
        this.lastPosition = lastPosition;
    }
}
