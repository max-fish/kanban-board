package callbacks;

import javafx.scene.layout.StackPane;

public interface BoardNamePopupCallBack {
    void onStart(StackPane dialogContainer);
    void onValidName(String name);
    void onCancel();
}
