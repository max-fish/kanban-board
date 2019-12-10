import org.junit.Test;
import javafx.scene.input.MouseButton;
import org.testfx.util.WaitForAsyncUtils;

public class TestColumns extends BaseTest
{
    @Test
    public void testAddColumn() {
        createColumn();
        sleep(500);
    }

    @Test
    public void testIfColumnNameIsEditable() {
        createColumn();

        //Add column name
        clickOn("New Column");
        eraseText(10);
        write("Column1");
        sleep(500);
    }
    
    @Test
    public void testIfColumnRoleCanBeSet() {
        createColumn();

        //Click on work in progress
        clickOn("Add column role");
        clickOn("Work In Progress");

        //Click somewhere else to close the drop down
        moveBy(200, 50);
        clickOn(MouseButton.PRIMARY);

        //Verify 'work in progress' is selected
        WaitForAsyncUtils.waitForFxEvents();
        moveTo("Work In Progress");
        sleep(500);
    }

    @Test 
    public void testIfwipLimitDropDown() {
        createColumn();

        clickOn("#wipLimitDropDown");

        //Move to scroll bar
        moveBy(85,30);

        //Drag the slider down
        press(MouseButton.PRIMARY);
        moveBy(0,30);
        release(MouseButton.PRIMARY);

        WaitForAsyncUtils.waitForFxEvents();
        clickOn("5");

        sleep(500);
    }

    @Test
    public void TestDeleteColumn() {
        createColumn();

        //Open column menu
        clickOn("#columnMenuButton");

        //Click on delete button
        moveTo("Delete");
        moveBy(0, 30);
        clickOn(MouseButton.PRIMARY);

        //Confirm deletion
        moveTo("Delete Column");
        moveBy(220,95);
        clickOn(MouseButton.PRIMARY);

        sleep(500);
    }

    @Test
    public void testDragAndDropColumn() {
        //Add name to column
        createColumn();
        clickOn("New Column");
        eraseText(10);
        write("Column1");

        //Create second column
        clickOn("#addColumn");

        moveTo("#dragButton");

        press(MouseButton.PRIMARY);
        moveBy(300,0);
        release(MouseButton.PRIMARY);

        sleep(500);
    }
}