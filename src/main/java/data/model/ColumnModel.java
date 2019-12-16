package data.model;

import utils.Constants;

import static utils.Constants.ColumnRole.*;

import ui.KanbanColumn;
import data.log.ColumnCreateChange;
import data.log.ColumnNameChange;
import data.log.CardDeleteChange;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Data abstraction class for {@link ui.KanbanColumn}s
 */
public class ColumnModel {
    private static HashMap<Integer, ColumnModel> columns = new HashMap<>();
    // the id of the next column, ids increase chronologically
    private static int nextId = 1;

    private String name;
    private int id;
    private Constants.ColumnRole role;
    private int currentWip;
    private int wipLimit;
    private List<CardModel> cardModels;
    private transient BoardModel parentBoard;
    private transient KanbanColumn columnGUI;

    public ColumnModel(BoardModel parentBoard, String name, Constants.ColumnRole role) {
        this.name = name;
        this.role = role;
        cardModels = new ArrayList<>();
        this.parentBoard = parentBoard;

        while(columns.containsKey(nextId))
        {
            nextId++;
        }
        id = nextId;
        nextId++;

        columns.put(id, this);

        parentBoard.getActivityLogModel().addChange(new ColumnCreateChange(this));
    }

    public ColumnModel(BoardModel parentBoard, String name) {
        this(parentBoard, name, Constants.ColumnRole.BACKLOG);
    }

    public ColumnModel(BoardModel parentBoard) {
        this(parentBoard, "New Column", Constants.ColumnRole.BACKLOG);
    }

    public void init(KanbanColumn columnGUI, BoardModel parentBoard)
    {
        this.columnGUI = columnGUI;
        this.parentBoard = parentBoard;

        if(!columns.containsValue(this))
            columns.put(id, this);
    }

    public BoardModel getParent()
    {
        return parentBoard;
    }

    public void addCard(CardModel cardModel) {
        cardModels.add(cardModel);
        if (role == COMPLETED_WORK) cardModel.setCompletedDate(LocalDate.now());
        else if( role == WORK_IN_PROGRESS && cardModel.getEnterWIPDate() == null) cardModel.setEnterWIPDate(LocalDate.now());
    }

    public void insertCard(CardModel cardModel, int index) {
        cardModels.add(index, cardModel);
        if (role == COMPLETED_WORK) cardModel.setCompletedDate(LocalDate.now());
          System.out.println("Card inserted in " + name + " at " + index);
    }

    public void deleteCard(CardModel cardModel) {
        parentBoard.getActivityLogModel().addChange(new CardDeleteChange(cardModel, cardModels.indexOf(cardModel)));
        cardModels.remove(cardModel);
        parentBoard.getDeletedCards().add(cardModel);
          System.out.println("Card deleted");
    }

    public void removeCard(CardModel cardModel) {
        cardModels.remove(cardModel);
    }

    public void setName(String newName) {
        parentBoard.getActivityLogModel().addChange(new ColumnNameChange(this, name, newName));
        this.name = newName;
    }

    public void setNameWithoutTracking(String name)
    {
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

    public int getId()
    {
        return id;
    }

    public static ColumnModel getColumnModelById(int columnId)
    {
        return columns.get(columnId);
    }

    public static void addToColumns(ColumnModel columnToAdd)
    {
        if(!columns.containsValue(columnToAdd))
            columns.put(columnToAdd.getId(), columnToAdd);

          System.out.println("Deleted column added");
    }
}
