import data.model.CardModel;
import data.model.ColumnModel;
import data.model.BoardModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CardModelTest {

    private CardModel cardModel;

    @Before
    public void init(){
        cardModel = new CardModel(new ColumnModel(new BoardModel("board")));
    }

    @Test
    public void TestDefaultName(){
        assertEquals("New Card", cardModel.getTitle());
    }

    @Test
    public void TestName(){
        cardModel.setTitle("test");
        assertEquals("test", cardModel.getTitle());
    }

    @Test
    public void TestDefaultDescription(){
        assertEquals("", cardModel.getDescription());
    }

    @Test
    public void TestDescription(){
        cardModel.setDescription("This is a test");
        assertEquals("This is a test", cardModel.getDescription());
    }

    @Test
    public void TestDefaultStoryPoint(){
        assertEquals(1, cardModel.getStoryPoint());
    }

    @Test
    public void TestStoryPoint(){
        cardModel.setStoryPoint(2);
        assertEquals(2, cardModel.getStoryPoint());
    }

    @Test
    public void TestDefaultCompletedDate(){
        assertNull(cardModel.getCompletedDate());
    }

    @Test
    public void TestCompletedDate(){
        cardModel.setCompletedDate(LocalDate.now());
        assertEquals(LocalDate.now(), cardModel.getCompletedDate());
    }


}
