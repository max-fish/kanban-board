import org.junit.Test;
import javafx.scene.input.MouseButton;

public class TestColumns extends BaseTest
{
    @Test
    public void testAddColumn() {
        createColumn();
        sleep(500);
    }

    @Test
    public void testIfColumnNameAndRoleAreModifiable() {
        createColumn();

        //Add column name
        clickOn("Add columnModel name");
        write("Column1");

        //Add column role
        clickOn("Add columnModel role");
        write("Test");
        sleep(500);
    }

    @Test
    public void TestDeleteColumn() {
        createColumn();

        //Move to the delete button
        moveTo("Add columnModel name");
        moveBy(-84, -50);

        clickOn(MouseButton.PRIMARY);
        sleep(500);
    }
}