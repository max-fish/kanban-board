package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ui.KanbanColumn;
import model.Column;


public class ColumnController {
    @FXML
    private BorderPane rootPane;

    private Column column;

    @FXML
    public void makeNewCard(){
        //TODO implement making a new card
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
}
