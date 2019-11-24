package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.ColumnModel;
import ui.KanbanColumn;

import java.net.URL;
import java.util.ResourceBundle;


public class ColumnController implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private ColumnModel columnModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setColumnModel(ColumnModel columnModel)
    {
        this.columnModel = columnModel;
    }

    @FXML
    public void makeNewCard(){
        //TODO implement making a new card
    }

    @FXML
    public void deleteColumn(MouseEvent ev){
        columnModel.getBoard().deleteColumn(columnModel);
        columnModel = null;

        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().askToDeleteColumn(columnToDelete);
    }

    void setNameChangeListener()
    {
        columnName.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setName(newValue);
        });
    }

    void setRoleChangeListener()
    {
        columnRole.textProperty().addListener((observable, oldValue, newValue) -> {
            columnModel.setRole(newValue);
        });
    }

}
