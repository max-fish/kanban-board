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

    public void revert()
    {
        boardModel.getGUI().getController().getTitle().setText(prevName);
    }

    public String toString()
    {
        return prevName + " renamed to " + newName;
    }
}
