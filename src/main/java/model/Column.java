package model;

import java.util.List;
import java.util.ArrayList;

public class Column{
    private String name;
    private String role;
    private List<Card> cards;
    private Board parentBoard;

    /**
     *
     */
    public Column(Board parentBoard, String name, String role)
    {
        this.name = name;
        this.role = role;
        cards = new ArrayList<>();
        this.parentBoard = parentBoard;
    }

    public Column(Board parentBoard, String name)
    {
        this(parentBoard, name, "");
    }

    public Column(Board parentBoard)
    {
        this(parentBoard, "New Column", "");
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Board getBoard()
    {
        return parentBoard;
    }

}

