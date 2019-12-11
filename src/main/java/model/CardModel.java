package model;

public class CardModel {
    // the id of the next card, ids increase chronologically
    public static int nextId = 1;

    private int id;
    private String title;
    private String description;
    private String storyPoint;

    private transient ColumnModel parentColumn;

    /**
     *
     */
    public CardModel(String title, String description, String storyPoint, ColumnModel parentColumn)
    {
        this.title = title;
        this.description = description;
        this.storyPoint = storyPoint;
        this.parentColumn = parentColumn;

        id = nextId;
        nextId++;
          System.out.println("The card was created: " + id);
    }

    public CardModel(String title, ColumnModel parentColumn)
    {
        this(title, "", "", parentColumn);
    }

    public CardModel(ColumnModel parentColumn)
    {
        this("New KanbanCard", parentColumn);
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ColumnModel getColumn(){
        return parentColumn;
    }

    public void setColumn(ColumnModel parentColumn)
    {
        this.parentColumn = parentColumn;
    }
}
