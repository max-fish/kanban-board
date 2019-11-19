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

    public void deleteColumn(Column column)
    {
        columns.remove(column);
    }

    public boolean contains(Column column)
    {
        return columns.contains(column);
    }

    public boolean hasColumns()
    {
        if(columns.size() > 0)
            return true;
        else
            return false;
    }

    public List<Column> getColumns()
    {
        return columns;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
