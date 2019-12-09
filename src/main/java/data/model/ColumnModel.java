package data.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ColumnModel {
    private String name;
    private String role;
    private int currentWip;
    private int wipLimit;
    private List<CardModel> cardModels;

    public ColumnModel(String name, String role) {
        this.name = name;
        this.role = role;
        cardModels = new ArrayList<>();
    }

    public ColumnModel(String name) {
        this(name, "");
    }

    public ColumnModel() {
        this("", "");
    }

    public void addCard(CardModel cardModel) {
        cardModels.add(cardModel);
        if (role.equals("Completed Work")) cardModel.setCompletedDate(LocalDate.now());
    }

    public void deleteCard(CardModel cardModel) {
        cardModels.remove(cardModel);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
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

    public String getRole() {
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

    public List<CardModel> getCardModels() {
        return this.cardModels;
    }
}
