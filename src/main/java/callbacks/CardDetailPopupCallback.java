package callbacks;

import data.model.CardModel;
import javafx.scene.layout.StackPane;

public interface CardDetailPopupCallback {
    CardModel onStart(StackPane dialogContainer);
    void onSave(CardModel cardModel);
    void onCancel();
}
