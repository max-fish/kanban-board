import org.junit.Test;
import javafx.scene.input.MouseButton;

public class TestCards extends BaseTest
{
    @Test
    public void TestAddCard() {
        createCard();
        sleep(500);
    }

    @Test
    public void TestIfCardNameCanBeEditied() {
        createCard();

        //Move to end of text
        moveTo("New KanbanCard");
        moveBy(50,0);
        clickOn(MouseButton.PRIMARY);

        eraseText(14);
        write("Test");
        sleep(500);
    }

    @Test
    public void testIfExtraDetailsCanBeAddedToCard() {
        createCard();

        //Click on edit button
        moveTo("New KanbanCard");
        moveBy(-93, -53);
        clickOn(MouseButton.PRIMARY);

        //Move to description box
        moveTo("Task description:");
        moveBy(0,20);
        clickOn(MouseButton.PRIMARY);

        write("This is a test.");

        //Change story point
        clickOn("1");
        clickOn("3");

        clickOn("Save");

        //Verify that details are saved
        moveTo("New KanbanCard");
        moveBy(-93, -53);
        clickOn(MouseButton.PRIMARY);

        moveTo("This is a test.");

        sleep(500);
    }

    @Test
    public void TestDeleteCard() {
        createCard();

        moveBy(100,100);
        clickOn(MouseButton.PRIMARY);

        //Move to delete button
        moveTo("New KanbanCard");
        moveBy(93, -53);
        clickOn(MouseButton.PRIMARY);
        
        sleep(500);
    }

    @Test
    public void testIfCardCanBeDragedInSameColumn() {
        createCard();

        //Move to end of text
        moveTo("New KanbanCard");
        moveBy(50,0);
        clickOn(MouseButton.PRIMARY);

        eraseText(14);
        write("Test");

        clickOn("#columnMenuButton");

        clickOn("Add card");

        moveBy(100,100);
        clickOn(MouseButton.PRIMARY);

        moveTo("Test");
        moveBy(0,-20);

        press(MouseButton.PRIMARY);
        moveBy(0,150);
        release(MouseButton.PRIMARY);

        sleep(500);
    }

    @Test
    public void TestIfCardCanBeDraggedToNextColumn() {
        createCard();

        clickOn("#addColumn");

        moveTo("New KanbanCard");
        moveBy(0,-20);

        press(MouseButton.PRIMARY);
        moveBy(250,0);
        release(MouseButton.PRIMARY);

        sleep(500);
    }
}