package utils;

import callbacks.BoardNamePopupCallBack;
import com.jfoenix.controls.*;
import controllers.HomePageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

public class ComponentMaker {
    public static StackPane makeBoardCard(Label title) {
        StackPane boardCard = new StackPane();
        boardCard.setPrefWidth(185);
        boardCard.setPrefHeight(80);
        boardCard.setMaxWidth(185);
        boardCard.setMaxHeight(80);

        title.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 19));
        title.setTextFill(Color.WHITE);

        boardCard.getChildren().add(title);
        StackPane.setAlignment(title, Pos.TOP_LEFT);

        boardCard.setBackground(new Background(new BackgroundFill(MaterialColors.colorPrimary, new CornerRadii(5), Insets.EMPTY)));

        JFXRippler jfxRippler = new JFXRippler(boardCard);
        jfxRippler.setRipplerFill(Color.rgb(176, 133, 245, 0.26));
        return jfxRippler;
    }

    public static JFXButton makeAddButton() {
        JFXButton jfxButton = new JFXButton();
        jfxButton.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(100), Insets.EMPTY)));
        jfxButton.setStyle("-fx-padding: 5");

        FontIcon fontIcon = new FontIcon();
        fontIcon.setIconColor(MaterialColors.colorPrimary);
        fontIcon.setIconLiteral("gmi-add");
        fontIcon.setIconSize(30);

        jfxButton.setGraphic(fontIcon);
        jfxButton.setMaxHeight(30);
        jfxButton.setMaxWidth(30);
        jfxButton.setMinHeight(30);
        jfxButton.setMinWidth(30);
        return jfxButton;
    }

    public static void makeBoardNamePopup(BoardNamePopupCallBack callBack) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBackground(new Background(new BackgroundFill(MaterialColors.colorPrimary, new CornerRadii(5), Insets.EMPTY)));

        JFXTextField boardNameTextField = new JFXTextField();
        boardNameTextField.setPromptText("Board Name");
        boardNameTextField.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        boardNameTextField.setFocusColor(MaterialColors.colorAccent);
        boardNameTextField.setUnFocusColor(MaterialColors.colorLight);
        boardNameTextField.setFont(Font.loadFont(HomePageController.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 16));

        Label header = new Label("Name your board");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.loadFont(HomePageController.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 14));
        content.setHeading(header);
        content.setBody(boardNameTextField);
        content.setPrefSize(400, 100);

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(400, 100);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER, false);

        Font buttonFont = Font.loadFont(HomePageController.class.getResource("/fonts/Roboto-Bold.ttf").toExternalForm(), 14);
        JFXButton okButton = new JFXButton("Ok");
        okButton.setTextFill(Color.WHITE);
        okButton.setFont(buttonFont);
        okButton.setPrefHeight(32);

        okButton.setOnAction(event -> {
            if (!boardNameTextField.getText().isEmpty()) {
                dialog.close();
                callBack.onValidName(boardNameTextField.getText());
            }
        });
        JFXButton cancelButton = new JFXButton("Cancel");
        cancelButton.setTextFill(Color.WHITE);
        cancelButton.setFont(buttonFont);
        cancelButton.setOnAction(event -> {
            dialog.close();
            callBack.onCancel();
        });
        cancelButton.setButtonType(JFXButton.ButtonType.RAISED);
        cancelButton.setPrefHeight(32);
        content.setActions(okButton, cancelButton);
        callBack.onStart(stackPane);
        dialog.show();
    }
}
