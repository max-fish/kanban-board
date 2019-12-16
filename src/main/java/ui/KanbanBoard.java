package ui;

import controllers.CardController;
import controllers.KanbanBoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * This class inflates a {@link BorderPane} with a custom fxml layout, making it a KanbanBaord.
 */
public class KanbanBoard extends BorderPane {
    private KanbanBoardController kanbanBoardController;

    public KanbanBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/kanban_board_ui.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kanbanBoardController = fxmlLoader.getController();
    }

    /**
     * To make this KanbanCard mutable in response to user input
     * @return {@link KanbanBoardController}
     */
    public KanbanBoardController getController() {
        return kanbanBoardController;
    }

    /**
     * To make it possible to navigate from one Kanban component to another
     * @return the {@link BorderPane} container the encloses this KanbanCard.
     */
    public BorderPane getHomePage(){
        return (BorderPane) getParent();
    }
}
