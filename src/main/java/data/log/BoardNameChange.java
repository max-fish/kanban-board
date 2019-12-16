package data.log;

import data.model.BoardModel;

public class BoardNameChange extends BoardChange{
    private String prevName;
    private String newName;

    public BoardNameChange(BoardModel boardModel, String prevName, String newName)
    {
        super(boardModel);
        this.prevName = prevName;
        this.newName = newName;
    }

    public void apply()
    {
        if(applied)
            return;

        boardModel.getGUI().getController().getTitle().setText(newName);
        boardModel.setNameWithoutTracking(newName);
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        boardModel.getGUI().getController().getTitle().setText(prevName);
        boardModel.setNameWithoutTracking(prevName);
        applied = false;
    }

    public String toString()
    {
        return prevName + " renamed to " + newName;
    }
}
