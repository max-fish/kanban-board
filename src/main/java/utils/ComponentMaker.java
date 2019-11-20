package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXPopup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;
import controllers.ColumnController;

public class ComponentMaker {
    public static StackPane makeBoardCard(Label title) {
        StackPane boardCard = new StackPane();
        boardCard.setPrefWidth(185);
        boardCard.setPrefHeight(80);
        boardCard.setMaxWidth(185);
        boardCard.setMaxHeight(80);

        Label titleLabel = title;
        titleLabel.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 19));
        titleLabel.setTextFill(Color.WHITE);

        boardCard.getChildren().add(titleLabel);
        StackPane.setAlignment(titleLabel, Pos.TOP_LEFT);

        boardCard.setBackground(new Background(new BackgroundFill(MaterialColors.colorPrimary, new CornerRadii(5), Insets.EMPTY)));

        JFXRippler jfxRippler = new JFXRippler(boardCard);
        jfxRippler.setRipplerFill(Color.rgb(176, 133, 245, 0.26));
        return jfxRippler;
    }

    public static JFXButton makeAddButton() {
        JFXButton jfxButton = new JFXButton();
        jfxButton.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton.setBackground(new Background((new BackgroundFill(Color.WHITE, new CornerRadii(100), Insets.EMPTY))));
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

    public static JFXPopup makeColumnMenu(ColumnController controller)
    {
        JFXButton addCard = new JFXButton("Add card");
        FontIcon addCardIcon = new FontIcon();
        addCardIcon.setIconColor(MaterialColors.colorPrimary);
        addCardIcon.setIconLiteral("gmi-add");
        addCardIcon.setIconSize(17);
        addCard.setGraphic(addCardIcon);
        addCard.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 15));
        addCard.setAlignment(Pos.BASELINE_LEFT);
        addCard.setMinWidth(120);
        // TODO: setOnMouseClicked(...)

        JFXButton deleteColumn = new JFXButton("Delete");
        FontIcon deleteColumnIcon = new FontIcon();
        deleteColumnIcon.setIconColor(MaterialColors.colorPrimary);
        deleteColumnIcon.setIconLiteral("gmi-delete");
        deleteColumnIcon.setIconSize(17);
        deleteColumn.setGraphic(deleteColumnIcon);
        deleteColumn.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 15));
        deleteColumn.setAlignment(Pos.BASELINE_LEFT);
        deleteColumn.setMinWidth(120);
        deleteColumn.setOnMouseClicked(controller::deleteColumn);

        VBox container = new VBox(addCard, deleteColumn);

        JFXPopup menu = new JFXPopup();
        menu.setPopupContent(container);

        return menu;
    }
}
