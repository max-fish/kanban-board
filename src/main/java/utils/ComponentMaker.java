package utils;

import callbacks.BoardNamePopupCallBack;
import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.*;
import controllers.HomePageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

    public static void makeBoardNamePopup(BoardNamePopupCallBack callBack, Node currentUi) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBackground(new Background(new BackgroundFill(MaterialColors.colorPrimary, new CornerRadii(5), Insets.EMPTY)));

        JFXTextField boardNameTextField = new JFXTextField();
        boardNameTextField.setPromptText("Board Name");

        Label header = new Label("Name your board");
        content.setHeading(header);
        content.setBody(boardNameTextField);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentUi);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER, false);
        dialog.getStylesheets().add("/styling/board_name_popup_styling.css");
        JFXButton okButton = new JFXButton("Ok");

        okButton.setOnAction(event -> {
            if (!boardNameTextField.getText().isEmpty()) {
                dialog.close();
                callBack.onValidName(boardNameTextField.getText());
            }
        });
        JFXButton cancelButton = new JFXButton("Cancel");
        cancelButton.setOnAction(event -> {
            dialog.close();
            callBack.onCancel();
        });
        content.setActions(okButton, cancelButton);
        callBack.onStart(stackPane);
        dialog.show();
    }

    public static void makeDeleteConfirmationPopup(DeleteColumnPopupCallback callback, Node currentUi){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
        Label header = new Label("Delete Column");
        header.setTextFill(MaterialColors.colorPrimary);
        header.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 14));
        content.setHeading(header);
        Label body = new Label("Are you sure?");
        body.setTextFill(MaterialColors.colorPrimary);
        body.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 14));
        content.setBody(body);
        content.setPrefSize(400,100);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentUi);
        stackPane.setPrefSize(400, 100);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER, false);

        Font buttonFont = Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Bold.ttf").toExternalForm(), 14);
        JFXButton deleteButton = new JFXButton("Delete");
        deleteButton.setTextFill(MaterialColors.colorPrimary);
        deleteButton.setFont(buttonFont);
        deleteButton.setPrefHeight(32);
        deleteButton.setButtonType(JFXButton.ButtonType.FLAT);

        deleteButton.setOnAction(event -> {
            callback.onDelete();
            dialog.close();
        });

        JFXButton cancelButton = new JFXButton("Cancel");
        cancelButton.setTextFill(MaterialColors.colorPrimary);
        deleteButton.setFont(buttonFont);
        deleteButton.setPrefHeight(32);
        cancelButton.setButtonType(JFXButton.ButtonType.FLAT);
        cancelButton.setOnAction(event -> {
            callback.onCancel();
            dialog.close();
        });

        content.setActions(deleteButton, cancelButton);
        callback.onStart(stackPane);
        dialog.show();


    }
}
