import data.model.BoardModel;
import data.model.ColumnModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * This class makes sure the {@link BoardModel} correctly stores and retrieves data
 */
public class BoardModelTest {

    private BoardModel boardModel;

    @Before
    public void init() {
        boardModel = new BoardModel("test");
    }

    /**
     * Check whether the constructor of {@link BoardModel} correctly stores the name
     */
    @Test
    public void TestDefaultName() {
        assertEquals("test", boardModel.getName());
    }

    /**
     * Check whether setName correctly stores the name
     */
    @Test
    public void TestSetName() {
        boardModel.setName("test2");
        assertEquals("test2", boardModel.getName());
    }

    /**
     * Check whether the {@link BoardModel}'s creationDate field is initialized to the proper time
     */
    @Test
    public void TestCreationDate() {
        LocalDate timeOfCreation = LocalDate.now();

        assertEquals(timeOfCreation.toString(), boardModel.getCreationDate().toString());
    }

    /**
     * Check whether hasColumns returns false when there are no columns
     */
    @Test
    public void TestDefaultHasColumns() {
        assertFalse(boardModel.hasColumns());
    }

    /**
     * Check whether hasColumns returns true when there are columns
     */
    @Test
    public void TestHasColumns() {
        boardModel.addColumn(new ColumnModel());
        assertTrue(boardModel.hasColumns());
    }

    /**
     * Check whether the {@link BoardModel} starts out with no columns
     */
    @Test
    public void TestDefaultColumnListSize() {
        assertEquals(boardModel.getColumns().size(), 0);
    }

    /**
     * Check whether list of columns increments by 1 when a {@link ColumnModel} is added
     */
    @Test
    public void TestColumnListSize() {
        boardModel.addColumn(new ColumnModel());

        assertEquals(1, boardModel.getColumns().size());
    }

    /**
     * Check whether the {@link BoardModel} starts out with no WIP Columns
     */
    @Test
    public void TestDefaultWipColumnListSize() {
        assertEquals(boardModel.getWIPColumns().size(), 0);
    }

    /**
     * Check whether wipColumns only collects the {@link ColumnModel}s with the WIP role
     */
    @Test
    public void TestWipColumnListSize() {
        ColumnModel columnModel = new ColumnModel();
        ColumnModel columnModel1 = new ColumnModel();
        columnModel.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);

        assertEquals(1, boardModel.getWIPColumns().size());
    }

    /**
     * Check whether the {@link BoardModel} starts out with no completed columns
     */
    @Test
    public void TestDefaultCompletedColumnListSize() {
        assertEquals(0, boardModel.getCompletedColumns().size());
    }

    /**
     * Check whether the completedColumns only collects the {@link ColumnModel}'s with the Completed role
     */
    @Test
    public void TestCompletedColumnListSize() {
        ColumnModel columnModel = new ColumnModel();
        ColumnModel columnModel1 = new ColumnModel();
        columnModel.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);
        assertEquals(1, boardModel.getCompletedColumns().size());
    }

    /**
     * Check whether hasCompletedColumns returns false when there are no completed columns
     */
    @Test
    public void TestDefaultHasCompletedColumns(){
        assertFalse(boardModel.hasCompleteColumn());
    }

    /**
     * Check whether hasCompletedColumns returns true when there are completed columns
     */
    @Test
    public void TestHasCompletedColumns() {
        ColumnModel columnModel = new ColumnModel();
        columnModel.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(columnModel);
        assertTrue(boardModel.hasCompleteColumn());
    }

    /**
     * Check whether deleteColumn deletes the correct {@link ColumnModel} from the list of {@link ColumnModel}s
     */
    @Test
    public void TestDeleteColumns() {
        ColumnModel columnModel = new ColumnModel("test1");
        ColumnModel columnModel1 = new ColumnModel("test2");
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);
        boardModel.deleteColumn(columnModel);
        assertEquals(1, boardModel.getColumns().size());
        assertEquals(columnModel1, boardModel.getColumns().get(0));
    }

    /**
     * Check whether an array of {@link ColumnModel}s is properly updated with a series of additions/deletions
     */
    @Test
    public void TestColumnList(){
        ColumnModel columnModel = new ColumnModel("test1");
        ColumnModel columnModel1 = new ColumnModel("test2");
        ColumnModel columnModel2 = new ColumnModel("test3");
        boardModel.addColumn(columnModel);
        boardModel.addColumn(columnModel1);
        boardModel.addColumn(columnModel2);
        boardModel.deleteColumn(columnModel1);

        assertArrayEquals(new ColumnModel[]{columnModel, columnModel2}, boardModel.getColumns().toArray());
    }
}
