import data.model.CardModel;
import data.model.ColumnModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import static org.junit.Assert.*;

/**
 * This class makes sure {@link ColumnModel} correctly stores and retrieves data
 */
public class ColumnModelTest {
    private ColumnModel columnModel;

    @Before
    public void init(){
        columnModel = new ColumnModel();
    }

    /**
     * Check whether the constructor of {@link ColumnModel} correctly stores the name
     */
    @Test
    public void TestDefaultName(){
        assertEquals("", columnModel.getName());
    }

    /**
     * Check whether setName correctly stores the name
     */
    @Test
    public void TestName() {
        columnModel.setName("test");
        assertEquals("test", columnModel.getName());
    }

    /**
     * Check whether the {@link ColumnModel} starts out with the Backlog role
     */
    @Test
    public void TestDefaultRole(){
        assertEquals(Constants.ColumnRole.BACKLOG, columnModel.getRole());
    }

    /**
     * Check whether setRole correctly updates the role
     */
    @Test
    public void TestRole(){
        columnModel.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        assertEquals(Constants.ColumnRole.WORK_IN_PROGRESS, columnModel.getRole());
    }

    /**
     * Check whether the {@link ColumnModel} starts out with no WIP limit
     */
    @Test
    public void TestDefaultWipLimit(){
        assertEquals(0, columnModel.getWipLimit());
    }

    /**
     * Check whether setWipLimit correctly updates the WIP limit
     */
    @Test
    public void TestWipLimit(){
        columnModel.setWipLimit(1);
        assertEquals(1, columnModel.getWipLimit());
    }

    /**
     * Check whether the {@link ColumnModel} starts out with no current WIP
     */
    @Test
    public void TestDefaultCurrentWip(){
        assertEquals(0, columnModel.getCurrentWip());
    }

    /**
     * Check whether setCurrentWip correctly updates the current WIP
     */
    @Test
    public void TestCurrentWip(){
        columnModel.setCurrentWip(1);
        assertEquals(1, columnModel.getCurrentWip());
    }

    /**
     * Check whether the {@link ColumnModel} starts out with no cards
     */
    @Test
    public void TestDefaultCardAmount(){
        assertEquals(0, columnModel.getCards().size());
    }

    /**
     * Check whether the {@link ColumnModel}s card list updates properly when adding a new card
     */
    @Test
    public void TestCardAmount(){
        columnModel.addCard(new CardModel());
        assertEquals(1, columnModel.getCards().size());
    }

    /**
     * Makes sure hasCards returns false when there are no cards
     */
    @Test
    public void TestDefaultHasCards(){
        assertFalse(columnModel.hasCards());
    }

    /**
     * Makes sure hasCards return true when there are cards
     */
    @Test
    public void TestHasCards(){
        columnModel.addCard(new CardModel());
        assertTrue(columnModel.hasCards());
    }

    /**
     * Makes sure contains return false for a {@link CardModel} that is not in the {@link ColumnModel}
     */
    @Test
    public void TestDefaultContains(){
        CardModel cardModel = new CardModel();
        assertFalse(columnModel.contains(cardModel));
    }

    /**
     * Makes sure contains returns true for a {@link CardModel} that is in the {@link ColumnModel}
     */
    @Test
    public void TestContains(){
        CardModel cardModel = new CardModel();
        CardModel cardModel1 = new CardModel();
        columnModel.addCard(cardModel);

        assertTrue(columnModel.contains(cardModel));
        assertFalse(columnModel.contains(cardModel1));
    }

    /**
     * Check whether deleteCard removes the correct {@link CardModel} from the {@link ColumnModel}
     */
    @Test
    public void TestRemoveCards(){
        CardModel cardModel = new CardModel("test");
        CardModel cardModel1 = new CardModel("test1");
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);
        columnModel.deleteCard(cardModel);

        assertEquals(1, columnModel.getCards().size());
        assertEquals(cardModel1, columnModel.getCards().get(0));
    }

    /**
     * Check whether an array of {@link CardModel}s is properly updated with a series of additions/deletions
     */
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