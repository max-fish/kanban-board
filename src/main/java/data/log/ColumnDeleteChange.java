package data.log;

import data.model.ColumnModel;

public class ColumnDeleteChange extends ColumnChange{
    private int lastPosition;

    public ColumnDeleteChange(ColumnModel columnModel, int lastPosition)
    {
        super(columnModel);
        this.lastPosition = lastPosition;
    }

    public void apply()
    {
        if(applied)
            return;

        columnModel.getParent().deleteColumn(columnModel);
        columnModel.getParent().getGUI().getController().deleteColumn(columnModel.getGUI());
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        columnModel.getParent().getDeletedColumns().remove(columnModel);
        columnModel.getParent().getGUI().getController().insertColumn(columnModel, lastPosition);
        applied = false;
    }

    public String toString()
    {
        return "Column " + columnModel.getName() + " deleted";
    }
}
