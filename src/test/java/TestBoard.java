import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.assertions.api.Assertions.assertThat;
import javafx.scene.input.InputEvent;
import javafx.scene.robot.Robot;

import ui.HomePage;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.BeforeClass;

import java.io.IOException;

public class TestBoard extends ApplicationTest {

    @Override 
    public void start(Stage primaryStage) throws IOException {
        new HomePage().start(primaryStage);
    }

    @Test
    public void test1() {
        clickOn("#test");
        sleep(5000);
    }
}