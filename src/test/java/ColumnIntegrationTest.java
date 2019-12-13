import data.model.BoardModel;
import data.model.ColumnModel;
import data.model.CardModel;

import utils.Constants.ColumnRole;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import ui.KanbanBoard;
import ui.KanbanColumn;
import ui.KanbanCard;

import static org.junit.Assert.*;

public class ColumnIntegrationTest extends ApplicationTest
{
    private KanbanColumn kanbanColumn;
    private KanbanBoard board;

    @Before
    public void init(){
        board = new KanbanBoard();
        board.getController().fillWithData(new BoardModel("test"));
        kanbanColumn = new KanbanColumn(board);
        kanbanColumn.getController().fillWithData(new ColumnModel("column"));
        board.getController().makeNewColumn(kanbanColumn.getController().getColumnModel());
    }

    @Test
    public void testAddCard() {
        CardModel card = new CardModel();
        kanbanColumn.getController().makeNewCard(card);

        //Check if card is added
        assertEquals(1, kanbanColumn.getController().getColumnModel().getCards().size());
        assertEquals(card, kanbanColumn.getController().getColumnModel().getCards().get(0));
    }

    @Test
    public void testSetRole() {
        kanbanColumn.getController().setRole(ColumnRole.INFO_ONLY);
        assertEquals(ColumnRole.INFO_ONLY, kanbanColumn.getController().getColumnModel().getRole());
    }

    @Test
    public void testCardSwap() {
        CardModel card = new CardModel();
        kanbanColumn.getController().makeNewCard(card);

        CardModel card2 = new CardModel();
        kanbanColumn.getController().makeNewCard(card2);

        kanbanColumn.getController().swapCards(0, 1);

        assertEquals(card2, kanbanColumn.getController().getColumnModel().getCards().get(0));
        assertEquals(card, kanbanColumn.getController().getColumnModel().getCards().get(1));
    }

}