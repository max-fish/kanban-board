package ui;

import callbacks.CardDetailPopupCallback;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.CardDetailController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class CardDetailPopup extends JFXDialog {
    private CardDetailController cardDetailController;
    private CardDetailPopupCallback callback;
    private StackPane dialogContainer;

    public CardDetailPopup(CardDetailPopupCallback callback, Node currentUi){
        this.callback = callback;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/card_details_ui.fxml"));
        JFXDialogLayout jfxDialogLayout = null;
        try {
            jfxDialogLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardDetailController = fxmlLoader.getController();

        cardDetailController.setBoardNamePopupCallBack(callback);
        cardDetailController.setUi(this);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentUi);
        dialogContainer = stackPane;

        setDialogContainer(stackPane);
        setContent(jfxDialogLayout);
        setTransitionType(DialogTransition.CENTER);
        setOverlayClose(false);
    }

    public CardDetailController getController(){
        return cardDetailController;
    }

    @Override
    public void show() {
        cardDetailController.fillWithData(callback.onStart(dialogContainer));
        super.show();
    }
}
