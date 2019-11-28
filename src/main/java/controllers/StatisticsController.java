package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Label overallVelocity;
    @FXML
    private Label leadTime;
    @FXML
    private Label avgWIP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void displayStats(int cardsPerWeek){
        overallVelocity.setText("lala");

    }
}
