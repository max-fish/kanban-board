package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import java.io.IOException;


public class KanbanColumn {

    public BorderPane create() throws IOException
    {
        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        return FXMLLoader.load(getClass().getResource("/layouts/kanban_column_ui.fxml"));
    }
}

