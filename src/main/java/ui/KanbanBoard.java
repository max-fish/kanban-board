package ui;

import controllers.KanbanBoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class KanbanBoard extends BorderPane {
    private KanbanBoardController kanbanBoardController;

    public KanbanBoard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/kanban_board_ui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        kanbanBoardController = fxmlLoader.getController();
    }

    public KanbanBoardController getController() {
        return kanbanBoardController;
    }

    public BorderPane getHomePage(){
        return (BorderPane) getParent();
    }
}
