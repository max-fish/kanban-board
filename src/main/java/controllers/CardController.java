package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
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
        KanbanCard kanbanCardToDelete = (KanbanCard) rootPane;
        kanbanCardToDelete.getColumn().getController().deleteCard(kanbanCardToDelete);

        card.getColumn().deleteCard(card);
        card = null;
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

    public void setTitle(String title)
    {
        cardTitle.setText(title);
    }
}
