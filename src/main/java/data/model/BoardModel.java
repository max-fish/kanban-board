package data.model;

import javax.smartcardio.Card;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class BoardModel {
    private String name;
    private List<ColumnModel> columnModels;
    private LocalDate creationDate;

    /**
     *
     */
    public BoardModel(String name)
    {
        this.name = name;
        columnModels = new ArrayList<>();
        creationDate = LocalDate.now();
    }

    public void addColumn(ColumnModel columnModel)
    {
        columnModels.add(columnModel);
    }

    public void deleteColumn(ColumnModel columnModel)
    {
        columnModels.remove(columnModel);
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
            if(col.getRole().equals("Completed Work")) { completedColumns.add(col); }
        }
        return completedColumns;
    }

    public List<ColumnModel> getWIPColumns() {
        List<ColumnModel> WIPcolumns = new ArrayList<>();
        for(ColumnModel col : columnModels){
            boolean isWIPColumn = col.getRole().equals("Work In Progress") || col.getRole().equals("On hold");
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
}
