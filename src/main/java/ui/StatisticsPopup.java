package ui;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTabPane;
import controllers.StatisticsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 *  This class inflates a {@link JFXPopup} with a custom fxml layout, making it a StatisticsPopup.
 */
public class StatisticsPopup extends JFXPopup {

    private StatisticsController controller;

    public StatisticsPopup() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/layouts/statistics_ui.fxml"));
        TabPane root = null;
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
