package model;

import  java.time.LocalDate;

public class Card {
    public String title;
    public LocalDate dueDate;
    public String description;
    public String storyPoint;

    public Card(String title, LocalDate dueDate, String description, String storyPoint){
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.storyPoint = storyPoint;
    }

}
