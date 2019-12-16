import data.model.BoardModel;
import data.model.ColumnModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BoardModelTest {

    private BoardModel boardModel;

    @Before
    public void init() {
        boardModel = new BoardModel("test");
    }

    @Test
    public void TestDefaultName() {
        assertEquals("test", boardModel.getName());
    }

    @Test
    public void TestSetName() {
        boardModel.setName("test2");
        assertEquals("test2", boardModel.getName());
    }

    @Test
    public void TestCreationDate() {
        LocalDate timeOfCreation = LocalDate.now();

        assertEquals(timeOfCreation.toString(), boardModel.getCreationDate().toString());
    }

    @Test
    public void TestDefaultHasColumns() {
        assertFalse(boardModel.hasColumns());
    }

    @Test
    public void TestHasColumns() {
        boardModel.addColumn(new ColumnModel(boardModel));
        assertTrue(boardModel.hasColumns());
    }

    @Test
    public void TestDefaultColumnListSize() {
        assertEquals(boardModel.getColumns().size(), 0);
    }

    @Test
    public void TestColumnListSize() {
        boardModel.addColumn(new ColumnModel(boardModel));

        assertEquals(1, boardModel.getColumns().size());
    }

    @Test
    public void TestDefaultWipColumnListSize() {
        assertEquals(boardModel.getWIPColumns().size(), 0);
    }

    @Test
    public void TestWipColumnListSize() {
        ColumnModel columnModel = new ColumnModel(boardModel);
        columnModel.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        boardModel.addColumn(columnModel);

        assertEquals(1, boardModel.getWIPColumns().size());
    }

    @Test
    public void TestDefaultCompletedColumnListSize() {
        assertEquals(boardModel.getCompletedColumns().size(), 0);
    }

    @Test
    public void TestCompletedColumnListSize() {
        ColumnModel columnModel = new ColumnModel(boardModel);
        columnModel.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(columnModel);

        assertEquals(1, boardModel.getCompletedColumns().size());
    }

    @Test
    public void TestDefaultHasCompletedColumns(){
        assertFalse(boardModel.hasCompleteColumn());
    }

    @Test
    public void TestHasCompletedColumns() {
        ColumnModel columnModel = new ColumnModel(boardModel);
        columnModel.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(columnModel);
        assertTrue(boardModel.hasCompleteColumn());
    }

    @Test
    public void TestDeleteColumns() {
        ColumnModel columnModel = new ColumnModel(boardModel, "test1");
        ColumnModel columnModel1 = new ColumnModel(boardModel, "test2");
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);
        boardModel.deleteColumn(columnModel);
        assertEquals(1, boardModel.getColumns().size());
        assertEquals("test2", boardModel.getColumns().get(0).getName());
    }

    @Test
    public void TestColumnList(){
        ColumnModel columnModel = new ColumnModel(boardModel, "test1");
        ColumnModel columnModel1 = new ColumnModel(boardModel, "test2");
        ColumnModel columnModel2 = new ColumnModel(boardModel, "test3");
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);
        boardModel.addColumn(columnModel2);
        boardModel.deleteColumn(columnModel1);

        assertArrayEquals(new ColumnModel[]{columnModel, columnModel2}, boardModel.getColumns().toArray());
    }


}
