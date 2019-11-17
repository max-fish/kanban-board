package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.*;
import ui.KanbanBoard;
import ui.KanbanColumn;
import utils.ComponentMaker;
import model.Board;

import java.io.IOException;


public class KanbanBoardController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField boardTitle;
    @FXML
    private ScrollPane columnsScrollPane;
    @FXML
    private HBox columns;

    private boolean hasColumn = false;
    private Board board;

    @FXML
    public void makeNewColumn() throws IOException
    {
        if(!hasColumn){
            columnsScrollPane.setVisible(true);
        }
        KanbanColumn toInsert = new KanbanColumn((KanbanBoard) rootPane);
        columns.getChildren().add(toInsert);
        HBox.setMargin(toInsert, new Insets(10));
        hasColumn = true;
    }

    public void changeTitle(String title){
        boardTitle.setText(title);
    }

    void deleteColumn(KanbanColumn column){
        columns.getChildren().remove(column);
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }
}
