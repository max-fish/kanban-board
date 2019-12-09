
import org.junit.Test;



public class TestBoard extends BaseTest {

    @Test
    public void testIfANewBoardIsCreatedAndCanBeViewed() {
        clickOn("#addButton");
        clickOn("Board Name");
        write("Board");
        clickOn("Ok");
    }

    @Test
    public void TestHomePageButton() {
        openBoard();
        clickOn("#homepage");
    }
}