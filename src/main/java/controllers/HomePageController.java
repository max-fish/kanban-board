package controllers;

import ui.KanbanBoard;
import model.KanbanModel;
import model.Board;
import utils.ComponentMaker;
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
        try {
            KanbanBoard board = new KanbanBoard();

            if(colCounter == 4){
                rowCounter++;
                colCounter = 0;
            }
            Label boardLabel = new Label("New Board");
            StackPane newBoardCard = ComponentMaker.makeBoardCard(boardLabel);

            Board boardModel = new Board(boardLabel.getText());
            KanbanModel.instance().addBoard(boardModel);

            board.getController().setBoard(boardModel);
            board.getController().changeTitle(boardLabel.getText());

            board.getController().setHomePageLabel(boardLabel);
            board.getController().setTitleChangeListener();

            newBoardCard.setOnMouseClicked(event -> { rootPane.setCenter(board); });

            boardGrid.add(newBoardCard, colCounter, rowCounter);
            colCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
