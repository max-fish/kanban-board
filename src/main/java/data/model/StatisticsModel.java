package data.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StatisticsModel
{
    private BoardModel board;

    public StatisticsModel(BoardModel board){
        this.board = board;
    }

    public BoardModel getBoard(){ return board; }


    /**Return array where each position from 1 represents a week and its value the completed story points that week.
     * Position 0 contains the total story points over all weeks.
    */
    public int[] getOverallVelocity() {
        //Position 0 containing total sum of story points, other positions represent weeks of activity
        int[] storyPoints = new int[(int)board.getActiveWeeks()+1];
        //Go through all the completed columns of the board
        for (ColumnModel col : board.getCompletedColumns()) {
            //Go through all the cards in the "completed" columns
            for (CardModel card : col.getCards()) {

                int week = (int)ChronoUnit.WEEKS.between(card.getCreationDate(), card.getCompletedDate());
                storyPoints[week+1] += card.getStoryPoint();
                storyPoints[0] += card.getStoryPoint();
            }
        }
        if(storyPoints[0] == 0) return null;
        return storyPoints;
    }

    /**Return array where each position from 1 represents a week and its value the lead time per story point that week.
     * Position 0 contains the total count of completed storypoints in active weeks.
     */
    public int[] getLeadTime() {

        int[] leadTimes = new int[(int)board.getActiveWeeks()+1];

        for(ColumnModel col : board.getCompletedColumns()){
            for (CardModel card : col.getCards()) {
                int week = (int)ChronoUnit.WEEKS.between(card.getCreationDate(), card.getCompletedDate());
                leadTimes[week+1] += ChronoUnit.DAYS.between(card.getEnterWIPDate(), card.getCompletedDate());
                leadTimes[0] += card.getStoryPoint();
            }
        }
        if(leadTimes[0] == 0) return null;
        for(int week = 1; week<leadTimes.length; week++){ leadTimes[week] = leadTimes[week] / getOverallVelocity()[week]; }
        return leadTimes;
    }

    /**Return array where each position from 1 represents a week and its value the avg story points in WIP that week.
     * Position 0 contains the total sum of the averages.
     */
    public double[] getAverageWIP() {
        int[] overallVelocities = getOverallVelocity();
        int[] averageLeadTimes = getLeadTime();
        if(overallVelocities == null && averageLeadTimes == null) return null;

        double[] WIP = new double[(int)board.getActiveWeeks()+1];
        for(int i = 1; i<overallVelocities.length; i++){
            WIP[i] += overallVelocities[i] * averageLeadTimes[i]/7.0;
            WIP[0] += overallVelocities[i] * averageLeadTimes[i]/7.0;
        }
        if(WIP[0] == 0) return null;
        return WIP;
    }

}
