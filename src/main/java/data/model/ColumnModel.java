package data.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ColumnModel {
    private String name;
    private String role;
    private List<CardModel> cardModels;

    private transient BoardModel parentBoard;

    public ColumnModel(BoardModel parentBoard, String name, String role)
    {
        this.name = name;
        this.role = role;
        cardModels = new ArrayList<>();
        this.parentBoard = parentBoard;
    }

    public ColumnModel(BoardModel parentBoard, String name) { this(parentBoard, name, ""); }

    public ColumnModel(BoardModel parentBoard) { this(parentBoard, "New Column", "Add column role"); }

    public void addCard(CardModel cardModel) {
        cardModels.add(cardModel);
        if(role.equals("Completed Work")) cardModel.setCompletedDate(LocalDate.now());
    }

    public void deleteCard(CardModel cardModel)
    {
        cardModels.remove(cardModel);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public boolean contains(CardModel card)
    {
        return cardModels.contains(card);
    }

    public boolean hasCards() { return !cardModels.isEmpty(); }

    public List<CardModel> getCards()
    {
        return cardModels;
    }

    public String getName()
    {
        return name;
    }

    public String getRole() { return role; }

}
