import data.model.BoardModel;
import data.model.CardModel;
import data.model.ColumnModel;
import data.model.StatisticsModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class StatisticsModelTest
{
    private BoardModel emptyBoardModel;
    private StatisticsModel emptyBoardStats;

    private BoardModel boardModel;
    private StatisticsModel statisticsModel;


    @Before
    public void init(){
        emptyBoardModel = new BoardModel("Test empty board");
        emptyBoardStats = new StatisticsModel(emptyBoardModel);

        boardModel = new BoardModel("Test populated board");
        statisticsModel = new StatisticsModel(boardModel);
        setUpTestBoard();
    }

    public void setUpTestBoard() {
        LocalDate boardCreationDate = LocalDate.of(2019, 11, 24);
        boardModel.setCreationDate(boardCreationDate);

        //0
        ColumnModel backlogColumnTest = new ColumnModel();
        backlogColumnTest.setRole(Constants.ColumnRole.BACKLOG);
        boardModel.addColumn(backlogColumnTest);

        //1
        ColumnModel WIPColumnTest = new ColumnModel();
        WIPColumnTest.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);
        boardModel.addColumn(WIPColumnTest);

        //2
        ColumnModel CompletedColumnTest = new ColumnModel();
        CompletedColumnTest.setRole(Constants.ColumnRole.COMPLETED_WORK);
        boardModel.addColumn(CompletedColumnTest);

        addCards();

    }

    public CardModel createTestCompleteCard( LocalDate completedDate, int storyPoints){
        CardModel card = new CardModel();
        card.setCreationDate(LocalDate.of(2019,11, 25));
        boardModel.getColumns().get(2).addCard(card);
        card.setCompletedDate(completedDate);
        card.setStoryPoint(storyPoints);

        return card;
    }

    public void addCards(){
        CardModel testCard1 = createTestCompleteCard( LocalDate.of(2019,11,26), 1);
        CardModel testCard2 = createTestCompleteCard(LocalDate.of(2019,11,28), 2);
        CardModel testCard3 = createTestCompleteCard(LocalDate.of(2019,12,4), 3);
        CardModel testCard4 = createTestCompleteCard(LocalDate.of(2019,12,6), 2);
        CardModel testCard5 = createTestCompleteCard(LocalDate.of(2019,12,9), 3);

        CardModel testCard6 = new CardModel();
        testCard6.setCreationDate(LocalDate.of(2019,11, 25));
        testCard6.setStoryPoint(3);
        boardModel.getColumns().get(1).addCard(testCard6);
    }

    @Test
    public void TestEmptyBoardOverallVelocity(){
        assertEquals(emptyBoardStats.getOverallVelocity(),  null);
    }

    @Test
    public void TestEmptyBoardLeadTime(){
        assertEquals(emptyBoardStats.getLeadTime(),  -1, 0.001);
    }

    @Test
    public void TestEmptyBoardAvgWIP(){
        assertEquals(emptyBoardStats.getAverageWIP(emptyBoardModel.getActiveWeeks()),  -1, 0.001);
    }

    @Test
    public void TestOverallVelocity() { assertEquals(statisticsModel.getOverallVelocity()[0]/boardModel.getActiveWeeks(), 11.0/boardModel.getActiveWeeks(), 0.01); }

    @Test
    public void TestLeadTime(){ assertEquals(statisticsModel.getLeadTime(), 0.8, 0.01); }

    @Test
    public void TestAverageWIP() { assertEquals(statisticsModel.getAverageWIP(boardModel.getActiveWeeks()), 14.0/boardModel.getActiveWeeks(), 0.01); }


}
