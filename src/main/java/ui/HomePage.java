package ui;

import com.jfoenix.controls.JFXDecorator;
import controllers.HomePageController;
import utils.FileIO;
import data.db.KanbanModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the starting point of the application. It sets up the initial state of the program.
 */
public class HomePage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // create the file loader
        FileIO.instance().init(primaryStage);

        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/layouts/home_page_ui.fxml"));
        Parent root = fxmlLoader.load();
        HomePageController homePageController = fxmlLoader.getController();
        //homePageController.initConfig();

        KanbanModel.instance(); // create the data.model for the application
        KanbanModel.instance().setHomePageController(homePageController);
        KanbanModel.instance().loadSession();

        JFXDecorator jfxDecorator = new JFXDecorator(primaryStage, root);
        jfxDecorator.setCustomMaximize(true);
        jfxDecorator.setTitle("Kanban Board");
        Scene scene = new Scene(jfxDecorator, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styling/scene_styling.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> KanbanModel.instance().saveSession());

        primaryStage.show();
    }
}
