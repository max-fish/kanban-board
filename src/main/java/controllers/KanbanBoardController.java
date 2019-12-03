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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import ui.DeleteConfirmationPopup;
import ui.KanbanBoard;
import ui.KanbanColumn;
import utils.AnimationMaker;
import utils.ComponentMaker;
import model.BoardModel;
import model.CardModel;
import model.ColumnModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;


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
            makeNewColumn();
        });
        columns.getChildren().add(addButton);
    }

    @FXML
    public void makeNewColumn()
    {
        ColumnModel newColumnModel = new ColumnModel(/*board*/);

        makeNewColumn(newColumnModel);
    }

    public void makeNewColumn(ColumnModel newColumnModel)
    {
        try
        {
            KanbanColumn toInsert = new KanbanColumn((KanbanBoard)rootPane);

            TranslateTransition slideIn = AnimationMaker.makeAddColumnSlideInAnimation(toInsert);
            TranslateTransition addButtonSlideIn = AnimationMaker.makeAddColumnSlideInAnimation(addButton);

            columns.getChildren().set(columns.getChildren().size() - 1, toInsert);
            columns.getChildren().add(addButton);

            AnimationMaker.playAnimations(slideIn, addButtonSlideIn);

            HBox.setMargin(toInsert, new Insets(10));

            if(!board.contains(newColumnModel))
                board.addColumn(newColumnModel);

            toInsert.getController().setColumnModel(newColumnModel);
            toInsert.getController().setColumnName(newColumnModel.getName());
            toInsert.getController().setColumnRole(newColumnModel.getRole());
            toInsert.getController().setNameChangeListener();
            toInsert.getController().setRoleChangeListener();

            if(newColumnModel.hasCards())
                createCards(newColumnModel, toInsert);
        }
        catch(IOException exception)
        {
            System.out.println("The column could not be created.");
            exception.printStackTrace();
        }
    }

    private void createCards(ColumnModel columnModel, KanbanColumn column)
    {
        List<CardModel> cards = columnModel.getCards();
        for(CardModel card : cards)
            column.getController().makeNewCard(card);
    }

    void changeTitle(String title) {
        boardTitle.setText(title);
    }

    void askToDeleteColumn(KanbanColumn kanbanColumn, DeleteColumnDataCallback callback) {
        KanbanBoard board = (KanbanBoard) rootPane;
        BorderPane homePane = board.getHomePage();
        DeleteConfirmationPopup deleteConfirmationPopup = new DeleteConfirmationPopup(new DeleteColumnPopupCallback() {
            @Override
            public void onStart(StackPane stackPane) {
                homePane.setCenter(stackPane);
            }

            @Override
            public void onDelete() {
                callback.onDelete();
                homePane.setCenter(board);
                deleteColumn(kanbanColumn);
            }

            @Override
            public void onCancel() {
                homePane.setCenter(board);
            }
        }, homePane.getCenter());

        deleteConfirmationPopup.show();
    }

    private void deleteColumn(KanbanColumn column) {
        ParallelTransition parallelTransition = AnimationMaker.makeDeleteColumnParallelAnimation(columns, column);
        if (parallelTransition != null) {
            parallelTransition.play();
        }
        columns.getChildren().remove(column);
    }

    public void setTitleChangeListener() {
        boardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            board.setName(newValue);
            homePageLabel.setText(newValue);
        });
    }

    public void setBoard(BoardModel board)
    {
        this.board = board;
    }

    public void setHomePageLabel(Label label) {
        homePageLabel = label;
    }

    public BoardModel getBoardModel()
    {
        return board;
    }
}
