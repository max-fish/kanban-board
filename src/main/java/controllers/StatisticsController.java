package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import data.model.StatisticsModel;

import java.net.URL;

import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Label overallVelocity;
    @FXML
    private Label leadTimeCalc;
    @FXML
    private Label avgWIP;

    private StatisticsModel statisticsModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setStatisticsModel(StatisticsModel columnModel) {
        this.statisticsModel = columnModel;
    }

    public void displayStats(){
        if(!statisticsModel.getBoard().hasCompleteColumn()){
            overallVelocity.setText("To view the overall velocity you need to assign a Completed Work column.");
            leadTimeCalc.setText("To view the lead time you need to assign a Completed Work column.");
        }
        else {
            int velocity = statisticsModel.getOverallVelocity();
            int leadTime = statisticsModel.getLeadTime();

            if(velocity == -1) overallVelocity.setText("There's no story points on the Completed Work column yet");
            else overallVelocity.setText(overallVelocity + " story points per week");

            if(leadTime == -1) leadTimeCalc.setText("There's no story points on the Completed Work column yet");
            else leadTimeCalc.setText(leadTime + " weeks per task");
        }
    }



}
