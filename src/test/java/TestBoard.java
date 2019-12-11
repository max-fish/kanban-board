import org.junit.Test;

public class TestBoard extends BaseTest
{
    @Test
    public void testIfANewBoardIsCreatedAndCanBeViewed() {
        sleep(500);
        clickOn("#addButton");

        //Fill in dialogue box to create new board
        clickOn("Board Name");
        write("Board");
        clickOn("Ok");
        sleep(500);
    }

    @Test
    public void TestHomePageButton() {
        sleep(500);
        openBoard();

        clickOn("#homepage");
        sleep(500);
    }
}