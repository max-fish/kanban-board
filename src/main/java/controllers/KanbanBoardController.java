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
import model.Column;

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
    private Label homePageLabel;

    @FXML
    public void makeNewColumn()
    {
        Column newColumn = new Column();

        makeNewColumn(newColumn);
    }

    public void makeNewColumn(Column newColumn)
    {
        try
        {
            if(!hasColumn){
                columnsScrollPane.setVisible(true);
            }
            KanbanColumn toInsert = new KanbanColumn((KanbanBoard)rootPane);
            columns.getChildren().add(toInsert);
            HBox.setMargin(toInsert, new Insets(10));
            hasColumn = true;

            if(!board.contains(newColumn))
                board.addColumn(newColumn);

            toInsert.getController().setColumn(newColumn);
            toInsert.getController().setColumnName(newColumn.getName());
            toInsert.getController().setColumnRole(newColumn.getRole());
            toInsert.getController().setNameChangeListener();
            toInsert.getController().setRoleChangeListener();

            // TODO: check if the column has cards
            // if so, then create the GUI elements for them
        }
        catch(IOException exception)
        {
            System.out.println("The column could not be created.");
            exception.printStackTrace();
        }
    }

    public void changeTitle(String title){
        boardTitle.setText(title);
    }

    public void setTitleChangeListener()
    {
        boardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            board.setName(newValue);
            homePageLabel.setText(newValue);
        });
    }

    public void deleteColumn(KanbanColumn column){
        columns.getChildren().remove(column);
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public void setHomePageLabel(Label label)
    {
        homePageLabel = label;
    }

    public Board getBoardModel()
    {
        return board;
    }
}
