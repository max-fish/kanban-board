package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.StatisticsModel;

import java.net.URL;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Label overallVelocity;
    @FXML
    private Label leadTime;
    @FXML
    private Label avgWIP;

    private StatisticsModel statisticsModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setStatisticsModel(StatisticsModel columnModel) {
        this.statisticsModel = columnModel;
    }

    public void displayStats(int cardsPerWeek){
        overallVelocity.setText(getOverallVelocity() + " cards per week");
    }


    public int getOverallVelocity(){
        // Number of weeks the board has been created
        int activeWeeks = (int) ChronoUnit.WEEKS.between(statisticsModel.getBoard().getCreationDate(), LocalDate.now()) + 1;
        // Array in which each position represents the number of week and the value the num of cards added that week
        int cardsPerWeek[] = new int[activeWeeks];
        for(LocalDate date : statisticsModel.getCardDates()){
            int week = (int) ChronoUnit.WEEKS.between(statisticsModel.getBoard().getCreationDate(), date);
            cardsPerWeek[week]++;
        }
        return Arrays.stream(cardsPerWeek).sum() / activeWeeks;
    }
}
