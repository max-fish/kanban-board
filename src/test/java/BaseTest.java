import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;

import ui.HomePage;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;

import java.io.IOException;



public class BaseTest extends ApplicationTest 
{
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

    /**
     * The method will open a basic board called 'Board'
     */
    protected void openBoard() {
        clickOn("#addButton");

        //Fill in dialogue box to name board
        clickOn("Board Name");
        write("Board");
        clickOn("Ok");
        clickOn("Board");
    }

    /**
     * The method will create a column in a new board
     */
    protected void createColumn() {
        openBoard();

        clickOn("#addColumn");
    }

    /**
     * The method will create a card in a column
     */
    protected void createCard() {
        createColumn();

        //Move the mouse to the add button
        moveTo("Add columnModel name");
        moveBy(78, -50);
        
        clickOn(MouseButton.PRIMARY);
    }
}