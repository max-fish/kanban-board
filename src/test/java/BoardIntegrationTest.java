import data.model.BoardModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import org.junit.Test;
import ui.KanbanBoard;

import static org.junit.Assert.*;

public class BoardIntegrationTest extends BaseTest {
    @Test
    public void TestAddColumn(){
        KanbanBoard kanbanBoard = new KanbanBoard();
        kanbanBoard.getController().fillWithData(new BoardModel("test"));
        HBox kanbanBoardContents = ((HBox) ((ScrollPane) kanbanBoard.getCenter()).getContent());
        assertEquals(1, kanbanBoardContents.getChildren().size());
        kanbanBoard.getController().makeNewColumn();
        assertEquals(2, kanbanBoardContents.getChildren().size());
        assertEquals(1, kanbanBoard.getController().getBoardModel().getColumns().size());
    }
}
