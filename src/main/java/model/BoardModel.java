package model;

import java.util.List;
import java.util.ArrayList;

public class BoardModel {
    private String name;
    private List<ColumnModel> columnModels;

    /**
     *
     */
    public BoardModel(String name)
    {
        this.name = name;
        columnModels = new ArrayList<>();
    }

    public void addColumn(ColumnModel columnModel)
    {
        columnModels.add(columnModel);
    }

    public void deleteColumn(ColumnModel columnModel)
    {
        columnModels.remove(columnModel);
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
