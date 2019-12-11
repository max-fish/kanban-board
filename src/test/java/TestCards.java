import org.junit.Test;
import javafx.scene.input.MouseButton;
import org.testfx.util.WaitForAsyncUtils;

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

        clickOn("Add Card Title");

        write("Test");

        //Verify name has been changed
        moveTo("Test");
        sleep(500);
    }

    @Test
    public void testIfExtraDetailsCanBeAddedToCard() {
        createCard();

        //Click on edit button
        moveTo("Add Card Title");
        moveBy(-93, -53);
        clickOn(MouseButton.PRIMARY);

        moveTo("Title:");
        moveBy(60, 0);
        clickOn(MouseButton.PRIMARY);

        write("Test");

        //Move to description box
        moveTo("Task description:");
        moveBy(0,40);
        clickOn(MouseButton.PRIMARY);

        write("This is a test.");

        //Change story point
        clickOn("1");
        clickOn("3");

        clickOn("Save");

        //Verify that details are saved
        moveTo("Test");
        moveBy(-93, -53);
        clickOn(MouseButton.PRIMARY);
        WaitForAsyncUtils.waitForFxEvents();
        moveTo("This is a test.");
        sleep(500);
    }

    @Test
    public void TestDeleteCard() {
        createCard();

        //Move to delete button
        moveTo("Add Card Title");
        moveBy(93, -53);
        clickOn(MouseButton.PRIMARY);
        sleep(500);
    }

    @Test
    public void testIfCardCanBeDragedInSameColumn() {
        createCard();

        clickOn("Add Card Title");

        write("Test");

        clickOn("#columnMenuButton");

        clickOn("Add card");


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

        moveTo("Add Card Title");
        moveBy(0,-20);

        press(MouseButton.PRIMARY);
        moveBy(250,0);
        release(MouseButton.PRIMARY);
        sleep(500);
    }
}