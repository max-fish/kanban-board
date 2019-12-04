import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;

import ui.HomePage;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;

public class TestBoard extends ApplicationTest {

    @Override 
    public void start(Stage primaryStage) throws IOException {
        new HomePage().start(primaryStage);
    }

    @Test
    public void testIfANewBoardIsCreatedAndCanBeViewed() {
        clickOn("#test");
        clickOn("board");
    }

    @Test
    public void TestHomePageButton() {
        clickOn("#test");
        clickOn("board");
    }
}