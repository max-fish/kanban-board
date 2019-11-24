package ui;

import controllers.CardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


import java.io.IOException;



public class KanbanCard extends BorderPane {
    private  KanbanColumn parent;
    private CardController cardController;

    public KanbanCard(KanbanColumn parent) throws IOException{
        this.parent = parent;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/kanban_card_ui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        cardController = fxmlLoader.getController();
    }

    public CardController getController() {
        return cardController;
    }

    public KanbanColumn getColumn() {
        return parent;
    }




}
