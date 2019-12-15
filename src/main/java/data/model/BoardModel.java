package data.model;

import utils.Constants;
import data.log.ActivityLogModel;
import ui.KanbanBoard;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class BoardModel {
    private String name;
    private List<ColumnModel> columnModels;
    private LocalDate creationDate;
    private ActivityLogModel activityLogModel;
    private KanbanBoard boardGUI;

    /**
     *
     */
    public BoardModel(String name)
    {
        this.name = name;
        columnModels = new ArrayList<>();
        creationDate = LocalDate.now();
        activityLogModel = new ActivityLogModel();
    }

    public void init()
    {
        if(activityLogModel == null)
            activityLogModel = new ActivityLogModel();
    }

    public void addColumn(ColumnModel columnModel)
    {
        columnModels.add(columnModel);
    }

    public void deleteColumn(ColumnModel columnModel)
    {
        columnModels.remove(columnModel);
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

    public boolean contains(ColumnModel columnModel) { return columnModels.contains(columnModel); }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getCreationDate() { return creationDate; }

    public boolean hasCompleteColumn() { return !getCompletedColumns().isEmpty(); }

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
}
