package data.model;

import utils.Constants;

import static utils.Constants.ColumnRole.*;

import ui.KanbanColumn;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ColumnModel {
    private String name;
    private Constants.ColumnRole role;
    private int currentWip;
    private int wipLimit;
    private List<CardModel> cardModels;
    private BoardModel parentBoard;
    private KanbanColumn columnGUI;

    public ColumnModel(BoardModel parentBoard, String name, Constants.ColumnRole role) {
        this.name = name;
        this.role = role;
        cardModels = new ArrayList<>();
        this.parentBoard = parentBoard;
    }

    public ColumnModel(BoardModel parentBoard, String name) {
        this(parentBoard, name, Constants.ColumnRole.BACKLOG);
    }

    public ColumnModel(BoardModel parentBoard) {
        this(parentBoard, "", Constants.ColumnRole.BACKLOG);
    }

    public BoardModel getParent()
    {
        return parentBoard;
    }

    public void addCard(CardModel cardModel) {
        cardModels.add(cardModel);
        if (role == COMPLETED_WORK) cardModel.setCompletedDate(LocalDate.now());
    }

    public void insertCard(CardModel cardModel, int index) {
        cardModels.add(index, cardModel);
        if (role == COMPLETED_WORK) cardModel.setCompletedDate(LocalDate.now());
          System.out.println("Card inserted in " + name + " at " + index);
    }

    public void deleteCard(CardModel cardModel) {
        cardModels.remove(cardModel);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Constants.ColumnRole role) {
        this.role = role;
    }

    public boolean contains(CardModel card) {
        return cardModels.contains(card);
    }

    public boolean hasCards() {
        return !cardModels.isEmpty();
    }

    public List<CardModel> getCards() {
        return cardModels;
    }

    public String getName() {
        return name;
    }

    public Constants.ColumnRole getRole() {
        return role;
    }

    public int getWipLimit() {
        return wipLimit;
    }

    public void setWipLimit(int wipLimit) {
        this.wipLimit = wipLimit;
    }

    public int getCurrentWip() {
        return currentWip;
    }

    public void setCurrentWip(int currentWip) {
        this.currentWip = currentWip;
    }

    public void setGUI(KanbanColumn columnGUI)
    {
        this.columnGUI = columnGUI;
    }

    public KanbanColumn getGUI()
    {
        return columnGUI;
    }
}
