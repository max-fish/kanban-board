package model;

public class CardModel {
    // the id of the next card, ids increase chronologically
    public static int nextId = 1;

    private int id;
    private String title;
    private String description;
    private String storyPoint;

    //private ColumnModel parent;

    /**
     *
     */
    public CardModel(String title, String description, String storyPoint/*, ColumnModel parent*/)
    {
        this.title = title;
        this.description = description;
        this.storyPoint = storyPoint;
        //this.parent = parent;

        id = nextId;
        nextId++;
          System.out.println("The card was created: " + id);
    }

    public CardModel(String title/*, ColumnModel parent*/)
    {
        this(title, "", ""/*, parent*/);
    }

    public CardModel(/*ColumnModel parent*/)
    {
        this("New KanbanCard"/*,parent*/);
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String get_title(){
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStoryPoint(String story){
        this.storyPoint = story;
    }

    public String getDescription(){
        return description;
    }

    public String getStoryPoint(){
        return storyPoint;
    }

    /*public ColumnModel getParent(){
        return parent;
    }*/
}
