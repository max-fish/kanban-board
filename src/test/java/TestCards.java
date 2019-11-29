import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.BoardModel;
import model.CardModel;
import model.ColumnModel;

public class TestCards
{

    private ColumnModel col1;
    private CardModel card1;

    @Before
    public void setup() {
        BoardModel board = new BoardModel("board");

        col1 = new ColumnModel(board, "col1", "");
        card1 = new CardModel("card1", "", "", col1);

        CardModel card2 = new CardModel("card2", "", "", col1);
        CardModel card3 = new CardModel("card3", "", "", col1);

        col1.addCard(card1);
        col1.addCard(card2);
        col1.addCard(card3);
    }

    @Test
    public void TestAddCardShouldIncreaseArraySize() {
        int size = col1.getCardModels().size();
        CardModel card4 = new CardModel("card4", "", "", col1);
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