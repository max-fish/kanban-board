package data.model;

import utils.Constants;

import static utils.Constants.ColumnRole.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ColumnModel {
    private String name;
    private Constants.ColumnRole role;
    private int currentWip;
    private int wipLimit;
    private List<CardModel> cardModels;

    public ColumnModel(String name, Constants.ColumnRole role) {
        this.name = name;
        this.role = role;
        cardModels = new ArrayList<>();
    }

    public ColumnModel(String name) {
        this(name, Constants.ColumnRole.BACKLOG);
    }

    public ColumnModel() {
        this("", Constants.ColumnRole.BACKLOG);
    }

    public void addCard(CardModel cardModel) {
        cardModels.add(cardModel);
        if (role == COMPLETED_WORK) cardModel.setCompletedDate(LocalDate.now());
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
}
