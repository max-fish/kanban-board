import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;

import ui.HomePage;
import javafx.stage.Stage;

import org.junit.Test;

import java.io.IOException;



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