package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.*;
import model.ColumnModel;
import ui.KanbanBoard;
import ui.KanbanColumn;
import utils.AnimationMaker;
import utils.ComponentMaker;
import model.BoardModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class KanbanBoardController implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField boardTitle;
    @FXML
    private HBox columns;

    private BoardModel board;
    private Label homePageLabel;

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
        addButton.setId("addColumn");
        columns.getChildren().add(addButton);
    }

    @FXML
    private void makeNewColumn() throws IOException {

        KanbanColumn toInsert = new KanbanColumn((KanbanBoard) rootPane);

        TranslateTransition slideIn = AnimationMaker.makeAddColumnSlideInAnimation(toInsert);
        TranslateTransition addButtonSlideIn = AnimationMaker.makeAddColumnSlideInAnimation(addButton);

        columns.getChildren().set(columns.getChildren().size() - 1, toInsert);
        columns.getChildren().add(addButton);

        AnimationMaker.playAnimations(slideIn, addButtonSlideIn);

        HBox.setMargin(toInsert, new Insets(10));

        ColumnModel newColumnModel = new ColumnModel(board);
        board.addColumn(newColumnModel);

        toInsert.getController().setColumnModel(newColumnModel);
        toInsert.getController().setNameChangeListener();
        toInsert.getController().setRoleChangeListener();
    }

    void changeTitle(String title) {
        boardTitle.setText(title);
    }

    void setTitleChangeListener() {
        boardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            board.setName(newValue);
            homePageLabel.setText(newValue);
        });
    }

    void deleteColumn(KanbanColumn column) {
        ParallelTransition parallelTransition = AnimationMaker.makeDeleteColumnParallelAnimation(columns, column);
        columns.getChildren().remove(column);

        if(parallelTransition != null) {
            parallelTransition.play();
        }
    }

    void setBoard(BoardModel board) {
        this.board = board;
    }

    void setHomePageLabel(Label label) {
        homePageLabel = label;
    }
}
