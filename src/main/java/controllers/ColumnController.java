package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class ColumnController {
    private KanbanBoardController kanbanBoardController;

    public void setController(KanbanBoardController controller){
        this.kanbanBoardController = controller;
    }

    @FXML
    public void makeNewCard(){
        //TODO implement making a new card
    }

    @FXML
    public void deleteColumn(){
        kanbanBoardController.deleteColumn();
    }
}