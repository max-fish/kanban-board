package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.CardModel;
import model.ColumnModel;
import ui.KanbanCard;
import ui.KanbanColumn;

import java.io.IOException;


public class ColumnController {
    @FXML
    private VBox cards;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private ColumnModel columnModel;

    @FXML
    public void makeNewCard() throws IOException {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        cards.getChildren().add(newCard);


        CardModel newCardModel = new CardModel(columnModel);

        columnModel.addCard(newCardModel);

        newCard.getController().setCard(newCardModel);
    }

    @FXML
    public void deleteColumn(MouseEvent ev){
        columnModel.getBoard().deleteColumn(columnModel);
        columnModel = null;

        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().deleteColumn(columnToDelete);
    }

    public void setColumnModel(ColumnModel columnModel)
    {
        this.columnModel = columnModel;
    }

    public void setNameChangeListener()
    {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setName(newValue);
        });
    }

    public void setRoleChangeListener()
    {
        columnRole.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setRole(newValue);
        });
    }

    public void deleteCard(KanbanCard kanbanCard) {
        cards.getChildren().remove(kanbanCard);
    }
}
