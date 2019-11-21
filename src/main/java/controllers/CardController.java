package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import model.Card;


public class CardController {
    @FXML
    private JFXTextField cardTitle;

//    @FXML
//    private DatePicker dueDatePicker;

    @FXML
    private JFXTextField descTextArea;

    @FXML
    private BorderPane rootPane;

//    @FXML
//    private Button add;

    private Card card;


    public void setCard(Card card)
    {
        this.card = card;
    }

    public void setTitleChangeListener()
    {
        cardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            card.setTitle(newValue);
        });
    }

    public void setDescChangeListener()
    {
        descTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            card.setDescription(newValue);
        });
    }




}
