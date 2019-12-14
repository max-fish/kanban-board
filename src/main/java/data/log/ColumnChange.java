package data.log;

import data.model.ColumnModel;

public abstract class ColumnChange implements Change{
    protected ColumnModel columnModel;

    public ColumnChange(ColumnModel columnModel)
    {
        this.columnModel = columnModel;
    }

    public void revert()
    {

    }
}
