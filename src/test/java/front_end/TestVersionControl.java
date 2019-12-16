package front_end;

import org.junit.Test;
import javafx.scene.input.MouseButton;

public class TestVersionControl extends BaseTest 
{
    @Test
    public void TestundoAction() {
        sleep(500);

        createCard();

        moveTo("#homepage");
        moveBy(-25, 60);

        clickOn(MouseButton.PRIMARY);

        moveTo("Card created");
        moveBy(-27, -20);

        clickOn(MouseButton.PRIMARY);

        sleep(500);
    }

    @Test
    public void TestredoAction() {
        sleep(500);

        createCard();

        moveTo("#homepage");
        moveBy(-25, 60);

        clickOn(MouseButton.PRIMARY);

        moveTo("Card created");
        moveBy(-27, -20);

        clickOn(MouseButton.PRIMARY);

        moveBy(170, 0);

        clickOn(MouseButton.PRIMARY);

        moveTo("New Card");

        sleep(500);
    }
}