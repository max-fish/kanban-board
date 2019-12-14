package data.log;

import ui.KanbanBoard;

public class BoardNameChange extends BoardChange{
    private String prevName;
    private String newName;

    public BoardNameChange(KanbanBoard board, String prevName, String newName)
    {
        super(board);
        this.prevName = prevName;
        this.newName = newName;
    }

    public void revert()
    {
        board.getController().getTitle().setText(prevName);
    }

    public String toString()
    {
        return prevName + " renamed to " + newName;
    }
}
