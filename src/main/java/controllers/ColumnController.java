package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.ColumnModel;
import ui.KanbanColumn;


public class ColumnController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private ColumnModel columnModel;

    @FXML
    public void makeNewCard(){
        //TODO implement making a new card
    }

    @FXML
    public void deleteColumn(MouseEvent ev){
        columnModel.getBoard().deleteColumn(columnModel);
        columnModel = null;

        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().deleteColumn(columnToDelete);
    }

    public void setColumnModel(ColumnModel columnModel)
    {
        this.columnModel = columnModel;
    }

    public void setNameChangeListener()
    {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setName(newValue);
        });
    }

    public void setRoleChangeListener()
    {
        columnRole.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setRole(newValue);
        });
    }
}
