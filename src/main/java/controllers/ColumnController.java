package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ui.KanbanColumn;
import model.Column;


public class ColumnController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private Column column;

    @FXML
    public void makeNewCard(){
        //TODO implement making a new card
    }

    @FXML
    public void deleteColumn(MouseEvent ev){
        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().getBoardModel().deleteColumn(column);
        column = null;

        columnToDelete.getBoard().getController().deleteColumn(columnToDelete);
    }

    public void setColumn(Column column)
    {
        this.column = column;
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
