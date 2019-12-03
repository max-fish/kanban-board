package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import data.model.CardModel;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.KanbanCard;
import ui.KanbanColumn;
import utils.DragAndDropForCards;

import java.net.URL;
import java.util.ResourceBundle;


public class CardController implements Initializable {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DragAndDropForCards dragAnimation = new DragAndDropForCards();
        KanbanCard card = (KanbanCard) rootPane;
        dragAnimation.setDragAnimation(card, card.getColumn());
    }

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
//        card.getParent().deleteCard(card);
        //System.out.println(card);
//        card = null;

        KanbanCard kanbanCardToDelete = (KanbanCard) rootPane;
        kanbanCardToDelete.getColumn().getController().getColumnModel().deleteCard(card);
        card = null;

        kanbanCardToDelete.getColumn().getController().deleteCard(kanbanCardToDelete);
    }

    public void setTitleChangeListener()
    {
        cardTitle.textProperty().addListener((observable, oldValue, newValue) -> card.setTitle(newValue));
    }

    public void setDescChangeListener()
    {
        descTextArea.textProperty().addListener((observable, oldValue, newValue) -> card.setDescription(newValue));
    }

}
