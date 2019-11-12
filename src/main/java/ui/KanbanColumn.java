package ui;

import com.jfoenix.controls.JFXDecorator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.io.IOException;

import static javafx.application.Application.launch;

public class KanbanColumn {

    public BorderPane create() throws IOException
    {
        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        return FXMLLoader.load(getClass().getResource("/layouts/kanban_column_ui.fxml"));
    }
}

