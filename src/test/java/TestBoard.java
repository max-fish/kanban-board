import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;

import ui.HomePage;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class TestBoard extends ApplicationTest {

    @Override
    public void init() throws Exception {
        FxToolkit.registerStage(() -> new Stage());
    }

    @Override 
    public void start(Stage primaryStage) throws IOException {
        new HomePage().start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
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