import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import ui.HomePage;
import javafx.stage.Stage;

import java.io.IOException;

import org.junit.Test;

public class TestBoard extends ApplicationTest {

    @Override 
    public void start(Stage primaryStage) throws IOException {
        new HomePage().start(primaryStage);
    }
}