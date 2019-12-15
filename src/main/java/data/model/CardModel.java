package data.model;

import java.nio.file.LinkOption;
import java.time.LocalDate;

public class CardModel {
    // the id of the next card, ids increase chronologically
    public static int nextId = 1;

    private int id;
    private String title;
    private String description;
    private Integer storyPoints;
    private LocalDate creationDate;
    private LocalDate enterWIPDate = null;
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
    }

    public CardModel(String title)
    {
        this(title, "", 1);
    }

    public CardModel()
    {
        this("");
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

    public void setCreationDate(LocalDate date){ creationDate = date; }

    public void setEnterWIPDate(LocalDate date){ enterWIPDate = date; }

    public LocalDate getEnterWIPDate(){ return enterWIPDate; }
}
