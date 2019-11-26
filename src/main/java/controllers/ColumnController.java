package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CardModel;
import model.ColumnModel;
import ui.KanbanCard;
import ui.KanbanColumn;
import utils.AnimationMaker;
import utils.DragAndDrop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ColumnController implements Initializable {
    @FXML
    private VBox cards;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private ColumnModel columnModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DragAndDrop dragAnimation = new DragAndDrop();
        KanbanColumn kanbanColumn = (KanbanColumn) rootPane;

        dragAnimation.setDragAnimation(kanbanColumn,  (HBox) ((ScrollPane) kanbanColumn.getBoard().getCenter()).getContent());
    }

    @FXML
    public void makeNewCard() throws IOException {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        cards.getChildren().add(newCard);

        CardModel newCardModel = new CardModel(columnModel);

        columnModel.addCard(newCardModel);

        newCard.getController().setCard(newCardModel);
    }

    @FXML
    public void deleteColumn() {
        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().askToDeleteColumn(columnToDelete, () -> {
            columnModel.getBoard().deleteColumn(columnModel);
            columnModel = null;
        });
    }

    void setColumnModel(ColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    void setNameChangeListener() {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> columnModel.setName(newValue));
    }

    void setRoleChangeListener() {
        columnRole.textProperty().addListener((observable, oldValue, newValue) -> columnModel.setRole(newValue));
    }

    void deleteCard(KanbanCard kanbanCard) {
        cards.getChildren().remove(kanbanCard);
    }
}
