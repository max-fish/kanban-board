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

    @Before
    public void init() {
        boardModels = new ArrayList<>();

        BoardModel boardModel = new BoardModel("test");

        ColumnModel columnModel = new ColumnModel();
        columnModel.setName("TODO");
        columnModel.setRole(Constants.ColumnRole.BACKLOG);
        columnModel.setCurrentWip(32);


        CardModel cardModel = new CardModel("test");
        cardModel.setDescription("this is a test");

        CardModel cardModel1 = new CardModel("test1");
        cardModel1.setDescription("this is a test1");


        columnModel.addCard(cardModel);
        columnModel.addCard(cardModel1);

        boardModel.addColumn(columnModel);

        ColumnModel columnModel1 = new ColumnModel();
        columnModel1.setName("Developing");
        columnModel1.setRole(Constants.ColumnRole.BACKLOG);

        CardModel cardModel2 = new CardModel("test2");

        columnModel1.addCard(cardModel2);

        boardModel.addColumn(columnModel1);

        boardModels.add(boardModel);
    }

    @Test
    public void TestToFromJSON() {
        String json = JSONParser.toJson(boardModels);
        List<BoardModel> retrievedBoardModels = JSONParser.fromJson(json);
        BoardModel firstBoardModel = retrievedBoardModels.get(0);

        assertEquals("test", firstBoardModel.getName());

        ColumnModel firstColumnModel = firstBoardModel.getColumns().get(0);

        assertEquals("TODO", firstColumnModel.getName());
        assertEquals(Constants.ColumnRole.BACKLOG, firstColumnModel.getRole());
        assertEquals(32, firstColumnModel.getCurrentWip());

        CardModel firstCardModel = firstColumnModel.getCards().get(0);

        assertEquals("test", firstCardModel.getTitle());
        assertEquals(boardModels.get(0).getColumns().get(0).getCards().get(0).getCompletedDate(), firstCardModel.getCompletedDate());
        assertEquals("this is a test", firstCardModel.getDescription());
        assertEquals(boardModels.get(0).getColumns().get(0).getCards().get(0).getCreationDate(),firstCardModel.getCreationDate());
        assertEquals(1, firstCardModel.getStoryPoint());
    }


}
