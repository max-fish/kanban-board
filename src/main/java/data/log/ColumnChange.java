package data.log;

import data.model.ColumnModel;

public abstract class ColumnChange extends Change{
    protected transient ColumnModel columnModel;
    private int columnId;

    public ColumnChange(ColumnModel columnModel)
    {
        this.columnModel = columnModel;
        columnId = columnModel.getId();
    }

    public void init()
    {
        this.columnModel = ColumnModel.getColumnModelById(columnId);
    }
}
