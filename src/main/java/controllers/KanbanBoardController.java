package controllers;

import callbacks.DeleteColumnDataCallback;
import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import data.model.BoardModel;
import data.model.CardModel;
import data.model.ColumnModel;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import ui.DeleteConfirmationPopup;
import data.model.StatisticsModel;
import ui.KanbanBoard;
import ui.KanbanColumn;
import ui.Statistics;
import utils.AnimationMaker;
import utils.ComponentMaker;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private String homePageTitle;
    private JFXButton addButton;
    @FXML
    private JFXButton statisticsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        statisticsButton.setOnMouseClicked(event -> getStatistics());

        addButton = ComponentMaker.makeAddButton();
        addButton.setOnMouseClicked(event -> makeNewColumn());

        columns.getChildren().add(addButton);
    }

    @FXML
    public void makeNewColumn()
    {
        ColumnModel newColumnModel = new ColumnModel(board);

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

    private void getStatistics(){
        //add info ofr creating sttistics as parameters and keep record on fields
        Statistics statPopup = new Statistics();
        StatisticsModel model = new StatisticsModel(board);
        statPopup.getController().setStatisticsModel(model);
        statPopup.getController().displayStats();
        statPopup.show(statisticsButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void setTitleChangeListener() {
        boardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            board.setName(newValue);
            homePageTitle = newValue;
        });
    }

    public void setBoard(BoardModel board)
    {
        this.board = board;
    }

    public void setHomePageLabel(String title) {
        homePageTitle = title;
    }

    public BoardModel getBoardModel()
    {
        return board;
    }

}
