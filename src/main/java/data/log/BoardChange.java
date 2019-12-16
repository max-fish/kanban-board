package data.log;

import data.model.BoardModel;

public abstract class BoardChange extends Change{
    protected transient BoardModel boardModel;
    private int boardId;

    public BoardChange(BoardModel boardModel)
    {
        this.boardModel = boardModel;
        boardId = boardModel.getId();
    }

    public void init()
    {
        this.boardModel = BoardModel.getBoardModelById(boardId);
    }
}
