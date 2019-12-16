import org.junit.Test;
import javafx.scene.input.MouseButton;

public class TestCards extends BaseTest
{
    private void nameCard() {
        //Click on edit button
        moveTo("New Card");
        moveBy(-93, -53);
        clickOn(MouseButton.PRIMARY);

        moveTo("Title:");
        moveBy(150, 0);
        clickOn(MouseButton.PRIMARY);

        eraseText(8);
        write("Test");

        clickOn("Save");
    }

    @Test
    public void TestAddCard() {
        sleep(500);
        createCard();
        sleep(500);
    }

    @Test
    public void TestIfCardNameCanBeEditied() {
        sleep(500);
        createCard();

        nameCard();

        //Verify name has been changed
        moveTo("Test");
        sleep(500);
    }

    @Test
    public void testIfExtraDetailsCanBeAddedToCard() {
        sleep(500);
        createCard();

        //Click on edit button
        moveTo("New Card");
        moveBy(-93, -53);
        clickOn(MouseButton.PRIMARY);

        moveTo("Title:");
        moveBy(150, 0);
        clickOn(MouseButton.PRIMARY);

        eraseText(8);
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
        sleep(500);
        moveTo("This is a test.");
        sleep(500);
    }

    @Test
    public void TestDeleteCard() {
        sleep(500);
        createCard();

        //Move to delete button
        moveTo("New Card");
        moveBy(93, -53);
        clickOn(MouseButton.PRIMARY);
        
        sleep(500);
        clickOn("Delete");

        sleep(500);
    }

    @Test
    public void testIfCardCanBeDragedInSameColumn() {
        sleep(500);
        createCard();

        nameCard();

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
        sleep(500);
        createCard();

        clickOn("#addColumn");

        moveTo("New Card");
        moveBy(0,-20);

        press(MouseButton.PRIMARY);
        moveBy(250,0);
        release(MouseButton.PRIMARY);
        sleep(500);
    }
}
