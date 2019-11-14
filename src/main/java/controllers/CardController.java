package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Card;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CardController {
    @FXML
    private TextField titleTF;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private  TextArea descTextArea;

    @FXML
    private Button add;

    private Stage stage;

    private Card card;

    public CardController(Card card){
        this.card = card;
    }

    public void showStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/kanban_card_ui.fxml"));
            fxmlLoader.setController(this);
            Parent cardView = fxmlLoader.load();
            this.stage = new Stage();
            stage.setTitle(card.title);
            stage.show();
        } catch (IOException exception) {
            System.err.println(exception.toString());
        }

    }




}
