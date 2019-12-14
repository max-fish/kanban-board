package data.log;

import data.model.ColumnModel;

public class ColumnNameChange extends ColumnChange{
    private String prevName;
    private String newName;

    public ColumnNameChange(ColumnModel columnModel, String prevName, String newName)
    {
        super(columnModel);
        this.prevName = prevName;
        this.newName = newName;
    }

    public String toString()
    {
        return prevName + " renamed to " + newName;
    }
}
