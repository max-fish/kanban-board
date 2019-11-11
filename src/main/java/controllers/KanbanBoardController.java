package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import ui.KanbanColumn;

import java.io.IOException;


public class KanbanBoardController {
    @FXML
    private HBox columns;


    @FXML
    public void goToHomeScreen(){
        //TODO implement action to home screen
    }

    @FXML
    public void makeNewColumn() throws IOException
    {
        KanbanColumn column = new KanbanColumn();
        columns.getChildren().add(column.create());

    }
}
