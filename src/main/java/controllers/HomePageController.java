package controllers;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXButton;
import callbacks.BoardNamePopupCallBack;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import ui.BoardNamePopup;
import ui.KanbanBoard;
import data.db.KanbanModel;
import data.model.BoardModel;
import data.model.ColumnModel;
import utils.GUIMaker;
import java.util.List;
import javafx.fxml.FXML;

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
        fileMenu = GUIMaker.makeFileMenu();
        JFXButton importButton = (JFXButton) ((VBox) fileMenu.getPopupContent()).getChildren().get(0);
        importButton.setOnAction(event -> KanbanModel.instance().loadJSON());
        JFXButton exportButton = (JFXButton) ((VBox) fileMenu.getPopupContent()).getChildren().get(1);
        exportButton.setOnAction(event -> KanbanModel.instance().saveJSON());
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
                makeNewBoard(new BoardModel(boardTitle), boardTitle);
                rootPane.setCenter(boardGrid);
            }

            @Override
            public void onCancel() {
                rootPane.setCenter(boardGrid);
            }
        }, rootPane.getCenter());
        dialog.show();
    }

    public void makeNewBoard(BoardModel boardModel, String boardTitle)
    {
        try {
            KanbanBoard board = new KanbanBoard();

            if (colCounter == 4) {
                rowCounter++;
                colCounter = 0;
            }

            StackPane newBoardCard = GUIMaker.makeBoardCard(boardTitle);

            KanbanModel.instance().addBoard(boardModel);

            board.getController().fillWithData(boardModel);

            if(boardModel.hasColumns())
                createColumns(boardModel, board);

            boardModel.setGUI(board);

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
