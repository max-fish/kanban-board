package controllers;

import ComponentUtils.ComponentMaker;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class HomePageController {
    public BorderPane rootPane;
    public GridPane boardGrid;
    private int colCounter = 0;
    private int rowCounter = 0;

    public void initConfig(){
        boardGrid.maxWidthProperty().bind(rootPane.widthProperty().multiply(4).divide(5));
        boardGrid.maxHeightProperty().bind(rootPane.heightProperty().multiply(4).divide(5));
    }

    @FXML
    public void goToHomeScreen() {

    }

    @FXML
    public void makeNewBoard() {
        if(colCounter == 4){
            rowCounter++;
            colCounter = 0;
        }
        boardGrid.add(ComponentMaker.makeBoardCard("board name"), colCounter, rowCounter);
        colCounter++;
    }
}
