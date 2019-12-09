package controllers;

import callbacks.CardDetailPopupCallback;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import data.model.CardModel;

import data.model.CardDetailModel;
import javafx.scene.layout.StackPane;
import ui.CardDetailPopup;
import ui.KanbanBoard;
import ui.KanbanCard;
import utils.DragAndDropForCards;

import java.net.URL;
import java.util.ResourceBundle;


public class CardController implements Initializable {
    @FXML
    private JFXTextField cardTitle;
    @FXML
    private BorderPane rootPane;

    private CardModel cardModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DragAndDropForCards dragAnimation = new DragAndDropForCards();
        KanbanCard card = (KanbanCard) rootPane;
        dragAnimation.setDragAnimation(card);

        cardTitle.textProperty().addListener((observable, oldValue, newValue) -> cardModel.setTitle(newValue));
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
            public void onStart(StackPane dialogContainer) {
                homePage.setCenter(dialogContainer);
            }

            @Override
            public void onSave() {
                cardTitle.setText(cardModel.getTitle());
                homePage.setCenter(board);
            }

            @Override
            public void onCancel() {
                homePage.setCenter(board);
            }
        }, homePage.getCenter());

        CardDetailModel model = new CardDetailModel(cardModel);
        cardDetailPopup.getController().setCardDetailModel(model);
        cardDetailPopup.show();
    }

    @FXML
    public void deleteCard() {
        KanbanCard kanbanCardToDelete = (KanbanCard) rootPane;
        kanbanCardToDelete.getColumn().getController().deleteCard(kanbanCardToDelete);
        cardModel = null;
    }

    public CardModel getData() {
        return cardModel;
    }
}
