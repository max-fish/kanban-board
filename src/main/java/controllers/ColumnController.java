package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ui.KanbanColumn;
import model.Column;


public class ColumnController {
    public VBox cards;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXTextField columnName;
    @FXML
    private JFXTextField columnRole;

    private ColumnModel columnModel;

    @FXML
    public void makeNewCard() throws IOException {
        cards.getChildren().add(new TestCard());
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
