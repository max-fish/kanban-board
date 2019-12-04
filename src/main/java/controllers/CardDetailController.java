package controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.CardDetailModel;
import model.StatisticsModel;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CardDetailController implements Initializable {


    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXTextArea storyPointTextArea;

    private CardDetailModel cardDetailModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setCardDetailModel(CardDetailModel cardDetailModel) {
        this.cardDetailModel = cardDetailModel;
        titleTextField.setText(cardDetailModel.getCard().get_title());
    }

}
