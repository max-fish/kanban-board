package controllers;

import callbacks.CardDetailPopupCallback;
import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

import data.model.CardModel;
import data.log.CardEditChange;

import javafx.scene.layout.StackPane;
import ui.CardDetailPopup;
import ui.DeleteConfirmationPopup;
import ui.KanbanBoard;
import ui.KanbanCard;
import utils.DragAndDropForCards;

import java.net.URL;
import java.util.ResourceBundle;


public class CardController implements Initializable {
    @FXML
    private Label cardTitle;
    @FXML
    private BorderPane rootPane;

    private CardModel cardModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DragAndDropForCards dragAnimation = new DragAndDropForCards();
        KanbanCard card = (KanbanCard) rootPane;
        dragAnimation.setDragAnimation(card);

        /*cardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            cardModel.setTitle(newValue);
            cardModel.getParent().getParent().getActivityLogModel().addChange(new CardEditChange(cardModel, oldValue, newValue));
          });*/
    }

    public void fillWithData(CardModel cardModel) {
        this.cardModel = cardModel;
        cardTitle.setText(cardModel.getTitle());
    }

    @FXML
    public void editDetails() {
        KanbanCard card = (KanbanCard) rootPane;
        KanbanBoard board = card.getColumn().getBoard();
        BorderPane homePage = board.getHomePage();
        cardModel.setTitle(cardTitle.getText());
        CardDetailPopup cardDetailPopup = new CardDetailPopup(new CardDetailPopupCallback() {
            @Override
            public CardModel onStart(StackPane dialogContainer) {
                homePage.setCenter(dialogContainer);
                return cardModel;
            }

            @Override
            public void onSave(CardModel cardModel) {
                CardController.this.cardModel = cardModel;
                cardTitle.setText(cardModel.getTitle());
                  System.out.println(cardTitle.getText());

                homePage.setCenter(board);
            }

            @Override
            public void onCancel() {
                homePage.setCenter(board);
            }
        }, homePage.getCenter());

        cardDetailPopup.show();
    }

    @FXML
    public void deleteCard() {
        KanbanCard kanbanCardToDelete = (KanbanCard) rootPane;
        KanbanBoard board = kanbanCardToDelete.getColumn().getBoard();
        BorderPane homePage = board.getHomePage();
        DeleteConfirmationPopup deleteCardConfirmation = new DeleteConfirmationPopup(new DeleteColumnPopupCallback() {
            @Override
            public void onStart(StackPane stackPane) {
                homePage.setCenter(stackPane);
            }

            @Override
            public void onDelete() {
                kanbanCardToDelete.getColumn().getController().deleteCard(kanbanCardToDelete);
                cardModel = null;
                homePage.setCenter(board);
            }

            @Override
            public void onCancel() {
                homePage.setCenter(board);
            }
        }, homePage.getCenter());

        deleteCardConfirmation.show();
    }

    public void removeCard(KanbanCard cardToRemove){
        cardToRemove.getColumn().getController().removeCard(cardToRemove);
    }

    public CardModel getData() {
        return cardModel;
    }
}
