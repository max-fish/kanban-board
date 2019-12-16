package data.model;

import java.util.HashMap;
import java.time.LocalDate;
import ui.KanbanCard;
import data.log.CardCreateChange;

/**
 * Data abstraction class for {@link ui.KanbanCard}s
 */
public class CardModel {
    private static HashMap<Integer, CardModel> cards = new HashMap<>();
    // the id of the next card, ids increase chronologically
    private static int nextId = 1;

    private int id;
    private String title;
    private String description;
    private Integer storyPoints;
    private LocalDate creationDate;
    private LocalDate enterWIPDate = null;
    private LocalDate completedDate = null;
    private transient ColumnModel parentColumn;
    private transient KanbanCard cardGUI;

    public CardModel(ColumnModel parentColumn, String title, String description, Integer storyPoints)
    {
        this.title = title;
        this.description = description;
        this.storyPoints = storyPoints;
        creationDate = LocalDate.now();
        this.parentColumn = parentColumn;

        while(cards.containsKey(nextId))
        {
            nextId++;
        }
        id = nextId;
        nextId++;

        cards.put(id, this);

        parentColumn.getParent().getActivityLogModel().addChange(new CardCreateChange(this));

          System.out.println("The card was created: " + title + id);
    }

    public CardModel(ColumnModel parentColumn, String title)
    {
        this(parentColumn, title, "", 1);
    }

    public CardModel(ColumnModel parentColumn)
    {
        this(parentColumn, "New Card");
    }

    public void init(KanbanCard cardGUI, ColumnModel parentColumn)
    {
        this.cardGUI = cardGUI;
        this.parentColumn = parentColumn;

        if(!cards.containsValue(this))
            cards.put(id, this);
    }

    public ColumnModel getParent()
    {
        return parentColumn;
    }

    public int getID()
    {
        return id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStoryPoint(int story){
        this.storyPoints = story;
    }

    public String getDescription()
    {
        return description;
    }

    public int getStoryPoint(){
        return storyPoints;
    }

    public LocalDate getCreationDate() { return creationDate; }

    public void setCompletedDate(LocalDate date) { completedDate = date; }

    public LocalDate getCompletedDate() { return completedDate; }

    public void setCreationDate(LocalDate date){ creationDate = date; }

    public void setEnterWIPDate(LocalDate date){ enterWIPDate = date; }

    public LocalDate getEnterWIPDate(){ return enterWIPDate; }

    public void setGUI(KanbanCard cardGUI)
    {
        this.cardGUI  = cardGUI;
    }

    public KanbanCard getGUI()
    {
        return cardGUI;
    }

    public static CardModel getCardModelById(int cardId)
    {
        return cards.get(cardId);
    }

    public static void addToCards(CardModel cardToAdd)
    {
        if(!cards.containsValue(cardToAdd))
            cards.put(cardToAdd.getID(), cardToAdd);

          System.out.println("Deleted card added");
    }
}
