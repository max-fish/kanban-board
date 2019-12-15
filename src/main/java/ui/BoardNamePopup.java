package ui;

import callbacks.BoardNamePopupCallBack;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.BoardNamePopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.io.IOException;

/**
 *  This class inflates a {@link JFXDialog} with a custom fxml layout, making it a BoardNamePopup.
 */
public class BoardNamePopup extends JFXDialog {

    private BoardNamePopupCallBack boardNamePopupCallBack;
    private StackPane dialogContainer;

    public BoardNamePopup(BoardNamePopupCallBack boardNamePopupCallBack, Node currentUi) {
        this.boardNamePopupCallBack = boardNamePopupCallBack;
        JFXDialogLayout jfxDialogLayout = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/board_name_popup_layout.fxml"));
        try {
            jfxDialogLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoardNamePopupController boardNamePopupController = fxmlLoader.getController();
        boardNamePopupController.setBoardNamePopupCallBack(boardNamePopupCallBack);
        boardNamePopupController.setUi(this);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentUi);
        dialogContainer = stackPane;

        setDialogContainer(stackPane);
        setContent(jfxDialogLayout);
        setTransitionType(DialogTransition.CENTER);
        setOverlayClose(false);
    }

    @Override
    public void show() {
        boardNamePopupCallBack.onStart(dialogContainer);
        super.show();
    }
}
