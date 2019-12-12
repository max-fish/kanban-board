import data.model.CardModel;
import data.model.ColumnModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import static org.junit.Assert.*;

public class ColumnModelTest {
    private ColumnModel columnModel;

    @Before
    public void init(){
        columnModel = new ColumnModel();
    }

    @Test
    public void TestDefaultName(){
        assertEquals("", columnModel.getName());
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
        columnModel.addCard(new CardModel());
        assertEquals(1, columnModel.getCards().size());
    }

    @Test
    public void TestDefaultHasCards(){
        assertFalse(columnModel.hasCards());
    }

    @Test
    public void TestHasCards(){
        columnModel.addCard(new CardModel());
        assertTrue(columnModel.hasCards());
    }

    @Test
    public void TestDefaultContains(){
        CardModel cardModel = new CardModel();
        assertFalse(columnModel.contains(cardModel));
    }

    @Test
    public void TestContains(){
        CardModel cardModel = new CardModel();
        CardModel cardModel1 = new CardModel();
        columnModel.addCard(cardModel);

        assertTrue(columnModel.contains(cardModel));
        assertFalse(columnModel.contains(cardModel1));
    }

    @Test
    public void TestRemoveCards(){
        CardModel cardModel = new CardModel("test");
        CardModel cardModel1 = new CardModel("test1");
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);
        columnModel.deleteCard(cardModel);

        assertEquals(1, columnModel.getCards().size());
        assertEquals("test1", columnModel.getCards().get(0).getTitle());
    }

    @Test
    public void TestCardList(){
        CardModel cardModel = new CardModel("test");
        CardModel cardModel1 = new CardModel("test1");
        CardModel cardModel2 = new CardModel("test2");
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);
        columnModel.addCard(cardModel2);
        columnModel.deleteCard(cardModel1);
        assertArrayEquals(new CardModel[]{cardModel, cardModel2}, columnModel.getCards().toArray());
    }
}