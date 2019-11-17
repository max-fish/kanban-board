package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.*;
import ui.KanbanBoard;
import ui.KanbanColumn;
import utils.ComponentMaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class KanbanBoardController implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField boardTitle;
    @FXML
    private ScrollPane columnsScrollPane;
    @FXML
    private HBox columns;

    private boolean hasColumn = false;

    private JFXButton addButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton = ComponentMaker.makeAddButton();
        addButton.setOnMouseClicked(event -> {
            try {
                makeNewColumn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        columns.getChildren().add(addButton);
    }

    @FXML
    public void makeNewColumn() throws IOException {
        if(!hasColumn){
            columnsScrollPane.setVisible(true);
        }
        KanbanColumn toInsert = new KanbanColumn((KanbanBoard) rootPane);
        columns.getChildren().set(columns.getChildren().size() - 1, toInsert);
        columns.getChildren().add(addButton);
        HBox.setMargin(toInsert, new Insets(10));
        hasColumn = true;
    }

    public void changeTitle(String title){
        boardTitle.setText(title);
    }

    void deleteColumn(KanbanColumn column){
        columns.getChildren().remove(column);
    }
}
