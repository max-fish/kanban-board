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


    public int[] getOverallVelocity() {
        //Position 0 containing total sum of story points, other positions represent weeks of activity
        int[] storyPoints = new int[(int)board.getActiveWeeks()+1];
        //Go through all the completed columns of the board
        for (ColumnModel col : board.getCompletedColumns()) {
            //Go through all the cards in the "completed" columns
            for (CardModel card : col.getCards()) {

                int week = (int)ChronoUnit.WEEKS.between(card.getCreationDate(), card.getCompletedDate());
                int weeknum = week+1;
                System.out.println("Completion date: "+card.getCompletedDate() +" , inserted into week: "+ weeknum);
                storyPoints[week+1] += card.getStoryPoint();
                storyPoints[0] += card.getStoryPoint();
            }
        }
        if(storyPoints[0] == 0) return null;
        return storyPoints;
    }


    public double getLeadTime() {
        double leadTimes = 0;
        double cardCount = 0;

        for(ColumnModel col : board.getCompletedColumns()){
            for (CardModel card : col.getCards()) {
                leadTimes += ChronoUnit.WEEKS.between(card.getCreationDate(), card.getCompletedDate());
                cardCount++;
            }
        }
        if(cardCount == 0) return -1;
        return leadTimes / cardCount;
    }

    //Get average WIP in the active week given.
    public double getAverageWIP(double activeWeeks) {
        double storyPoints = 0;
                //countCompletedStoryPoints();
        //Add story points currently in WIP
        for(ColumnModel col : board.getWIPColumns()){
            for (CardModel card : col.getCards()) {
                storyPoints += card.getStoryPoint();
            }
        }
        if(storyPoints == 0) return -1;
        return storyPoints / activeWeeks;

    }
}
