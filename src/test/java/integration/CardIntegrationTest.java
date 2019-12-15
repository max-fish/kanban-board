package integration;

import data.model.BoardModel;
import data.model.ColumnModel;
import data.model.CardModel;
import javafx.scene.layout.VBox;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import ui.KanbanBoard;
import ui.KanbanColumn;
import ui.KanbanCard;

import static org.junit.Assert.*;

/**
 * This class tests the communication between the {@link KanbanCard} and the {@link CardModel}
 *  * through the controller methods
 */
public class CardIntegrationTest extends ApplicationTest
{
    private KanbanCard kanbanCard;
    private KanbanColumn kanbanColumn;
    private VBox kanbanColumnContents;


    @Before
    public void init(){
        KanbanBoard board = new KanbanBoard();
        board.getController().fillWithData(new BoardModel("test"));

        kanbanColumn = new KanbanColumn(board);
        kanbanColumn.getController().fillWithData(new ColumnModel("column"));

        CardModel cardModel = new CardModel("card");
        kanbanColumn.getController().makeNewCard(cardModel);
        kanbanColumnContents = (VBox) kanbanColumn.getCenter();
        kanbanCard = (KanbanCard) kanbanColumnContents.getChildren().get(0);
    }

    /**
     * Check whether deleting a card removes it from the {@link KanbanColumn} and {@link ColumnModel}
     */
    @Test 
    public void testDeleteCard(){
        kanbanCard.getController().deleteCardDirectly(kanbanCard);

        //check ui
        assertEquals(0, kanbanColumnContents.getChildren().size());
        //check data
        assertEquals(0, kanbanColumn.getController().getColumnModel().getCards().size());
    }
}