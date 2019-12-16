import data.model.CardModel;
import data.model.ColumnModel;
import data.model.BoardModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import static org.junit.Assert.*;

public class ColumnModelTest {
    private ColumnModel columnModel;

    @Before
    public void init(){
        columnModel = new ColumnModel(new BoardModel("board"));
    }

    @Test
    public void TestDefaultName(){
        assertEquals("New Column", columnModel.getName());
    }

    @Test
    public void TestName() {
        columnModel.setName("test");
        assertEquals("test", columnModel.getName());
    }

    @Test
    public void TestDefaultRole(){
        assertEquals(Constants.ColumnRole.BACKLOG, columnModel.getRole());
    }

    @Test
    public void TestRole(){
        columnModel.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        assertEquals(Constants.ColumnRole.WORK_IN_PROGRESS, columnModel.getRole());
    }

    @Test
    public void TestDefaultWipLimit(){
        assertEquals(0, columnModel.getWipLimit());
    }

    @Test
    public void TestWipLimit(){
        columnModel.setWipLimit(1);
        assertEquals(1, columnModel.getWipLimit());
    }

    @Test
    public void TestDefaultCurrentWip(){
        assertEquals(0, columnModel.getCurrentWip());
    }

    @Test
    public void TestCurrentWip(){
        columnModel.setCurrentWip(1);
        assertEquals(1, columnModel.getCurrentWip());
    }

    @Test
    public void TestDefaultCardAmount(){
        assertEquals(0, columnModel.getCards().size());
    }

    @Test
    public void TestCardAmount(){
        columnModel.addCard(new CardModel(columnModel));
        assertEquals(1, columnModel.getCards().size());
    }

    @Test
    public void TestDefaultHasCards(){
        assertFalse(columnModel.hasCards());
    }

    @Test
    public void TestHasCards(){
        columnModel.addCard(new CardModel(columnModel));
        assertTrue(columnModel.hasCards());
    }

    @Test
    public void TestDefaultContains(){
        CardModel cardModel = new CardModel(columnModel);
        assertFalse(columnModel.contains(cardModel));
    }

    @Test
    public void TestContains(){
        CardModel cardModel = new CardModel(columnModel);
        CardModel cardModel1 = new CardModel(columnModel);
        CardModel cardModel2 = new CardModel(new ColumnModel(new BoardModel("board")));
        CardModel cardModel3 = new CardModel(new ColumnModel(new BoardModel("board")));
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel2);

        assertTrue(columnModel.contains(cardModel));
        assertFalse(columnModel.contains(cardModel1));
        assertTrue(columnModel.contains(cardModel2));
        assertFalse(columnModel.contains(cardModel3));
    }

    @Test
    public void TestRemoveCards(){
        CardModel cardModel = new CardModel(columnModel, "test");
        CardModel cardModel1 = new CardModel(columnModel, "test1");
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);
        columnModel.deleteCard(cardModel);

        assertEquals(1, columnModel.getCards().size());
        assertEquals("test1", columnModel.getCards().get(0).getTitle());
    }

    @Test
    public void TestCardList(){
        CardModel cardModel = new CardModel(columnModel, "test");
        CardModel cardModel1 = new CardModel(columnModel, "test1");
        CardModel cardModel2 = new CardModel(columnModel, "test2");
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);
        columnModel.addCard(cardModel2);
        columnModel.deleteCard(cardModel1);
        assertArrayEquals(new CardModel[]{cardModel, cardModel2}, columnModel.getCards().toArray());
    }
}
