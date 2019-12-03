package controllers;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXButton;
import callbacks.BoardNamePopupCallBack;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import ui.BoardNamePopup;
import ui.KanbanBoard;
import model.KanbanModel;
import model.BoardModel;
import model.ColumnModel;
import utils.ComponentMaker;
import java.util.List;
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
    @FXML
    private JFXButton fileMenuButton;

    private int colCounter = 0;
    private int rowCounter = 0;
    private JFXPopup fileMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //KanbanModel.instance(); // create the model for the application
        boardGrid.maxWidthProperty().bind(rootPane.widthProperty().multiply(4).divide(5));
        boardGrid.maxHeightProperty().bind(rootPane.heightProperty().multiply(4).divide(5));

        fileMenu = ComponentMaker.makeFileMenu();
    }

    @FXML
    public void goToHomeScreen() {
        if (rootPane.getCenter() instanceof BorderPane) {
            rootPane.setCenter(boardGrid);
        }
    }

    public void askToNameBoard() {
        BoardNamePopup dialog = new BoardNamePopup(new BoardNamePopupCallBack() {
            @Override
            public void onStart(StackPane stackPane) {
                rootPane.setCenter(stackPane);
            }

            @Override
            public void onValidName(String boardTitle) {
                makeNewBoard(new BoardModel(boardTitle), new Label(boardTitle));
                rootPane.setCenter(boardGrid);
            }

            @Override
            public void onCancel() {
                rootPane.setCenter(boardGrid);
            }
        }, rootPane.getCenter());
        dialog.show();
    }

    public void makeNewBoard(BoardModel boardModel, Label boardLabel)
    {
        try {
            KanbanBoard board = new KanbanBoard();

            if (colCounter == 4) {
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

            newBoardCard.setOnMouseClicked(event -> rootPane.setCenter(board));

            boardGrid.add(newBoardCard, colCounter, rowCounter);
            colCounter++;
        } catch (IOException e) {
            System.out.println("The board could not be created.");
            e.printStackTrace();
        }
    }

    private void createColumns(BoardModel boardModel, KanbanBoard board)
    {
        List<ColumnModel> columns = boardModel.getColumns();
        for(ColumnModel column : columns)
            board.getController().makeNewColumn(column);
    }

    @FXML
    public void openFileMenu()
    {
        fileMenu.show(fileMenuButton, JFXPopup.PopupVPosition.TOP,
                      JFXPopup.PopupHPosition.LEFT, 0, fileMenuButton.getHeight());
    }
}
