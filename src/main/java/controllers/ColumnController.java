package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;


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
    public void deleteColumn(MouseEvent ev){
        //The method depends on the fact that the scene diagram isn't changed
        kanbanBoardController.deleteColumn( (BorderPane) ((Node) ev.getSource()).getParent().getParent());
    }
}