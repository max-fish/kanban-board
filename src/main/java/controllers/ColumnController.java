package controllers;

import com.jfoenix.controls.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import data.model.CardModel;
import data.model.ColumnModel;
import ui.KanbanCard;
import ui.KanbanColumn;
import utils.ComponentMaker;
import utils.DragAndDrop;

import java.net.URL;
import java.util.ResourceBundle;

public class ColumnController implements Initializable {
    @FXML
    private VBox cards;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXButton columnMenuButton;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXButton columnRole;
    @FXML
    private JFXButton dragButton;
    @FXML
    private JFXComboBox<Label> wipLimitDropDown;

    private JFXPopup columnMenu;

    private JFXPopup columnRoleOptions;

    private ColumnModel columnModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnMenu = ComponentMaker.makeColumnMenu();
        JFXButton addCardButton = (JFXButton) ((VBox) columnMenu.getPopupContent()).getChildren().get(0);
        addCardButton.setOnAction(event -> makeNewCard());

        JFXButton deleteColumnButton = (JFXButton) ((VBox) columnMenu.getPopupContent()).getChildren().get(1);
        deleteColumnButton.setOnAction(event -> deleteColumn());

        columnRoleOptions = ComponentMaker.makeColumnRoleDropDown();

        for (Node option : ((VBox) columnRoleOptions.getPopupContent()).getChildren()) {
            JFXButton optionButton = (JFXButton) option;
            optionButton.setOnAction(event -> setRole(optionButton.getText()));
        }

        wipLimitDropDown.setOnAction(event -> columnModel.setWipLimit(Integer.parseInt(wipLimitDropDown.getValue().getText().substring(0,1))));

        DragAndDrop dragAnimation = new DragAndDrop();
        KanbanColumn kanbanColumn = (KanbanColumn) rootPane;
        dragAnimation.setDragAnimation(kanbanColumn, dragButton, (HBox) ((ScrollPane) kanbanColumn.getBoard().getCenter()).getContent());

    }

    public void makeNewCard() {
        if (columnModel.getCurrentWip() >= columnModel.getWipLimit() && columnModel.getWipLimit() != 0) {
            ComponentMaker.makeWipLimitSnackbar(((KanbanColumn) rootPane).getBoard());
        } else {
            CardModel newCardModel = new CardModel(/*columnModel*/);
            makeNewCard(newCardModel);
        }
    }

    public void makeNewCard(CardModel newCardModel) {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        cards.getChildren().add(newCard);

        if (!columnModel.contains(newCardModel))
            columnModel.addCard(newCardModel);

        newCard.getController().fillWithData(newCardModel);
        columnModel.setCurrentWip(columnModel.getCurrentWip() + 1);
    }

    public void deleteColumn() {
        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().askToDeleteColumn(columnToDelete, () -> {
            if (columnMenu.isShowing())
                columnMenu.hide();

            columnToDelete.getBoard().getController().getBoardModel().deleteColumn(columnModel);
            columnModel = null;
        });
    }

    @FXML
    public void openColumnMenu() {
        columnMenu.show(columnMenuButton, JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.LEFT, 0, columnMenuButton.getHeight());
    }

    public void setColumnModel(ColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    public void setColumnName(String name) {
        columnName.setText(name);
    }

    public void setColumnRole(String role) {
        columnRole.setText(role);
    }

    public void setNameChangeListener() {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> columnModel.setName(newValue));
    }

    public void setRole(String role) {
        columnRole.setText(role);
        columnModel.setRole(role);
    }

    public void deleteCard(KanbanCard kanbanCard) {
        cards.getChildren().remove(kanbanCard);
    }

    @FXML
    public void setColumnRoleDropDown() {
        columnRoleOptions.show(columnRole, JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.LEFT, 0, columnRole.getHeight());
    }

    public ColumnModel getColumnModel() {
        return columnModel;
    }

}
