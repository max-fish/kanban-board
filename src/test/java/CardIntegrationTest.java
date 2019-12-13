import data.model.BoardModel;
import data.model.ColumnModel;
import data.model.CardModel;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import ui.KanbanBoard;
import ui.KanbanColumn;
import ui.KanbanCard;

import static org.junit.Assert.*;

public class CardIntegrationTest extends ApplicationTest
{
    private KanbanCard kanbanCard;
    private KanbanColumn kanbanColumn;

    @Before
    public void init(){
        KanbanBoard board = new KanbanBoard();
        board.getController().fillWithData(new BoardModel("test"));

        kanbanColumn = new KanbanColumn(board);
        kanbanColumn.getController().fillWithData(new ColumnModel("column"));

        CardModel cardModel = new CardModel("card");
        kanbanCard = new KanbanCard(kanbanColumn);
        kanbanCard.getController().fillWithData(cardModel);

        kanbanColumn.getController().makeNewCard(cardModel);
    }

    @Test 
    public void testDeleteCard(){
        kanbanCard.getController().deleteCardDirectly(kanbanCard);
        assertEquals(0, kanbanColumn.getController().getColumnModel().getCards().size());
    }
}