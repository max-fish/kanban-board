package ui;

import controllers.CardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

/**
 * This class inflates a {@link BorderPane} wiht a custom fxml layout, yielding a KanbanCard.
 */
public class KanbanCard extends BorderPane {
    private  KanbanColumn parent;
    private CardController cardController;

    public KanbanCard(KanbanColumn parent){
        this.parent = parent;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/kanban_card_ui.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cardController = fxmlLoader.getController();
    }

    /**
     * To make this KanbanCard mutable in response to user input
     * @return {@link CardController}
     */
    public CardController getController() {
        return cardController;
    }

    /**
     * To make it possible to navigate from one Kanban component to another
     * @return the {@link KanbanColumn} container the encloses this KanbanCard.
     */
    public KanbanColumn getColumn() {
        return parent;
    }




}
