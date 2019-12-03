package controllers;

import callbacks.DeleteColumnDataCallback;
import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import model.ColumnModel;
import model.StatisticsModel;
import org.graalvm.compiler.phases.graph.StatelessPostOrderNodeIterator;
import ui.KanbanBoard;
import ui.KanbanColumn;
import ui.Statistics;
import utils.AnimationMaker;
import utils.ComponentMaker;
import model.BoardModel;
import model.CardModel;
import java.awt.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;


public class KanbanBoardController implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private AnchorPane topBoard;
    @FXML
    private JFXTextField boardTitle;
    @FXML
    private HBox columns;

    private BoardModel board;

    private Label homePageLabel;

    private JFXButton addButton;
    private JFXButton statisticsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        statisticsButton = ComponentMaker.makeStatisticsButton();
        topBoard.setRightAnchor(statisticsButton,10.0);
        topBoard.setTopAnchor(statisticsButton,10.0);
        topBoard.getChildren().add(statisticsButton);

        statisticsButton.setOnMouseClicked(event -> getStatistics());

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

    void askToDeleteColumn(KanbanColumn kanbanColumn, DeleteColumnDataCallback callback) {
        ComponentMaker.makeDeleteConfirmationPopup(new DeleteColumnPopupCallback() {
            @Override
            public void onStart(StackPane stackPane) {
                rootPane.setCenter(stackPane);
            }

            @Override
            public void onDelete() {
                callback.onDelete();
                rootPane.setCenter(columns);
                deleteColumn(kanbanColumn);
            }

            @Override
            public void onCancel() {
                rootPane.setCenter(columns);
            }
        }, rootPane.getCenter());
    }

    private void deleteColumn(KanbanColumn column) {
        ParallelTransition parallelTransition = AnimationMaker.makeDeleteColumnParallelAnimation(columns, column);
        columns.getChildren().remove(column);

        if (parallelTransition != null) {
            parallelTransition.play();
        }
    }

    public void getStatistics(){
        //add info ofr creating sttistics as parameters and keep record on fields
        try {
            Statistics toShow = new Statistics();
            StatisticsModel model = new StatisticsModel(board);
            toShow.getController().setStatisticsModel(model);
            toShow.getController().displayStats(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setTitleChangeListener() {
        boardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            board.setName(newValue);
            homePageLabel.setText(newValue);
        });
    }

    void setBoard(BoardModel board) {
        this.board = board;
    }

    void setHomePageLabel(Label label) {
        homePageLabel = label;
    }

}
