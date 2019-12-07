package ui;

import controllers.ColumnController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class KanbanColumn extends BorderPane {
    private KanbanBoard parent;
    private ColumnController columnController;

    public KanbanColumn(KanbanBoard parent) {
        this.parent = parent;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/kanban_column_ui.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        columnController = fxmlLoader.getController();
    }

    public ColumnController getController() {
        return columnController;
    }

    public KanbanBoard getBoard() {
        return parent;
    }
}
