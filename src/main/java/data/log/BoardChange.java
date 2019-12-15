package data.log;

import data.model.BoardModel;

public abstract class BoardChange implements Change{
    protected transient BoardModel boardModel;

    public BoardChange(BoardModel boardModel)
    {
        this.boardModel = boardModel;
    }
}
