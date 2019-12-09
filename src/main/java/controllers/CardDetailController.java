package controllers;

import callbacks.CardDetailPopupCallback;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import data.model.CardDetailModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CardDetailController implements Initializable {


    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXComboBox<Integer> storyPointCombo;

    private CardDetailModel cardDetailModel;

    private CardDetailPopupCallback callback;

    private JFXDialog dialog;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBoardNamePopupCallBack(CardDetailPopupCallback cardDetailPopupCallback) {
        callback = cardDetailPopupCallback;
    }

    public void setUi(JFXDialog jfxDialog) {
        dialog = jfxDialog;
    }

    void setCardDetailModel(CardDetailModel cardDetailModel) {
        this.cardDetailModel = cardDetailModel;
        titleTextField.setText(cardDetailModel.getCardModel().getTitle());
        descriptionTextArea.setText(cardDetailModel.getCardModel().getDescription());
        storyPointCombo.setValue(cardDetailModel.getCardModel().getStoryPoints());
    }

    @FXML
    public void saveDetails(){
        cardDetailModel.getCardModel().setTitle(titleTextField.getText());
        cardDetailModel.getCardModel().setStoryPoint(storyPointCombo.getValue());
        cardDetailModel.getCardModel().setDescription(descriptionTextArea.getText());
        callback.onSave();
        dialog.close();
    }

    @FXML
    private void cancel() {
        callback.onCancel();
        dialog.close();
    }
}