package front_end;

import org.junit.Test;
import javafx.scene.input.MouseButton;

public class TestColumns extends BaseTest
{
    @Test
    public void testAddColumn() {
        sleep(500);
        createColumn();
        sleep(500);
    }

    @Test
    public void testIfColumnNameIsEditable() {
        sleep(500);
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
        sleep(500);
        createColumn();
        sleep(500);
        //Click on work in progress
        clickOn("Backlog");
        sleep(500);
        moveTo("Work in Progress");
        moveBy(0,10);
        clickOn(MouseButton.PRIMARY);
        sleep(500);

        //Verify 'work in progress' is selected
        moveTo("Work in Progress");
        sleep(500);
    }

    @Test
    public void testIfwipLimitDropDown() {
        sleep(500);
        createColumn();

        clickOn("#wipLimitDropDown");

        //Move to scroll bar
        moveBy(35,30);
        sleep(500);
        //Drag the slider down
        press(MouseButton.PRIMARY);
        moveBy(0,30);
        release(MouseButton.PRIMARY);

        sleep(500);
        clickOn("5");

        //Verify 5 was clicked
        moveTo("5");
        sleep(500);
    }

    @Test
    public void TestDeleteColumn() {
        sleep(500);
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
        sleep(500);
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