package controllers;

import callbacks.CardDetailPopupCallback;
import com.jfoenix.controls.*;
import data.model.CardModel;
import javafx.fxml.FXML;

/**
 * This class handles user input when the user is changing the details of the {@link ui.KanbanCard}
 */
public class CardDetailController {
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXComboBox<Integer> storyPointCombo;

    private CardModel cardModel;

    private CardDetailPopupCallback callback;

    private JFXDialog dialog;

    public void setBoardNamePopupCallBack(CardDetailPopupCallback cardDetailPopupCallback) {
        callback = cardDetailPopupCallback;
    }

    public void setUi(JFXDialog jfxDialog) {
        dialog = jfxDialog;
    }

    /**
     * inflate the {@link ui.CardDetailPopup} with data from a {@link CardModel}
     * @param cardModel - {@link CardModel}
     */
    public void fillWithData(CardModel cardModel) {
        this.cardModel = cardModel;
        titleTextField.setText(cardModel.getTitle());
        descriptionTextArea.setText(cardModel.getDescription());
        storyPointCombo.setValue(cardModel.getStoryPoint());
    }

    @FXML
    private void saveDetails(){
        cardModel.setTitle(titleTextField.getText());
        cardModel.setStoryPoint(storyPointCombo.getValue());
        cardModel.setDescription(descriptionTextArea.getText());
        callback.onSave(cardModel);
        dialog.close();
    }

    @FXML
    private void cancel() {
        callback.onCancel();
        dialog.close();
    }
}