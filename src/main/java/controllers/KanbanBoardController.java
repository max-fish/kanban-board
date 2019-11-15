package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ui.KanbanColumn;

import javafx.scene.control.*;
import java.io.IOException;


public class KanbanBoardController {
    @FXML
    private ScrollPane columnsScrollPane;

    @FXML
    private HBox columns;

    private boolean hasColumn = false;

    public void deleteColumn(){
        columns.getChildren().remove(0);
    }

    @FXML
    public void goToHomeScreen(){
        //TODO implement action to home screen
    }

    @FXML
    public void makeNewColumn() throws IOException
    {
        if(!hasColumn){
            columnsScrollPane.setVisible(true);
        }
        KanbanColumn column = new KanbanColumn();
        BorderPane toInsert = column.create(this);
        columns.getChildren().add(toInsert);
        columns.setMargin(toInsert, new Insets(10));
    }
}
