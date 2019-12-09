import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;

import ui.HomePage;
import javafx.stage.Stage;

import org.junit.Test;

import java.io.IOException;



public class BaseTest extends ApplicationTest {

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

    protected void openBoard() {
        clickOn("#addButton");
        clickOn("Board Name");
        write("Board");
        clickOn("Ok");
        clickOn("Board");
    }

}