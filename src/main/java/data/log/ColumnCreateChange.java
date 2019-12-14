package data.log;

import data.model.ColumnModel;

public class ColumnCreateChange extends ColumnChange{

    public ColumnCreateChange(ColumnModel columnModel)
    {
        super(columnModel);
    }

    public String toString()
    {
        return "Column " + columnModel.getName() + " created";
    }
}
