package ui;

import controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import java.io.IOException;


public class KanbanColumn {

    public BorderPane create(KanbanBoardController controller) throws IOException
    {
        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/kanban_column_ui.fxml"));
        loader.load();
        ColumnController columnController = loader.getController();
        columnController.setController(controller);
        return loader.getRoot();
    }
}

