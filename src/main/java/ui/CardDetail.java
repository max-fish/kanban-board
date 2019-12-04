package ui;

import com.jfoenix.controls.JFXDecorator;
import controllers.CardDetailController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardDetail {
    private CardDetailController controller;

    public CardDetail() throws IOException { start(); }

    public void start() throws IOException {
        Stage stage = new Stage();
        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/card_details_ui.fxml"));
        Parent root = fxmlLoader.load();

        JFXDecorator jfxDecorator = new JFXDecorator(stage, root);
        jfxDecorator.setCustomMaximize(true);
        jfxDecorator.setTitle("Card Details");

        controller = fxmlLoader.getController();

        Scene scene = new Scene(jfxDecorator, 800, 400);
        scene.getStylesheets().add(getClass().getResource("/styling/card_detail_styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public CardDetailController getController(){
        return controller;
    }
}
