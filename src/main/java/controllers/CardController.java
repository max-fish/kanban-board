package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import model.BoardModel;
import model.CardDetailModel;
import model.CardModel;

import javafx.scene.input.MouseEvent;
import model.StatisticsModel;
import ui.CardDetail;
import ui.KanbanCard;
import ui.Statistics;

import java.io.IOException;


public class CardController {
    @FXML
    private JFXTextField cardTitle;

//    @FXML
//    private DatePicker dueDatePicker;

    @FXML
    private JFXTextField descTextArea;

    @FXML
    private BorderPane rootPane;

    @FXML
    private BorderPane infoPage;

//    @FXML
//    private Button add;

    private CardModel card;




    public void setCard(CardModel card)
    {
        this.card = card;
    }

    @FXML
    public void editDetails(MouseEvent event){
        //TODO: open a new page to edit the details
        card.setTitle(cardTitle.getText());
        try {
            CardDetail toShow = new CardDetail();
            CardDetailModel model = new CardDetailModel(card);
            toShow.getController().setCardDetailModel(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteCard(MouseEvent event){

        KanbanCard kanbanCardToDelete = (KanbanCard) rootPane;
        kanbanCardToDelete.getColumn().getController().getColumnModel().deleteCard(card);
        card = null;

        kanbanCardToDelete.getColumn().getController().deleteCard(kanbanCardToDelete);
    }

    public void setTitleChangeListener()
    {
        cardTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            card.setTitle(newValue);
        });
    }

    public void setDescChangeListener()
    {
        descTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            card.setDescription(newValue);
        });
    }





}
