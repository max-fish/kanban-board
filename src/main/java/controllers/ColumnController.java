package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import model.CardModel;
import model.ColumnModel;
import ui.KanbanCard;
import ui.KanbanColumn;
import utils.GUIMaker;
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
    private JFXButton columnMenuButton;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private JFXPopup columnMenu;
    private ColumnModel columnModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnMenu = GUIMaker.makeColumnMenu(this);

        DragAndDrop dragAnimation = new DragAndDrop();
        KanbanColumn kanbanColumn = (KanbanColumn) rootPane;
        dragAnimation.setDragAnimation(kanbanColumn,  (HBox) ((ScrollPane) kanbanColumn.getBoard().getCenter()).getContent());
    }

    public void makeNewCard(MouseEvent event)
    {
        CardModel newCardModel = new CardModel(/*columnModel*/);

        makeNewCard(newCardModel);
    }

    public void makeNewCard(CardModel newCardModel)
    {
        try
        {
            KanbanCard newCard = new KanbanCard((KanbanColumn) rootPane);
            cards.getChildren().add(newCard);

            if(!columnModel.contains(newCardModel))
                columnModel.addCard(newCardModel);

            newCard.getController().setCard(newCardModel);
        }
        catch(IOException exception)
        {
            System.out.println("The card could not be created");
            exception.printStackTrace();
        }
    }

    public void deleteColumn(MouseEvent event) {
        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().askToDeleteColumn(columnToDelete, () -> {
            if(columnMenu.isShowing())
                columnMenu.hide();

            columnToDelete.getBoard().getController().getBoardModel().deleteColumn(columnModel);
            //columnModel.getBoard().deleteColumn(columnModel);
            columnModel = null;
        });
    }

    @FXML
    public void openColumnMenu()
    {
        columnMenu.show(columnMenuButton, JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.LEFT, 0, columnMenuButton.getHeight());
    }

    public void setColumnModel(ColumnModel columnModel)
    {
        this.columnModel = columnModel;
    }

    public void setColumnName(String name)
    {
        columnName.setText(name);
    }

    public void setColumnRole(String role)
    {
        columnRole.setText(role);
    }

    public void setNameChangeListener()
    {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> columnModel.setName(newValue));
    }

    public void setRoleChangeListener() {
        columnRole.textProperty().addListener((observable, oldValue, newValue) -> columnModel.setRole(newValue));
    }

    public void deleteCard(KanbanCard kanbanCard) {
        cards.getChildren().remove(kanbanCard);
    }

    public ColumnModel getColumnModel()
    {
        return columnModel;
    }
}
