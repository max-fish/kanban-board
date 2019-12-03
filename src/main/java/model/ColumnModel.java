package model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ColumnModel {
    private String name;
    private String role;
    private List<CardModel> cardModels;
    private BoardModel parentBoard;

    /**
     *
     */
    public ColumnModel(BoardModel parentBoard, String name, String role)
    {
        this.name = name;
        this.role = role;
        cardModels = new ArrayList<>();
        this.parentBoard = parentBoard;
    }

    public ColumnModel(BoardModel parentBoard, String name)
    {
        this(parentBoard, name, "");
    }

    public ColumnModel(BoardModel parentBoard)
    {
        this(parentBoard, "New Column", "");
    }

    public void addCard(CardModel cardModel) {
        cardModels.add(cardModel);
        parentBoard.addedCard(LocalDate.now());
    }

    public void deleteCard(CardModel cardModel){cardModels.remove(cardModel);}

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public BoardModel getBoard()
    {
        return parentBoard;
    }

}

