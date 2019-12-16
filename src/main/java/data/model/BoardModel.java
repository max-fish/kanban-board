package data.model;

import utils.Constants;
import data.log.ActivityLogModel;
import data.log.BoardNameChange;
import ui.KanbanBoard;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardModel {
    private static HashMap<Integer, BoardModel> boards = new HashMap<>();
    private static int nextId = 1;

    private String name;
    private int id;
    private List<ColumnModel> columnModels;
    private List<ColumnModel> deletedColumns;
    private List<CardModel> deletedCards;
    private LocalDate creationDate;
    private ActivityLogModel activityLogModel;
    private transient KanbanBoard boardGUI;

    /**
     *
     */
    public BoardModel(String name)
    {
        this.name = name;
        columnModels = new ArrayList<>();
        creationDate = LocalDate.now();
        activityLogModel = new ActivityLogModel();

        while(boards.containsKey(nextId))
        {
            nextId++;
        }
        id = nextId;
        nextId++;

        boards.put(id, this);

        deletedColumns = new ArrayList<>();
        deletedCards = new ArrayList<>();
    }

    public void init(KanbanBoard boardGUI)
    {
        this.boardGUI = boardGUI;

        if(activityLogModel == null)
            activityLogModel = new ActivityLogModel();

        if(!boards.containsValue(this))
            boards.put(id, this);

        for(ColumnModel column : deletedColumns)
            ColumnModel.addToColumns(column);

        for(CardModel card : deletedCards)
            CardModel.addToCards(card);
    }

    public void addColumn(ColumnModel columnModel)
    {
        columnModels.add(columnModel);
    }

    public void deleteColumn(ColumnModel columnModel)
    {
        columnModels.remove(columnModel);
        deletedColumns.add(columnModel);
          System.out.println("Column deleted");
    }

    public boolean hasColumns()
    {
        return columnModels.size() > 0;
    }

    public List<ColumnModel> getColumns()
    {
        return columnModels;
    }

    public List<ColumnModel> getCompletedColumns() {
        List<ColumnModel> completedColumns = new ArrayList<>();
        for(ColumnModel col : columnModels){
            if(col.getRole() == Constants.ColumnRole.COMPLETED_WORK) { completedColumns.add(col); }
        }
        return completedColumns;
    }

    public List<ColumnModel> getWIPColumns() {
        List<ColumnModel> WIPcolumns = new ArrayList<>();
        for(ColumnModel col : columnModels){
            boolean isWIPColumn = col.getRole() == Constants.ColumnRole.WORK_IN_PROGRESS || col.getRole() == Constants.ColumnRole.ON_HOLD;
            if(isWIPColumn) { WIPcolumns.add(col); }
        }
        return WIPcolumns;
    }

    public boolean contains(ColumnModel columnModel)
    {
        return columnModels.contains(columnModel);
    }

    public void setName(String newName)
    {
        activityLogModel.addChange(new BoardNameChange(this, name, newName));
        this.name = newName;
    }

    public void setNameWithoutTracking(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getCreationDate()
    {
        return creationDate;
    }

    public boolean hasCompleteColumn()
    {
        return !getCompletedColumns().isEmpty();
    }

    public ActivityLogModel getActivityLogModel()
    {
        return activityLogModel;
    }

    public void setGUI(KanbanBoard boardGUI)
    {
        this.boardGUI = boardGUI;
    }

    public KanbanBoard getGUI()
    {
        return boardGUI;
    }

    public int getId()
    {
        return id;
    }

    public List<CardModel> getDeletedCards()
    {
        return deletedCards;
    }

    public List<ColumnModel> getDeletedColumns()
    {
        return deletedColumns;
    }

    public static BoardModel getBoardModelById(int boardId)
    {
        return boards.get(boardId);
    }
}
