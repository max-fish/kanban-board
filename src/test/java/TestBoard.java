import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;
import static org.testfx.assertions.api.Assertions.assertThat;
import utils.ComponentMaker;
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
    public void test1() {
        clickOn("#addButton");
        targetWindow("Name your board");
        //assertThat(targetWindow("Name your board").lookup("#okButton").queryButton()).hasText("Ok");
    }
}