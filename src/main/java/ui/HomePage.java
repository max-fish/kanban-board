package ui;

import com.jfoenix.controls.JFXDecorator;
import controllers.HomePageController;
import model.KanbanModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        KanbanModel.instance(); // create the model for the application

        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/layouts/home_page_ui.fxml"));
        Parent root = fxmlLoader.load();
        JFXDecorator jfxDecorator = new JFXDecorator(primaryStage, root);
        jfxDecorator.setCustomMaximize(true);
        jfxDecorator.setTitle("Kanban Board");
        Scene scene = new Scene(jfxDecorator, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styling/scene_styling.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
