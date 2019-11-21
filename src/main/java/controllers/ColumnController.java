package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.Card;
import ui.KanbanColumn;
import model.Column;
import ui.TestCard;

import java.io.IOException;


public class ColumnController {
    public VBox cards;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private Column column;

    @FXML
    public void makeNewCard() throws IOException {
        cards.getChildren().add(new TestCard());
    }

    @FXML
    public void deleteColumn(MouseEvent ev){
        column.getBoard().deleteColumn(column);
        column = null;

        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().deleteColumn(columnToDelete);
    }

    public void setColumn(Column column)
    {
        this.column = column;
    }

    public void setNameChangeListener()
    {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> {
            column.setName(newValue);
        });
    }

    public void setRoleChangeListener()
    {
        columnRole.textProperty().addListener((observable, oldValue, newValue) -> {
            column.setRole(newValue);
        });
    }





}
