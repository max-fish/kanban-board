package data.log;

import ui.KanbanBoard;

public abstract class BoardChange implements Change{
    protected transient KanbanBoard board;

    public BoardChange(KanbanBoard board)
    {
        this.board = board;
    }
}
