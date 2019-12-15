package controllers;

import callbacks.CardDetailPopupCallback;
import com.jfoenix.controls.*;
import data.model.CardModel;
import data.log.CardEditChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CardDetailController implements Initializable {


    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXComboBox<Integer> storyPointCombo;

    private CardModel cardModel;

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

    public void fillWithData(CardModel cardModel) {
        this.cardModel = cardModel;
        titleTextField.setText(cardModel.getTitle());
        descriptionTextArea.setText(cardModel.getDescription());
        storyPointCombo.setValue(cardModel.getStoryPoint());
    }

    @FXML
    public void saveDetails(){
        String prevName = cardModel.getTitle();
        String prevDescription = cardModel.getDescription();
        int prevStoryPoint = cardModel.getStoryPoint();
          System.out.println(prevName + " - " + prevDescription + " " + prevStoryPoint);

        cardModel.setTitle(titleTextField.getText());
        cardModel.setStoryPoint(storyPointCombo.getValue());
        cardModel.setDescription(descriptionTextArea.getText());

        CardEditChange change = new CardEditChange(cardModel, prevName, cardModel.getTitle(),
                                                    prevDescription, cardModel.getDescription(),
                                                    prevStoryPoint, cardModel.getStoryPoint());

        cardModel.getParent().getParent().getActivityLogModel().addChange(change);
          System.out.println(cardModel.getTitle() + " - " + cardModel.getDescription() + " " + cardModel.getStoryPoint());

        callback.onSave(cardModel);
        dialog.close();
    }

    @FXML
    private void cancel() {
        callback.onCancel();
        dialog.close();
    }
}
