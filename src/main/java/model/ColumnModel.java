package model;

import java.util.List;
import java.util.ArrayList;

public class Column{
    private String name;
    private String role;
    private List<Card> cards;

    /**
     *
     */
    public Column(String name, String role)
    {
        this.name = name;
        this.role = role;
        cards = new ArrayList<>();
    }

    public Column(String name)
    {
        this(name, "");
    }

    public Column()
    {
        this("New Column", "");
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

    public String getName()
    {
        return name;
    }

    public String getRole()
    {
        return role;
    }
}
