package callbacks;

import javafx.scene.layout.StackPane;

public interface CardDetailPopupCallback {
    void onStart(StackPane dialogContainer);
    void onSave();
    void onCancel();
}
