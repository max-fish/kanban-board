package data.model;

import jdk.vm.ci.meta.Local;

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

    //private ColumnModel parent;

    /**
     *
     */
    public CardModel(String title, String description, Integer storyPoints/*, ColumnModel parent*/)
    {
        this.title = title;
        this.description = description;
        this.storyPoints = storyPoints;
        //this.parent = parent;

        id = nextId;
        nextId++;
        creationDate = LocalDate.now();
          System.out.println("The card was created: " + id);
    }

    public CardModel(String title/*, ColumnModel parent*/)
    {
        this(title, "", 1/*, parent*/);
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

    public void setStoryPoint(Integer story){
        this.storyPoints = story;
    }

    public String getDescription(){
        return description;
    }

    public Integer getStoryPoints(){
        return storyPoints;
    }

    public LocalDate getCreationDate() { return creationDate; }

    public void setCompletedDate(LocalDate date) { completedDate = date; }

    public LocalDate getCompletedDate() { return completedDate; }

    /*public ColumnModel getParent(){
        return parent;
    }*/
}
