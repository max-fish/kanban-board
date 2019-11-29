import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.BoardModel;
import model.ColumnModel;

public class TestColumns 
{
    private BoardModel board;
    private ColumnModel col1;

    @Before
    public void setUp() {
        board = new BoardModel("board");

        col1 = new ColumnModel(board, "col1", "");

        ColumnModel col2 = new ColumnModel(board, "col2", "");
        ColumnModel col3 = new ColumnModel(board, "col3", "");

        board.addColumn(col1);
        board.addColumn(col2);
        board.addColumn(col3);
    }

    @Test
    public void TestAddColumnShouldIncreaseArraySize() {
        int size = board.getColumnModels().size();
        ColumnModel col4 = new ColumnModel(board, "col4", "");
        board.addColumn(col4);
        assertEquals(board.getColumnModels().size(), size + 1);
        assert board.getColumnModels().contains(col4);
    }

    @Test
    public void TestdeleteColumnShouldDecreaseArraySize() {
        int size = board.getColumnModels().size();
        board.deleteColumn(col1);
        assertEquals(board.getColumnModels().size(), size - 1);
        assert !board.getColumnModels().contains(col1);
    }
}
