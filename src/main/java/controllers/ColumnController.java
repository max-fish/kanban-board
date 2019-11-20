package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ui.KanbanColumn;
import model.Column;
import utils.ComponentMaker;
import java.net.URL;
import java.util.ResourceBundle;


public class ColumnController implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXButton columnMenuButton;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private JFXPopup columnMenu;
    private Column column;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnMenu = ComponentMaker.makeColumnMenu(this);
    }

    @FXML
    public void makeNewCard(){
        //TODO implement making a new card
    }

    @FXML
    public void deleteColumn(MouseEvent ev){
        column.getBoard().deleteColumn(column);
        column = null;

        if(columnMenu.isShowing())
            columnMenu.hide();

        KanbanColumn columnToDelete = (KanbanColumn) rootPane;
        columnToDelete.getBoard().getController().deleteColumn(columnToDelete);
    }

    @FXML
    public void openColumnMenu()
    {
        columnMenu.show(columnMenuButton, JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.LEFT, 0, columnMenuButton.getHeight());
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
