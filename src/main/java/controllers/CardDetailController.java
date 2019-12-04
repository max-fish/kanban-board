package controllers;

import callbacks.CardDetailPopupCallback;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import data.model.CardDetailModel;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CardDetailController implements Initializable {


    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXTextArea storyPointTextArea;

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
        titleTextField.setText(cardDetailModel.getCard().get_title());
        descriptionTextArea.setText(cardDetailModel.getCard().getDescription());
        storyPointTextArea.setText(cardDetailModel.getCard().getStoryPoint());
    }

    @FXML
    public void saveDetails(){
        cardDetailModel.getCard().setTitle(titleTextField.getText());
        cardDetailModel.getCard().setStoryPoint(storyPointTextArea.getText());
        cardDetailModel.getCard().setDescription(descriptionTextArea.getText());
        callback.onSave();
        dialog.close();
    }

    @FXML
    private void cancel() {
        callback.onCancel();
        dialog.close();
    }
}