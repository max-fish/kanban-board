package back_end;

import data.model.BoardModel;
import data.model.CardModel;
import data.model.ColumnModel;
import data.model.StatisticsModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * This class makes sure the {@link StatisticsModel} correctly stores, retrieves, and calculates the statistics
 * of the board
 */
public class StatisticsModelTest
{
    private StatisticsModel emptyBoardStats;
    private BoardModel boardModel;
    private StatisticsModel statisticsModel;
    private double activeWeeks;

    @Before
    public void init(){
        BoardModel emptyBoardModel = new BoardModel("Test empty board");
        emptyBoardStats = new StatisticsModel(emptyBoardModel);

        boardModel = new BoardModel("Test populated board");
        statisticsModel = new StatisticsModel(boardModel);

        setUpTestBoard();
    }

    public void setUpTestBoard() {
        LocalDate boardCreationDate = LocalDate.of(2019, 11, 24);
        boardModel.setCreationDate(boardCreationDate);
        activeWeeks = boardModel.getActiveWeeks();

        //0
        ColumnModel backlogColumnTest = new ColumnModel(boardModel);
        backlogColumnTest.setRole(Constants.ColumnRole.BACKLOG);
        boardModel.addColumn(backlogColumnTest);

        //1
        ColumnModel WIPColumnTest = new ColumnModel(boardModel);
        WIPColumnTest.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        boardModel.addColumn(WIPColumnTest);

        //2
        ColumnModel CompletedColumnTest = new ColumnModel(boardModel);
        CompletedColumnTest.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(CompletedColumnTest);

        addCards();
    }

    public CardModel createTestCompleteCard(LocalDate completedDate, int storyPoints){
        CardModel card = new CardModel(boardModel.getColumns().get(2));
        card.setCreationDate(LocalDate.of(2019,11, 25));
        card.setEnterWIPDate(LocalDate.of(2019,11, 25));
        boardModel.getColumns().get(2).addCard(card);
        card.setCompletedDate(completedDate);
        card.setStoryPoint(storyPoints);

        return card;
    }

    public void addCards(){
        createTestCompleteCard(LocalDate.of(2019,11,26), 1);
        createTestCompleteCard(LocalDate.of(2019,11,28), 2);
        createTestCompleteCard(LocalDate.of(2019,12,4), 3);
        createTestCompleteCard(LocalDate.of(2019,12,6), 2);
        createTestCompleteCard(LocalDate.of(2019,12,9), 3);

        CardModel testCard6 = new CardModel(boardModel.getColumns().get(1));
        testCard6.setCreationDate(LocalDate.of(2019,11, 25));
        testCard6.setStoryPoint(3);
        boardModel.getColumns().get(1).addCard(testCard6);
    }

    /**
     * There should be no velocity for an empty board
     */
    @Test
    public void TestEmptyBoardOverallVelocity(){
        assertNull(emptyBoardStats.getOverallVelocity());
    }

    /**
     * There should be no lead time for an empty board
     */
    @Test
    public void TestEmptyBoardLeadTime(){
        assertNull(emptyBoardStats.getLeadTime());
    }

    /**
     * There should be no average work in progress for an empty board
     */
    @Test
    public void TestEmptyBoardAvgWIP(){ assertNull(emptyBoardStats.getAverageWIP()); }

    /**
     * Check whether getOverallVelocity correctly calculates the overall velocity
     */
    @Test
    public void TestOverallVelocity() { assertEquals(statisticsModel.getOverallVelocity()[0]/activeWeeks, 11.0/activeWeeks, 0.01); }

    /**
     * Check whether getLeadTime correctly calculates the lead time
     */
    @Test
    public void TestLeadTime(){
        int[] leadTimeWeekArray= statisticsModel.getLeadTime();
        int daySum = 0;
        for(int j = 1; j<leadTimeWeekArray.length; j++){ daySum += leadTimeWeekArray[j]; }
        System.out.println("Day sum: "+ daySum);
        assertEquals(daySum / activeWeeks, 9 / activeWeeks, 0.01);
    }

    /**
     * Check whether getAverageWip correctly calculates the average work in progress
     */
    @Test
    public void TestAverageWIP() {
        assertEquals(statisticsModel.getAverageWIP()[0] / activeWeeks, 5/activeWeeks, 0.01); }
}
