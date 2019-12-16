package integration;

import data.model.BoardModel;
import data.model.ColumnModel;
import data.model.CardModel;

import javafx.scene.layout.VBox;
import ui.KanbanCard;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import ui.KanbanBoard;
import ui.KanbanColumn;

import static org.junit.Assert.*;

/**
 * This class tests the communication between the {@link KanbanColumn} and the {@link ColumnModel}
 * through the controller methods
 */
public class ColumnIntegrationTest extends ApplicationTest
{
    private KanbanColumn kanbanColumn;
    private VBox kanbanColumnContents;

    @Before
    public void init(){
        KanbanBoard kanbanBoard = new KanbanBoard();
        kanbanColumn = new KanbanColumn(kanbanBoard);
        BoardModel boardModel = new BoardModel("test");
        ColumnModel columnModel = new ColumnModel(boardModel);
        columnModel.setName("column");
        kanbanColumn.getController().fillWithData(columnModel);
        kanbanColumnContents = (VBox) kanbanColumn.getCenter();
    }

    /**
     * Check whether adding a card updates the {@link KanbanColumn} and the {@link ColumnModel}
     */
    @Test
    public void testAddCard() {
        BoardModel boardModel = new BoardModel("test");
        ColumnModel columnModel = new ColumnModel(boardModel);
        CardModel card = new CardModel(columnModel);
        kanbanColumn.getController().makeNewCard(card);


        //check if card is added to ui and model
        assertEquals(1, kanbanColumnContents.getChildren().size());
        assertEquals(1, kanbanColumn.getController().getColumnModel().getCards().size());

        //Check if correct data is added
        assertEquals(1, kanbanColumn.getController().getColumnModel().getCards().size());
        assertEquals(card, kanbanColumn.getController().getColumnModel().getCards().get(0));
    }


    /**
     * Check whether swapping cards updates the {@link KanbanColumn} and {@link ColumnModel}
     */
    @Test
    public void testCardSwap() {
        BoardModel boardModel = new BoardModel("test2");
        ColumnModel columnModel = new ColumnModel(boardModel);

        CardModel card = new CardModel(columnModel);
        kanbanColumn.getController().makeNewCard(card);

        CardModel card2 = new CardModel(columnModel);
        kanbanColumn.getController().makeNewCard(card2);

        kanbanColumn.getController().swapCards(0, 1);

        //check if ui updates
        assertEquals(card2, ((KanbanCard) kanbanColumnContents.getChildren().get(0)).getController().getCardModel());
        assertEquals(card, ((KanbanCard) kanbanColumnContents.getChildren().get(1)).getController().getCardModel());

        //check if data updates
        assertEquals(card2, kanbanColumn.getController().getColumnModel().getCards().get(0));
        assertEquals(card, kanbanColumn.getController().getColumnModel().getCards().get(1));
    }
}