package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import model.CardModel;

import javafx.scene.input.MouseEvent;
import ui.KanbanCard;


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
    }

    @FXML
    public void deleteCard(MouseEvent event){
        card.getParent().deleteCard(card);
        //System.out.println(card);
        card = null;

        KanbanCard kanbanCardToDelete = (KanbanCard) rootPane;
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
