package ui;

import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.DeleteConfirmationPopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

/**
 * This class inflates a {@link JFXDialog} with a custom fxml layout, making it a DeleteConfrimationPopup.
 */
public class DeleteConfirmationPopup extends JFXDialog {
    private DeleteColumnPopupCallback deleteColumnPopupCallback;
    private StackPane dialogContainer;

    public DeleteConfirmationPopup(DeleteColumnPopupCallback deleteColumnPopupCallback, Node currentUi){
        this.deleteColumnPopupCallback = deleteColumnPopupCallback;
        JFXDialogLayout jfxDialogLayout = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/delete_confirmation_popup_layout.fxml"));
        try {
            jfxDialogLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeleteConfirmationPopupController deleteConfirmationPopupController = fxmlLoader.getController();
        deleteConfirmationPopupController.setDeleteColumnPopupCallback(deleteColumnPopupCallback);
        deleteConfirmationPopupController.setUi(this);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentUi);
        dialogContainer = stackPane;

        setDialogContainer(stackPane);
        setContent(Objects.requireNonNull(jfxDialogLayout));
        setTransitionType(DialogTransition.CENTER);
        setOverlayClose(false);
    }

    @Override
    public void show() {
        deleteColumnPopupCallback.onStart(dialogContainer);
        super.show();
    }
}
