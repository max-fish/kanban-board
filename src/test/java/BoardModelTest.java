import data.model.BoardModel;
import data.model.ColumnModel;
import org.junit.Test;
import utils.Constants;

import java.time.LocalDate;
import static org.junit.Assert.*;

public class BoardModelTest {
    @Test
    public void TestName(){
        BoardModel boardModel = new BoardModel("test");
        assertEquals("test", boardModel.getName());
    }

    @Test
    public void TestSetName(){
        BoardModel boardModel = new BoardModel("test");
        boardModel.setName("test2");
        assertEquals("test2", boardModel.getName());
    }

    @Test
    public void TestDefaultName(){
        BoardModel boardModel = new BoardModel("test");
        LocalDate timeOfCreation = LocalDate.now();

        assertEquals(timeOfCreation.toString(), boardModel.getCreationDate().toString());
    }

    @Test
    public void TestDefaultHasColumns(){
        BoardModel boardModel = new BoardModel("test");
        assertFalse(boardModel.hasColumns());
    }

    @Test
    public void TestHasColumnsAfterAdding(){
        BoardModel boardModel = new BoardModel("test");
        boardModel.addColumn(new ColumnModel());

        assertTrue(boardModel.hasColumns());
    }

    @Test
    public void TestColumnListSize(){
        BoardModel boardModel = new BoardModel("test");

        assertEquals(boardModel.getColumns().size(), 0);

        boardModel.addColumn(new ColumnModel());

        assertEquals(1, boardModel.getColumns().size());
    }

    @Test
    public void TestWipColumnListSize() {
        BoardModel boardModel = new BoardModel("test");

        assertEquals(boardModel.getWIPColumns().size(), 0);

        ColumnModel columnModel = new ColumnModel();
        columnModel.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        boardModel.addColumn(columnModel);

        assertEquals(1, boardModel.getWIPColumns().size());
    }

    @Test
    public void TestCompletedColumnListSize(){
        BoardModel boardModel = new BoardModel("test");

        assertEquals(boardModel.getCompletedColumns().size(), 0);

        ColumnModel columnModel = new ColumnModel();
        columnModel.setRole(Constants.ColumnRole.COMPLETED_WORK);

        boardModel.addColumn(columnModel);

        assertEquals(1, boardModel.getCompletedColumns().size());
    }

    @Test
    public void TestHasCompletedColumns(){
        BoardModel boardModel = new BoardModel("test");
        assertFalse(boardModel.hasCompleteColumn());
        ColumnModel columnModel = new ColumnModel();
        columnModel.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(columnModel);
        assertTrue(boardModel.hasCompleteColumn());
    }

    @Test
    public void TestDeleteColumns(){
        BoardModel boardModel = new BoardModel("test");
        ColumnModel columnModel = new ColumnModel("test1");
        ColumnModel columnModel1 = new ColumnModel("test2");
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);
        boardModel.deleteColumn(columnModel);
        assertEquals(1, boardModel.getColumns().size());
        assertEquals("test2", boardModel.getColumns().get(0).getName());

    }




}
