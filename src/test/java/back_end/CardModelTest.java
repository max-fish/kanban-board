package back_end;

import data.model.CardModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * This class makes sure {@link CardModel} correctly stores and retrieves data
 */
public class CardModelTest {

    private CardModel cardModel;

    @Before
    public void init(){
        cardModel = new CardModel();
    }

    /**
     * Check whether the constructor of {@link CardModel} correctly stores the name
     */
    @Test
    public void TestDefaultName(){
        assertEquals("", cardModel.getTitle());
    }

    /**
     * Check whether setName correctly stores the name
     */
    @Test
    public void TestName(){
        cardModel.setTitle("test");
        assertEquals("test", cardModel.getTitle());
    }

    /**
     * Check whether the constructor of {@link CardModel} correctly stores the description
     */
    @Test
    public void TestDefaultDescription(){
        assertEquals("", cardModel.getDescription());
    }

    /**
     * Check whether setDescription correctly stores the description
     */
    @Test
    public void TestDescription(){
        cardModel.setDescription("This is a test");
        assertEquals("This is a test", cardModel.getDescription());
    }

    /**
     * Check whether the constructor of {@link CardModel} initializes the story point
     */
    @Test
    public void TestDefaultStoryPoint(){
        assertEquals(1, cardModel.getStoryPoint());
    }

    /**
     * Check whether setStoryPoint correctly stores the story point
     */
    @Test
    public void TestStoryPoint(){
        cardModel.setStoryPoint(2);
        assertEquals(2, cardModel.getStoryPoint());
    }

    /**
     * Make sure the {@link CardModel} starts out with no completed date
     */
    @Test
    public void TestDefaultCompletedDate(){
        assertNull(cardModel.getCompletedDate());
    }

    /**
     * check whether setCompletedDate correctly stores the completed date
     */
    @Test
    public void TestCompletedDate(){
        cardModel.setCompletedDate(LocalDate.now());
        assertEquals(LocalDate.now(), cardModel.getCompletedDate());
    }
}
