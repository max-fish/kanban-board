package ui;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTabPane;
import controllers.StatisticsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Statistics extends JFXPopup {

    private StatisticsController controller;

    public Statistics() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/layouts/statistics_ui.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStyle("-fx-background-color: transparent;");
        setPopupContent(root);
        controller = fxmlLoader.getController();
    }

    public StatisticsController getController() {
        return controller;
    }
}
