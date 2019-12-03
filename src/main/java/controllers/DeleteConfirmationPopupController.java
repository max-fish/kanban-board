package controllers;

import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteConfirmationPopupController implements Initializable {
    public JFXButton deleteButton;
    public JFXButton cancelButton;

    private DeleteColumnPopupCallback callback;
    private JFXDialog dialog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteButton.setOnAction(event -> {
            callback.onDelete();
            dialog.close();
        });
        cancelButton.setOnAction(event -> {
            dialog.close();
            callback.onCancel();
        });
    }

    public void setDeleteColumnPopupCallback(DeleteColumnPopupCallback boardNamePopupCallBack) {
        callback = boardNamePopupCallBack;
    }

    public void setUi(JFXDialog jfxDialog) {
        dialog = jfxDialog;
    }
}
