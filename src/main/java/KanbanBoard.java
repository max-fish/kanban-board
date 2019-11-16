import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class KanbanBoard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/kanban_board_ui.fxml"));
        JFXDecorator jfxDecorator = new JFXDecorator(primaryStage, root);
        jfxDecorator.setCustomMaximize(true);
        jfxDecorator.setTitle("Kanban Board");
        Scene scene = new Scene(jfxDecorator, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("styling/scene_styling.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
