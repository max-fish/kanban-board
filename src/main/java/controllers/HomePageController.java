package controllers;

import callbacks.BoardNamePopupCallBack;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import ui.KanbanBoard;
import model.KanbanModel;
import model.BoardModel;
import utils.ComponentMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomePageController implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private GridPane boardGrid;
    private int colCounter = 0;
    private int rowCounter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        KanbanModel.instance(); // create the model for the application
        boardGrid.maxWidthProperty().bind(rootPane.widthProperty().multiply(4).divide(5));
        boardGrid.maxHeightProperty().bind(rootPane.heightProperty().multiply(4).divide(5));
    }

    @FXML
    public void goToHomeScreen() {
        if (rootPane.getCenter() instanceof BorderPane) {
            rootPane.setCenter(boardGrid);
        }
    }

    public void askToNameBoard() {
        ComponentMaker.makeBoardNamePopup(new BoardNamePopupCallBack() {
            @Override
            public void onStart(StackPane stackPane) {
                rootPane.setCenter(stackPane);
            }

            @Override
            public void onValidName(String boardTitle) {
                makeNewBoard(boardTitle);
                rootPane.setCenter(boardGrid);
            }

            @Override
            public void onCancel() {
                rootPane.setCenter(boardGrid);
            }
        }, rootPane.getCenter());
    }

    @FXML
    private void makeNewBoard(String title) {
        try {
            KanbanBoard board = new KanbanBoard();

            if (colCounter == 4) {
                rowCounter++;
                colCounter = 0;
            }
            Label boardLabel = new Label(title);
            StackPane newBoardCard = ComponentMaker.makeBoardCard(boardLabel);

            BoardModel boardModel = new BoardModel(boardLabel.getText());
            KanbanModel.instance().addBoard(boardModel);

            board.getController().setBoard(boardModel);
            board.getController().changeTitle(boardLabel.getText());

            board.getController().setHomePageLabel(boardLabel);

            board.getController().setHomePageLabel(boardLabel);
            board.getController().setTitleChangeListener();

            newBoardCard.setOnMouseClicked(event -> rootPane.setCenter(board));

            boardGrid.add(newBoardCard, colCounter, rowCounter);
            colCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
