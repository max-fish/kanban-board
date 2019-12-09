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
    public void TestIfCardNameCanBeAdded() {
        createCard();

        clickOn("Add Card Title");
        write("Test");
        sleep(500);
    }

    @Test
    public void TestDeleteCard() {
        createCard();

        //Move to delete button
        moveTo("Add Card Title");
        moveBy(92, -17);

        clickOn(MouseButton.PRIMARY);
        sleep(500);
    }
}