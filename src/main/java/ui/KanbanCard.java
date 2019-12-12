package ui;

import controllers.CardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


import java.io.IOException;



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

    public CardController getController() {
        return cardController;
    }

    public KanbanColumn getColumn() {
        return parent;
    }




}
