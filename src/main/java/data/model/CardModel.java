package data.model;

import java.time.LocalDate;

public class CardModel {
    // the id of the next card, ids increase chronologically
    public static int nextId = 1;

    private int id;
    private String title;
    private String description;
    private Integer storyPoints;
    private LocalDate creationDate;
    private LocalDate completedDate = null;

    /**
     *
     */
    public CardModel(String title, String description, Integer storyPoints)
    {
        this.title = title;
        this.description = description;
        this.storyPoints = storyPoints;

        id = nextId;
        nextId++;
        creationDate = LocalDate.now();
          System.out.println("The card was created: " + title + id);
    }

    public CardModel(String title)
    {
        this(title, "", 1);
    }

    public CardModel()
    {
        this("");
    }

    public int getID()
    {
        return id;
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

    public void setStoryPoint(int story){
        this.storyPoints = story;
    }

    public String getDescription()
    {
        return description;
    }

    public int getStoryPoint(){
        return storyPoints;
    }

    public LocalDate getCreationDate() { return creationDate; }

    public void setCompletedDate(LocalDate date) { completedDate = date; }

    public LocalDate getCompletedDate() { return completedDate; }
}
