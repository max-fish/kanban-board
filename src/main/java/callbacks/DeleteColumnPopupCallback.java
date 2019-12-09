package callbacks;

import javafx.scene.layout.StackPane;

public interface DeleteColumnPopupCallback {
    void onStart(StackPane stackPane);
    void onDelete();
    void onCancel();
}
