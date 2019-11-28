package utils;

import callbacks.BoardNamePopupCallBack;
import callbacks.DeleteColumnPopupCallback;
import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    public static void makeDeleteConfirmationPopup(DeleteColumnPopupCallback callback, Node currentUi){
        JFXDialogLayout content = new JFXDialogLayout();
        Label header = new Label("Delete Column");
        content.setHeading(header);
        Label body = new Label("Are you sure?");
        content.setBody(body);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentUi);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER, false);
        dialog.getStylesheets().add("/styling/delete_confirmation_popup_styling.css");

        JFXButton deleteButton = new JFXButton("Delete");

        deleteButton.setOnAction(event -> {
            callback.onDelete();
            dialog.close();
        });

        JFXButton cancelButton = new JFXButton("Cancel");
        cancelButton.setOnAction(event -> {
            dialog.close();
            callback.onCancel();
        });

        content.setActions(deleteButton, cancelButton);
        callback.onStart(stackPane);
        dialog.show();
    }

    public static JFXButton makeStatisticsButton(){
        JFXButton jfxButton = new JFXButton();
        jfxButton.setButtonType(JFXButton.ButtonType.RAISED);
        //jfxButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(100), Insets.EMPTY)));
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
}
