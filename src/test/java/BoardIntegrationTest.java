import data.model.BoardModel;
import data.model.ColumnModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import ui.KanbanBoard;
import ui.KanbanColumn;

import static org.junit.Assert.*;

public class BoardIntegrationTest extends ApplicationTest {

    private KanbanBoard kanbanBoard;

    @Before
    public void init(){
        kanbanBoard = new KanbanBoard();
        kanbanBoard.getController().fillWithData(new BoardModel("test"));
    }

    /**
     * Check whether fillWithData properly updates the {@link BoardModel}
     */
    @Test
    public void TestFillWithData(){
        BoardModel boardModel = new BoardModel("test1");
        kanbanBoard.getController().fillWithData(boardModel);
        assertEquals(boardModel, kanbanBoard.getController().getBoardModel());
    }

    /**
     * Check whether makeNewColumn with no parameters properly updates the {@link KanbanBoard}
     * and the {@link BoardModel}
     */
    @Test
    public void TestAddDefaultColumn(){
        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());

        //check initial state of KanbanBoard and BoardModel
        assertEquals(1, kanbanBoardContents.getChildren().size());
        assertEquals(0, kanbanBoard.getController().getBoardModel().getColumns().size());

        kanbanBoard.getController().makeNewColumn();

        assertEquals(2, kanbanBoardContents.getChildren().size());
        assertEquals(1, kanbanBoard.getController().getBoardModel().getColumns().size());
    }

    /**
     * Check whether makeNewColumn with a specific {@link ColumnModel} properly updates the {@link KanbanBoard} and {@link BoardModel}
     */
    @Test
    public void TestAddColumn(){
        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());

        ColumnModel columnModel =  new ColumnModel("test");

        kanbanBoard.getController().makeNewColumn(new ColumnModel("test"));

        //check if properly added
        assertEquals(2, kanbanBoardContents.getChildren().size());
        assertEquals(1, kanbanBoard.getController().getBoardModel().getColumns().size());

        //check if properly reflected in KanbanBoard and BoardModel
        assertEquals(columnModel, ((KanbanColumn) kanbanBoardContents.getChildren().get(0)).getController().getColumnModel());
        assertEquals(columnModel, kanbanBoard.getController().getBoardModel().getColumns().get(0));
    }

    /**
     * Check whether swapping columns is properly reflected in the {@link KanbanBoard} and {@link BoardModel}
     */
    @Test
    public void TestSwapColumns(){
        ColumnModel columnModel1 = new ColumnModel("test1");
        ColumnModel columnModel2 = new ColumnModel("test2");
        kanbanBoard.getController().makeNewColumn(columnModel1);
        kanbanBoard.getController().makeNewColumn(columnModel2);
        kanbanBoard.getController().swapColumns(0,1);

        //board model check
        assertEquals("test2", kanbanBoard.getController().getBoardModel().getColumns().get(0).getName());
        assertEquals("test1", kanbanBoard.getController().getBoardModel().getColumns().get(1).getName());

        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());

        //ui check
        assertEquals(columnModel2, ((KanbanColumn) kanbanBoardContents.getChildren().get(0)).getController().getColumnModel());
        assertEquals(columnModel1, ((KanbanColumn) kanbanBoardContents.getChildren().get(1)).getController().getColumnModel());
    }
}
