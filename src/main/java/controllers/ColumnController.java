package controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import utils.GUIMaker;
import utils.Constants;
import utils.DragAndDrop;

import java.net.URL;
import java.util.Collections;
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
        columnMenu = GUIMaker.makeColumnMenu();

        JFXButton addCardButton = (JFXButton) ((VBox) columnMenu.getPopupContent()).getChildren().get(0);
        addCardButton.setOnAction(event -> {
            makeNewCard();
            columnMenu.hide();
        });

        JFXButton deleteColumnButton = (JFXButton) ((VBox) columnMenu.getPopupContent()).getChildren().get(1);
        deleteColumnButton.setOnAction(event -> {
            deleteColumn();
            columnMenu.hide();
        });

        columnRoleOptions = GUIMaker.makeColumnRoleDropDown();

        for (Node option : ((VBox) columnRoleOptions.getPopupContent()).getChildren()) {
            JFXButton optionButton = (JFXButton) option;
            optionButton.setOnAction(event -> {
                setRole(Constants.ColumnRole.getEnumFromRoleString(optionButton.getText()));
                columnRoleOptions.hide();
            });
        }

        columnName.textProperty().addListener((observable, oldValue, newValue) -> columnModel.setName(newValue));

        wipLimitDropDown.setOnAction(event -> {
            try {
                columnModel.setWipLimit(Integer.parseInt(wipLimitDropDown.getValue().getText()));
            } catch (NumberFormatException e) {
                columnModel.setWipLimit(0);
            }
        });

        DragAndDrop dragAnimation = new DragAndDrop();
        KanbanColumn kanbanColumn = (KanbanColumn) rootPane;
        dragAnimation.setDragAnimation(kanbanColumn, dragButton, kanbanColumn.getBoard());
    }

    private void makeNewCard() {
        if (columnModel.getCurrentWip() >= columnModel.getWipLimit() && columnModel.getWipLimit() != 0) {
            GUIMaker.makeWipLimitSnackbar(((KanbanColumn) rootPane).getBoard());
        } else makeNewCard(new CardModel());
    }

    public void makeNewCard(CardModel newCardModel) {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        newCard.getController().fillWithData(newCardModel);
        cards.getChildren().add(newCard);

        if (!columnModel.contains(newCardModel))
            columnModel.addCard(newCardModel);
        columnModel.setCurrentWip(columnModel.getCurrentWip() + 1);
    }

    public void makeNewCard(int index, CardModel newCardModel){
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        newCard.getController().fillWithData(newCardModel);
        cards.getChildren().add(index, newCard);

        if (!columnModel.contains(newCardModel))
            columnModel.addCard(newCardModel);
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

    public void fillWithData(ColumnModel columnModel) {
        this.columnModel = columnModel;
        columnName.setText(columnModel.getName());
        columnRole.setText(columnModel.getRole().roleString);
    }

    public void setRole(Constants.ColumnRole role) {

        columnRole.setText(role.roleString);
        columnModel.setRole(role);
    }

    public void deleteCard(KanbanCard kanbanCard) {
        cards.getChildren().remove(kanbanCard);
        columnModel.deleteCard(kanbanCard.getController().getData());
    }

    @FXML
    public void setColumnRoleDropDown() {
        columnRoleOptions.show(columnRole, JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.LEFT, 0, columnRole.getHeight());
    }

    public ColumnModel getColumnModel() {
        return columnModel;
    }

    public void swapCards(int idx1, int idx2) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(cards.getChildren());
        Collections.swap(workingCollection, idx1, idx2);
        cards.getChildren().setAll(workingCollection);
        Collections.swap(columnModel.getCards(), idx1, idx2);
    }

}
