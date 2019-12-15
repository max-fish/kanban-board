package controllers;

import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles user input when the is asked to confirm the deletion of a component
 */
public class DeleteConfirmationPopupController implements Initializable {
    public JFXButton deleteButton;
    public JFXButton cancelButton;

    private DeleteColumnPopupCallback callback;
    private JFXDialog dialog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //if user confirms intent to delete
        deleteButton.setOnAction(event -> {
            callback.onDelete();
            dialog.close();
        });
        //if user cancels
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
