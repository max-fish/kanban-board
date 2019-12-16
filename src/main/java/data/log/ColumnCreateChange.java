package data.log;

import data.model.ColumnModel;

public class ColumnCreateChange extends ColumnChange{

    public ColumnCreateChange(ColumnModel columnModel)
    {
        super(columnModel);
    }

    public void apply()
    {
        if(applied)
            return;

        columnModel.getParent().getDeletedColumns().remove(columnModel);
        columnModel.getParent().getGUI().getController().makeNewColumn(columnModel);
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        columnModel.getParent().deleteColumn(columnModel);
        columnModel.getParent().getGUI().getController().deleteColumn(columnModel.getGUI());
        applied = false;
    }

    public String toString()
    {
        return "Column " + columnModel.getName() + " created";
    }
}
