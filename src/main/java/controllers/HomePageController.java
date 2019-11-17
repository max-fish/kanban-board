package controllers;

import javafx.fxml.Initializable;
import ui.KanbanBoard;
import utils.ComponentMaker;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
        if(colCounter == 4){
            rowCounter++;
            colCounter = 0;
        }
        StackPane newBoardCard = ComponentMaker.makeBoardCard("board name");

        newBoardCard.setOnMouseClicked(event -> {
            try {
                rootPane.setCenter(new KanbanBoard());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        boardGrid.add(newBoardCard, colCounter, rowCounter);
        colCounter++;
    }
}
