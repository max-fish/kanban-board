package model;

import java.util.List;
import java.util.ArrayList;

public class Board{
    private String name;
    private List<Column> columns;

    /**
     *
     */
    public Board(String name)
    {
        this.name = name;
        columns = new ArrayList<>();
    }

    public void addColumn(Column column)
    {
        columns.add(column);
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
