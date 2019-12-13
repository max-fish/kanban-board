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

    @Test
    public void TestFillWithData(){
        BoardModel boardModel = new BoardModel("test1");
        kanbanBoard.getController().fillWithData(boardModel);
        assertEquals(boardModel, kanbanBoard.getController().getBoardModel());
    }

    @Test
    public void TestAddDefaultColumn(){
        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());

        assertEquals(1, kanbanBoardContents.getChildren().size());
        assertEquals(0, kanbanBoard.getController().getBoardModel().getColumns().size());

        kanbanBoard.getController().makeNewColumn();

        assertEquals(2, kanbanBoardContents.getChildren().size());
        assertEquals(1, kanbanBoard.getController().getBoardModel().getColumns().size());
    }

    @Test
    public void TestAddColumn(){
        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());

        assertEquals(1, kanbanBoardContents.getChildren().size());
        assertEquals(0, kanbanBoard.getController().getBoardModel().getColumns().size());

        kanbanBoard.getController().makeNewColumn(new ColumnModel("test"));

        assertEquals(2, kanbanBoardContents.getChildren().size());
        assertEquals(1, kanbanBoard.getController().getBoardModel().getColumns().size());
        assertEquals("test", ((KanbanColumn) kanbanBoardContents.getChildren().get(0)).getController().getColumnModel().getName());
        assertEquals("test", kanbanBoard.getController().getBoardModel().getColumns().get(0).getName());
    }

    @Test
    public void TestSwapColumns(){
        kanbanBoard.getController().makeNewColumn(new ColumnModel("test1"));
        kanbanBoard.getController().makeNewColumn(new ColumnModel("test2"));
        kanbanBoard.getController().swapColumns(0,1);

        assertEquals("test2", kanbanBoard.getController().getBoardModel().getColumns().get(0).getName());
        assertEquals("test1", kanbanBoard.getController().getBoardModel().getColumns().get(1).getName());

        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());

        assertEquals("test2", ((KanbanColumn) kanbanBoardContents.getChildren().get(0)).getController().getColumnModel().getName());
        assertEquals("test1", ((KanbanColumn) kanbanBoardContents.getChildren().get(1)).getController().getColumnModel().getName());
    }
}
