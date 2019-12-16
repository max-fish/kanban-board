package front_end;

import org.junit.Test;
import javafx.scene.input.MouseButton;

public class TestStats extends BaseTest 
{
    @Test
    public void TestStatsButtons() {
        sleep(500);
        openBoard();
        moveTo("#homepage");

        moveBy(-63, 55);
        clickOn(MouseButton.PRIMARY);
        sleep(1000);

        clickOn("Lead time");
        sleep(1000);

        clickOn("Average WIP");
        sleep(1000);
    }
}