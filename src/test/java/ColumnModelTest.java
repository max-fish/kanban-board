import data.model.CardModel;
import data.model.ColumnModel;
import org.junit.Test;
import utils.Constants;
import static org.junit.Assert.*;

public class ColumnModelTest {
    @Test
    public void DoesColumnModelHaveCorrectName() {
        ColumnModel columnModel = new ColumnModel("test");
        assertEquals("test", columnModel.getName());
    }

    @Test
    public void DoesColumnModelHaveCorrectDefaultName(){
        ColumnModel columnModel = new ColumnModel();
        assertEquals("", columnModel.getName());
    }

    @Test
    public void DoesColumnModelHaveCorrectWipLimit(){
        ColumnModel columnModel = new ColumnModel("test");
        columnModel.setWipLimit(1);
        assertEquals(1, columnModel.getWipLimit());
    }

    @Test
    public void DoesColumnModelHaveCorrectDefaultWipLimit(){
        assertEquals(Constants.ColumnRole.BACKLOG, new ColumnModel().getRole());
    }

    @Test
    public void DoesColumnModelHaveCorrectCurrentWip(){
        ColumnModel columnModel = new ColumnModel();
        columnModel.setCurrentWip(1);
        assertEquals(1, columnModel.getCurrentWip());
    }

    @Test
    public void DoesColumnModelHaveCorrectDefaultWip(){
        assertEquals(0, new ColumnModel().getCurrentWip());
    }

    @Test
    public void DoesColumnModelHaveCorrectCardAmount(){
        ColumnModel columnModel = new ColumnModel();
        columnModel.addCard(new CardModel());
        assertEquals(1, columnModel.getCards().size());
    }

    @Test
    public void DoesColumnModelHaveCorrectDefaultCardAmount(){
        assertEquals(0, new ColumnModel().getCards().size());
    }

    @Test
    public void DoesHasCardsReturnProperBoolean(){
        ColumnModel columnModel = new ColumnModel();
        assertFalse(columnModel.hasCards());
        columnModel.addCard(new CardModel());
        assertTrue(columnModel.hasCards());
    }

    @Test
    public void DoesContainsReturnProperBoolean(){
        ColumnModel columnModel = new ColumnModel();
        CardModel cardModel = new CardModel();
        CardModel cardModel1 = new CardModel();

        assertFalse(columnModel.contains(cardModel));
        assertFalse(columnModel.contains(cardModel1));

        columnModel.addCard(cardModel);

        assertTrue(columnModel.contains(cardModel));
        assertFalse(columnModel.contains(cardModel1));
    }

    @Test
    public void TestRemovalOfCards(){
        ColumnModel columnModel = new ColumnModel();
        CardModel cardModel = new CardModel("test");
        CardModel cardModel1 = new CardModel("test1");
        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);
        columnModel.deleteCard(cardModel);
        assertEquals(1, columnModel.getCards().size());
        assertEquals("test1", columnModel.getCards().get(0).getTitle());
    }
}