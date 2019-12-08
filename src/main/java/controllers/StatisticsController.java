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
    private Label leadTime;
    @FXML
    private Label averageWIP;

    private StatisticsModel statisticsModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setStatisticsModel(StatisticsModel columnModel) {
        this.statisticsModel = columnModel;
    }

    public void displayStats(){
        if(!statisticsModel.getBoard().hasCompleteColumn()){
            overallVelocity.setText("To view the overall velocity you need to assign a role to your columns.");
            leadTime.setText("To view the overall velocity you need to assign a role to your columns.");
            averageWIP.setText("To view the overall velocity you need to assign a role to your columns.");
        }
        else {
            int overallVelocityVal = statisticsModel.getOverallVelocity();
            int leadTimeVal = statisticsModel.getLeadTime();
            int averageWIPVal = statisticsModel.getAverageWIP();

            if(overallVelocityVal == -1) overallVelocity.setText("There's no story points on the Completed Work column yet");
            else overallVelocity.setText(overallVelocityVal + " story points per week");

            if(leadTimeVal == -1) leadTime.setText("There's no story points on the Completed Work column yet");
            else leadTime.setText(leadTimeVal + " weeks per task");

            if(averageWIPVal == -1) averageWIP.setText("There's no story points on the Work In Progress columns yet");
            else averageWIP.setText(averageWIPVal + " story points in WIP");
        }
    }



}
