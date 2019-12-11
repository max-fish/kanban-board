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

    public int countCompletedStoryPoints() {
        int storyPointsCount = 0;
        //Go thorugh all the completed columns of the board
        for (ColumnModel col : board.getCompletedColumns()) {
            //Go through all the cards in the "completed" columns
            for (CardModel card : col.getCards()) {
                storyPointsCount += card.getStoryPoint();
            }
        }
        return storyPointsCount;
    }

    public int getOverallVelocity() {
        int activeWeeks = (int) ChronoUnit.WEEKS.between(board.getCreationDate(), LocalDate.now()) + 1;
        int storyPointsCount = countCompletedStoryPoints();
        if(storyPointsCount == 0) return -1;
        return storyPointsCount / activeWeeks;
    }

    public int getLeadTime() {
        int leadTimes = 0;
        int cardCount = 0;

        for(ColumnModel col : board.getCompletedColumns()){
            for (CardModel card : col.getCards()) {
                leadTimes += ChronoUnit.WEEKS.between(card.getCreationDate(), card.getCompletedDate());
                cardCount++;
            }
        }
        if(cardCount == 0) return -1;
        return leadTimes / cardCount;
    }

    public int getAverageWIP() {
        int activeWeeks = (int) ChronoUnit.WEEKS.between(board.getCreationDate(), LocalDate.now()) + 1;
        int storyPoints = countCompletedStoryPoints();
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
