package controllers;

import ui.KanbanBoard;
import model.KanbanModel;
import model.Board;
import model.Column;
import utils.ComponentMaker;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

import java.io.IOException;


public class HomePageController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private GridPane boardGrid;
    private int colCounter = 0;
    private int rowCounter = 0;

    public void initConfig(){
        boardGrid.maxWidthProperty().bind(rootPane.widthProperty().multiply(4).divide(5));
        boardGrid.maxHeightProperty().bind(rootPane.heightProperty().multiply(4).divide(5));
    }

    @FXML
    public void goToHomeScreen() {
        if(rootPane.getCenter() instanceof BorderPane){
            rootPane.setCenter(boardGrid);
        }
    }

    @FXML
    public void makeNewBoard() {
        Label boardLabel = new Label("New Board");
        Board boardModel = new Board(boardLabel.getText());

        makeNewBoard(boardModel, boardLabel);
    }

    public void makeNewBoard(Board boardModel, Label boardLabel)
    {
        try {
            KanbanBoard board = new KanbanBoard();

            if(colCounter == 4){
                rowCounter++;
                colCounter = 0;
            }
            StackPane newBoardCard = ComponentMaker.makeBoardCard(boardLabel);

            KanbanModel.instance().addBoard(boardModel);

            board.getController().setBoard(boardModel);
            board.getController().changeTitle(boardLabel.getText());

            board.getController().setHomePageLabel(boardLabel);
            board.getController().setTitleChangeListener();

            if(boardModel.hasColumns())
                createColumns(boardModel, board);

            newBoardCard.setOnMouseClicked(event -> { rootPane.setCenter(board); });

            boardGrid.add(newBoardCard, colCounter, rowCounter);
            colCounter++;
        } catch (IOException e) {
            System.out.println("The board could not be created.");
            e.printStackTrace();
        }
    }

    private void createColumns(Board boardModel, KanbanBoard board)
    {
        List<Column> columns = boardModel.getColumns();
        for(Column column : columns)
            board.getController().makeNewColumn(column);
    }

    @FXML
    public void loadJSON()
    {
        KanbanModel.instance().loadJSON();
    }
}
