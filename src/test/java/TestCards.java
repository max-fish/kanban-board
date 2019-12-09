import data.model.BoardModel;
import data.model.CardModel;
import data.model.ColumnModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCards
{

    private ColumnModel col1;
    private CardModel card1;

    @Before
    public void setup() {
        BoardModel board = new BoardModel("board");

        col1 = new ColumnModel(board, "col1", "");
        card1 = new CardModel("card1", "desc1", 1);

        CardModel card2 = new CardModel("card2", "desc2", 2);
        CardModel card3 = new CardModel("card3", "desc3", 3);

        col1.addCard(card1);
        col1.addCard(card2);
        col1.addCard(card3);
    }

    @Test
    public void TestAddCardShouldIncreaseArraySize() {
        int size = col1.getCardModels().size();
        CardModel card4 = new CardModel("card4", "desc4", 4);
        col1.addCard(card4);
        assertEquals(col1.getCardModels().size(), size + 1);
        assert col1.getCardModels().contains(card4);
    }

    @Test
    public void TestDeleteCardShouldIncreaseArraySize() {
        int size = col1.getCardModels().size();
        col1.deleteCard(card1);
        assertEquals(col1.getCardModels().size(), size - 1);
        assert !col1.getCardModels().contains(card1);
    }
}