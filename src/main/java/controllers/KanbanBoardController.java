package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ui.KanbanColumn;

import javafx.scene.control.*;
import java.io.IOException;


public class KanbanBoardController {
    public BorderPane rootPane;
    public JFXTextField boardTitle;
    @FXML
    private ScrollPane columnsScrollPane;

    @FXML
    private HBox columns;



    private boolean hasColumn = false;
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
        BorderPane toInsert = column.create();
        columns.getChildren().add(toInsert);
        HBox.setMargin(toInsert, new Insets(10));
        hasColumn = true;
    }

    public void requestFocus(MouseEvent mouseEvent) {

    }
}
