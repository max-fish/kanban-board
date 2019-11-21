package model;

public class Card{
    // the id of the next card, ids increase chronologically
    public static int nextId = 1;

    private int id;
    private String title;
    private String description;
    private Column parentCol;
    private String  storyPoint;

    /**
     *
     */
    public Card(String title, String description, Column col, String story)
    {
        this.title = title;
        this.description = description;
        this.parentCol = col;
        this.storyPoint = story;

        id = nextId;
        nextId++;
    }

    public Card(String title, Column col)
    {
        this(title, "", col, "");
    }

    public Card()
    {
        this("New Card", "", null, "");
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStoryPoint (String story){
        this.storyPoint = story;
    }

    public Column getColumn(){
        return this.parentCol;
    }
}
