package controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import data.model.CardModel;
import data.model.ColumnModel;
import data.model.CardModel;
import data.log.ColumnDeleteChange;
import data.log.ColumnNameChange;
import data.log.ColumnRoleChange;
import data.log.CardMoveChange;
import ui.KanbanCard;
import ui.KanbanColumn;
import utils.GUIMaker;
import utils.Constants;
import utils.DragAndDrop;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * This class handles user input for a {@link KanbanColumn}
 */
public class ColumnController implements Initializable {
    @FXML
    private VBox cards;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXButton columnMenuButton;
    @FXML
    private StackPane nameContainer;
    @FXML
    private Label columnName;
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

        //sets onAction listener for every column role button in the popup
        for (Node option : ((VBox) columnRoleOptions.getPopupContent()).getChildren()) {
            JFXButton optionButton = (JFXButton) option;
            optionButton.setOnAction(event -> {
                setRole(Constants.ColumnRole.getEnumFromRoleString(optionButton.getText()));
                columnRoleOptions.hide();
            });
        }

        /*columnName.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setName(newValue);
            columnModel.getParent().getActivityLogModel().addChange(new ColumnNameChange(columnModel, oldValue, newValue));
          });*/

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
        } else makeNewCard(new CardModel(columnModel));
    }

    /**
     * Creates a new {@link KanbanCard} component and inflates with a {@link CardModel}
     * @param newCardModel - {@link CardModel}
     */
    public void makeNewCard(CardModel newCardModel) {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        newCard.getController().fillWithData(newCardModel);
        cards.getChildren().add(newCard);

        if (!columnModel.contains(newCardModel))
            columnModel.addCard(newCardModel);
        else
            System.out.println("The card was created: " + newCardModel.getTitle() + newCardModel.getID());

        columnModel.setCurrentWip(columnModel.getCurrentWip() + 1);
        System.out.println(columnModel.getCurrentWip());
        System.out.println(columnModel.getWipLimit());

        newCardModel.init(newCard, columnModel);
    }

    public void insertCard(CardModel newCardModel, int position) {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        newCard.getController().fillWithData(newCardModel);
        cards.getChildren().add(position, newCard);

        if (!columnModel.contains(newCardModel))
            columnModel.addCard(newCardModel);

        columnModel.setCurrentWip(columnModel.getCurrentWip() + 1);

        newCardModel.init(newCard, columnModel);
    }

    public void makeNewCard(int index, CardModel newCardModel) {
        KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
        newCard.getController().fillWithData(newCardModel);
        cards.getChildren().add(index, newCard);

        if (!columnModel.contains(newCardModel))
            columnModel.addCard(newCardModel);
        columnModel.setCurrentWip(columnModel.getCurrentWip() + 1);

    }

    /**
     * Asks a user to confirm deletion of this column
     */
    public void deleteColumn() {
        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().askToDeleteColumn(columnToDelete, () -> {
            //if confirmed, delete column
            if (columnMenu.isShowing())
                columnMenu.hide();

            int lastPosition = columnModel.getParent().getColumns().indexOf(columnModel);
            columnModel.getParent().getActivityLogModel().addChange(new ColumnDeleteChange(columnModel, lastPosition));

            columnToDelete.getBoard().getController().getBoardModel().deleteColumn(columnModel);
            columnModel = null;
        });
    }

    @FXML
    private void openColumnMenu() {
        columnMenu.show(columnMenuButton, JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.LEFT, 0, columnMenuButton.getHeight());
    }

    /**
     * Inflate this {@link KanbanColumn} component with a {@link ColumnModel}
     * @param columnModel - {@link ColumnModel}
     */
    public void fillWithData(ColumnModel columnModel) {
        this.columnModel = columnModel;
        columnName.setText(columnModel.getName());
        columnRole.setText(columnModel.getRole().roleString);
    }

    /**
     * Sets the role of a column
     * @param role - {@link utils.Constants.ColumnRole}
     */
    public void setRole(Constants.ColumnRole newRole) {

        Constants.ColumnRole prevRole = columnModel.getRole();
        columnRole.setText(newRole.roleString);
        columnModel.setRole(newRole);

        columnModel.getParent().getActivityLogModel().addChange(new ColumnRoleChange(columnModel, prevRole, newRole));
    }

    /**
     * Delete a specific {@link KanbanCard} from this {@link KanbanColumn}
     * @param kanbanCard - {@link KanbanCard}
     */
    public void deleteCard(KanbanCard kanbanCard) {
        cards.getChildren().remove(kanbanCard);

        CardModel cardModelToDelete = kanbanCard.getController().getData();
        int position = columnModel.getCards().indexOf(cardModelToDelete);
        columnModel.deleteCard(cardModelToDelete);
    }

    public void removeCard(KanbanCard kanbanCard)
    {
        cards.getChildren().remove(kanbanCard);

        CardModel cardModelToRemove = kanbanCard.getController().getData();
        int position = columnModel.getCards().indexOf(cardModelToRemove);
        columnModel.removeCard(cardModelToRemove);
    }

    @FXML
    private void setColumnRoleDropDown() {
        columnRoleOptions.show(columnRole, JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.LEFT, 0, columnRole.getHeight());
    }

    /**
     * Return the data associated with this ui component
     * @return columnModel
     */
    public ColumnModel getColumnModel() {
        return columnModel;
    }

    public ColumnModel getData() {
        return columnModel;
    }

    /**
     * swaps the cards while they are being dragged across a column
     * @param idx1 - index of the dragged card or the swapping card
     * @param idx2 - index of the dragged card or the swapping
     */
    public void swapCards(int idx1, int idx2) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(cards.getChildren());
        Collections.swap(workingCollection, idx1, idx2);
        cards.getChildren().setAll(workingCollection);
        Collections.swap(columnModel.getCards(), idx1, idx2);

        columnModel.getParent().getActivityLogModel().addChange(
            new CardMoveChange(columnModel.getCards().get(idx2), idx1, columnModel, idx2, columnModel)
        );
    }

    @FXML
    public void editName()
    {
        nameContainer.getChildren().remove(columnName);
        JFXTextField columnEdit = GUIMaker.makeColumnEditField(nameContainer, columnName, columnModel);
        nameContainer.getChildren().add(columnEdit);
        columnEdit.requestFocus();
        columnEdit.selectAll();
    }

    public Label getName()
    {
        return columnName;
    }

    public JFXButton getRoleButton()
    {
        return columnRole;
    }
}
