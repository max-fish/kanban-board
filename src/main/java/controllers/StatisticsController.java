package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import data.model.StatisticsModel;

import java.net.URL;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Label overallVelocity;
    @FXML
    private Label leadTime;
    @FXML
    private Label averageWIP;

    @FXML
    private LineChart<Number, Number> linechartOverallVelocity;
    @FXML
    private LineChart<Number, Number> linechartLeadTime;
    @FXML
    private LineChart<Number, Number> linechartAverageWIP;

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
            double overallVelocityVal = -1;
            if(statisticsModel.getOverallVelocity() != null){
                overallVelocityVal = statisticsModel.getOverallVelocity()[0] / statisticsModel.getBoard().getActiveWeeks();
            }
            double leadTimeVal = statisticsModel.getLeadTime();
            double averageWIPVal = statisticsModel.getAverageWIP(statisticsModel.getBoard().getActiveWeeks());

            if(overallVelocityVal == -1) overallVelocity.setText("There's no story points on the Completed Work column yet");
            else overallVelocity.setText(overallVelocityVal + " story points per week");

            if(leadTimeVal == -1) leadTime.setText("There's no story points on the Completed Work column yet");
            else leadTime.setText(leadTimeVal + " weeks per task");

            if(averageWIPVal == -1) averageWIP.setText("There's no story points on the Work In Progress columns yet");
            else averageWIP.setText(averageWIPVal + " story points in WIP");

            displayLineChartOverallVelocity();
            displayLineChartAverageWIP();
        }
    }

    public void displayLineChartOverallVelocity(){
        XYChart.Series ovSeries = new XYChart.Series();

        double weeks = statisticsModel.getBoard().getActiveWeeks();
        System.out.println("week: ");
        System.out.println(weeks);

        int[] storyPoints = statisticsModel.getOverallVelocity();
        int visited = 0;
        for(int x= 1; x<storyPoints.length ; x++){
            System.out.println("Week: "+x+", storypoints: "+ (storyPoints[x]+visited)/x);
            ovSeries.getData().add(new XYChart.Data(x-1, (storyPoints[x]+visited)/(double)x));
            visited += storyPoints[x];
        }

        linechartOverallVelocity.getData().addAll(ovSeries);
    }

    public void displayLineChartAverageWIP(){
        XYChart.Series wipSeries = new XYChart.Series();

        double week = statisticsModel.getBoard().getActiveWeeks();
        System.out.println("week: ");
        System.out.println(week);

        for(int x= 1; x<= week ; x++){
            wipSeries.getData().add(new XYChart.Data(x-1, statisticsModel.getAverageWIP(x)));
        }

        linechartAverageWIP.getData().addAll(wipSeries);
    }




}
