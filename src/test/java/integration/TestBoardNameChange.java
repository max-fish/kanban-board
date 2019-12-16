package integration;

import data.log.BoardNameChange;
import data.model.BoardModel;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import ui.KanbanBoard;
import static org.junit.Assert.*;

/**
 * This class test whether applying changes from version management
 * correctly updates the {@link KanbanBoard} and {@link BoardModel}
 */
public class TestBoardNameChange extends ApplicationTest {
    private KanbanBoard kanbanBoard;
    private BoardNameChange boardNameChange;
    private BoardModel boardModel;


    @Before
    public void init(){
        kanbanBoard = new KanbanBoard();
        boardModel = new BoardModel("test");
        boardModel.init(kanbanBoard);
        boardNameChange = new BoardNameChange(boardModel, "test", "next");
    }

    @Test
    public void TestRevert(){
        boardNameChange.revert();

        //data
        assertEquals("test", boardModel.getName());
        //ui
        assertEquals("test", kanbanBoard.getController().getTitle().getText());
    }

    @Test
    public void TestApply(){
        boardNameChange.revert();
        boardNameChange.apply();

        //data
        assertEquals("next", boardModel.getName());
        //ui
        assertEquals("next", kanbanBoard.getController().getTitle().getText());
    }
}
