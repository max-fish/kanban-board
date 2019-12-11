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
        clickOn("Add column name");
        write("Column1");

        //Verify name changed
        moveTo("Column1");
        sleep(500);
    }
    
    @Test
    public void testIfColumnRoleCanBeSet() {
        createColumn();
        sleep(5);
        //Click on work in progress
        clickOn("Backlog");
        WaitForAsyncUtils.waitForFxEvents();
        moveTo("Work in Progress");
        moveBy(0,10);
        clickOn(MouseButton.PRIMARY);
        WaitForAsyncUtils.waitForFxEvents();

        //Verify 'work in progress' is selected
        moveTo("Work in Progress");
        sleep(500);
    }

    @Test 
    public void testIfwipLimitDropDown() {
        createColumn();

        clickOn("#wipLimitDropDown");

        //Move to scroll bar
        moveBy(35,30);

        //Drag the slider down
        press(MouseButton.PRIMARY);
        moveBy(0,30);
        release(MouseButton.PRIMARY);

        WaitForAsyncUtils.waitForFxEvents();
        clickOn("5");

        //Verify 5 was clicked
        moveTo("5");
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
        clickOn("Add column name");
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