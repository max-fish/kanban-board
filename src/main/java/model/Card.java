package model;

public class Card{
    // the id of the next card, ids increase chronologically
    public static int nextId = 1;

    private int id;
    private String title;
    private String description;

    /**
     *
     */
    public Card(String title, String description)
    {
        this.title = title;
        this.description = description;

        id = nextId;
        nextId++;
    }

    public Card(String title)
    {
        this(title, "");
    }

    public Card()
    {
        this("New Card", "");
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
