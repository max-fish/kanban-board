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

    public void apply()
    {
        if(applied)
            return;

        columnModel.setNameWithoutTracking(newName);
        columnModel.getGUI().getController().getName().setText(newName);
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        columnModel.setNameWithoutTracking(prevName);
        columnModel.getGUI().getController().getName().setText(prevName);
        applied = false;
    }

    public String toString()
    {
        return prevName + " renamed to " + newName;
    }
}
