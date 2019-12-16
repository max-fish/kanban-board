package back_end;

import data.db.JSONParser;
import data.model.BoardModel;
import data.model.CardModel;
import data.model.ColumnModel;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JSONParserTest {
    private List<BoardModel> boardModels;
    private List<BoardModel> retrievedBoardModels;


    /**
     * Create a sample Data Model relation
     */
    @Before
    public void init() {
        boardModels = new ArrayList<>();

        BoardModel boardModel = new BoardModel("test");

        ColumnModel columnModel = new ColumnModel(boardModel);
        columnModel.setName("TODO");
        columnModel.setRole(Constants.ColumnRole.BACKLOG);
        columnModel.setCurrentWip(32);


        CardModel cardModel = new CardModel(columnModel);
        cardModel.setTitle("test");
        cardModel.setDescription("this is a test");

        CardModel cardModel1 = new CardModel(columnModel);
        cardModel1.setTitle("test1");
        cardModel1.setDescription("this is also a test1");


        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);

        boardModel.addColumn(columnModel);

        ColumnModel columnModel1 = new ColumnModel(boardModel);
        columnModel1.setName("Developing");
        columnModel1.setRole(Constants.ColumnRole.WORK_IN_PROGRESS);

        CardModel cardModel2 = new CardModel(columnModel);
        cardModel2.setDescription("final test");

        columnModel1.addCard(cardModel2);

        boardModel.addColumn(columnModel1);

        boardModels.add(boardModel);

        String json = JSONParser.toJson(boardModels);

        retrievedBoardModels = JSONParser.fromJson(json);
    }

    /**
     * Make sure the {@link BoardModel} retrieved from the JSON has the same name
     */
    @Test
    public void CheckBoardDetails() {
        BoardModel firstBoardModel = retrievedBoardModels.get(0);
        assertEquals("test", firstBoardModel.getName());
    }

    /**
     * Make sure the {@link ColumnModel}s  received from the JSON have the same contents as before
     */
    @Test
    public void CheckColumnDetails(){
        BoardModel firstBoardModel = retrievedBoardModels.get(0);
        ColumnModel firstColumnModel = firstBoardModel.getColumns().get(0);

        assertEquals("TODO", firstColumnModel.getName());
        assertEquals(Constants.ColumnRole.BACKLOG, firstColumnModel.getRole());
        assertEquals(32, firstColumnModel.getCurrentWip());

        ColumnModel secondColumnModel = firstBoardModel.getColumns().get(1);
        assertEquals("Developing", secondColumnModel.getName());
        assertEquals(Constants.ColumnRole.WORK_IN_PROGRESS, secondColumnModel.getRole());
    }

    /**
     * Make sure the {@link CardModel}s recieved from the JSON have the same contents as before
     */
    @Test
    public void CheckCardDetails(){
        BoardModel firstBoardModel = retrievedBoardModels.get(0);
        ColumnModel firstColumnModel = firstBoardModel.getColumns().get(0);

        CardModel firstCardModel = firstColumnModel.getCards().get(0);

        assertEquals("test", firstCardModel.getTitle());
        assertEquals("this is a test", firstCardModel.getDescription());
        assertEquals(boardModels.get(0).getColumns().get(0).getCards().get(0).getCreationDate(), firstCardModel.getCreationDate());
        assertEquals(boardModels.get(0).getColumns().get(0).getCards().get(0).getCompletedDate(), firstCardModel.getCompletedDate());
        assertEquals(1, firstCardModel.getStoryPoint());

        CardModel secondCardModel = firstColumnModel.getCards().get(1);

        assertEquals("test1", secondCardModel.getTitle());
        assertEquals("this is also a test1", secondCardModel.getDescription());
        assertEquals(boardModels.get(0).getColumns().get(0).getCards().get(1).getCreationDate(), secondCardModel.getCreationDate());
        assertEquals(boardModels.get(0).getColumns().get(0).getCards().get(1).getCompletedDate(), firstCardModel.getCompletedDate());
        assertEquals(1, secondCardModel.getStoryPoint());

        ColumnModel secondColumnModel = firstBoardModel.getColumns().get(1);

        assertEquals("Developing", secondColumnModel.getName());
        assertEquals(Constants.ColumnRole.WORK_IN_PROGRESS, secondColumnModel.getRole());

        CardModel thirdCardModel = secondColumnModel.getCards().get(0);

        assertEquals("New Card", thirdCardModel.getTitle());
        assertEquals("final test", thirdCardModel.getDescription());
    }


}
