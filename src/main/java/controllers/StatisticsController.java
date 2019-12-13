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

import java.text.DecimalFormat;
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

    private int[] overallVelocityWeekArray;
    private int[] leadTimeWeekArray;
    private double[] averageWIPWeekArray;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    void setStatisticsModel(StatisticsModel statisticsModel) {
        this.statisticsModel = statisticsModel;
        this.overallVelocityWeekArray = statisticsModel.getOverallVelocity();
        this.leadTimeWeekArray = statisticsModel.getLeadTime();
        this.averageWIPWeekArray = statisticsModel.getAverageWIP();
    }

    public void displayStats(){
        if(!statisticsModel.getBoard().hasCompleteColumn()){
            overallVelocity.setText("To view the overall velocity you need to assign a role to your columns.");
            leadTime.setText("To view the overall velocity you need to assign a role to your columns.");
            averageWIP.setText("To view the overall velocity you need to assign a role to your columns.");
        }
        else {
            double overallVelocityVal = -1;
            if(overallVelocityWeekArray != null){
                overallVelocityVal = overallVelocityWeekArray[0] / statisticsModel.getBoard().getActiveWeeks();
            }
            double leadTimeVal = -1;
            if(leadTimeWeekArray != null){
                int daySum = 0;
                for(int j = 1; j<leadTimeWeekArray.length; j++){ daySum += leadTimeWeekArray[j]; }
                leadTimeVal = daySum / statisticsModel.getBoard().getActiveWeeks();
            }
            double averageWIPVal = -1;
            if(averageWIPWeekArray != null){
                averageWIPVal = averageWIPWeekArray[0] / statisticsModel.getBoard().getActiveWeeks();
            }


            if(overallVelocityVal == -1) overallVelocity.setText("There's no story points on the Completed Work column yet");
            else overallVelocity.setText(new DecimalFormat("#.0").format(overallVelocityVal) + " story points per week");

            if(leadTimeVal == -1) leadTime.setText("There's no story points on the Completed Work column yet");
            else leadTime.setText(new DecimalFormat("#.0").format(leadTimeVal) + " days per story point");

            if(averageWIPVal == -1) averageWIP.setText("There's no story points on the Work In Progress columns yet");
            else averageWIP.setText(new DecimalFormat("#.0").format(averageWIPVal) + " story points in WIP per week");

            displayLineChartOverallVelocity();
            displayLineChartLeadTime();
            displayLineChartAverageWIP();
        }
    }

    public void displayLineChartOverallVelocity(){
        XYChart.Series ovSeries = new XYChart.Series();

        double weeks = statisticsModel.getBoard().getActiveWeeks();

        int visited = 0;
        for(int x= 1; x<overallVelocityWeekArray.length ; x++){
            ovSeries.getData().add(new XYChart.Data(x-1,(overallVelocityWeekArray[x]+visited)/(double)x));
            visited += overallVelocityWeekArray[x];
        }

        linechartOverallVelocity.getData().addAll(ovSeries);
    }

    public void displayLineChartLeadTime(){
        XYChart.Series leadTimeSeries = new XYChart.Series();
        double week = statisticsModel.getBoard().getActiveWeeks();
        int visited = 0;

        for(int x= 1; x<= week ; x++){
            System.out.println("Week: "+ (x-1)+ ", Leadtime: "+ leadTimeWeekArray[x]);
            leadTimeSeries.getData().add(new XYChart.Data(x-1, (leadTimeWeekArray[x]+visited)/(double)x));
            visited += leadTimeWeekArray[x];
        }

        linechartLeadTime.getData().addAll(leadTimeSeries);
    }

    public void displayLineChartAverageWIP(){
        XYChart.Series wipSeries = new XYChart.Series();

        double week = statisticsModel.getBoard().getActiveWeeks();
        int visited = 0;
        for(int x= 1; x<= week ; x++){
            wipSeries.getData().add(new XYChart.Data(x-1, (averageWIPWeekArray[x]+visited)/(double)(x)));
            visited += averageWIPWeekArray[x];
        }

        linechartAverageWIP.getData().addAll(wipSeries);
    }




}
