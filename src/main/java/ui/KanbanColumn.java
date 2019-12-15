package ui;

import controllers.ColumnController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * This class inflates a {@link BorderPane} with a custom fxml layout, making it a KanbanColumn.
 */
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

    /**
     * To make this KanbanCard mutable in response to user input
     * @return {@link ColumnController}
     */
    public ColumnController getController() {
        return columnController;
    }

    /**
     * To make it possible to navigate from one Kanban component to another
     * @return the {@link KanbanBoard} container the encloses this KanbanCard.
     */
    public KanbanBoard getBoard() {
        return parent;
    }
}
