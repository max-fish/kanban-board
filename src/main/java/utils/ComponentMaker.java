package utils;

import controllers.KanbanBoardController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class ComponentMaker {
    public static StackPane makeBoardCard(String title) {
        StackPane boardCard = new StackPane();
        boardCard.setPrefWidth(185);
        boardCard.setPrefHeight(80);
        boardCard.setMaxWidth(185);
        boardCard.setMaxHeight(80);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.loadFont(ComponentMaker.class.getResource("/fonts/Roboto-Regular.ttf").toExternalForm(), 19));
        titleLabel.setTextFill(Color.WHITE);

        boardCard.getChildren().add(titleLabel);
        StackPane.setAlignment(titleLabel, Pos.TOP_LEFT);

        boardCard.setBackground(new Background(new BackgroundFill(MaterialColors.colorLight, new CornerRadii(5), Insets.EMPTY)));
        return boardCard;
    }

    public static BorderPane makeBoard(String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ComponentMaker.class.getResource("/layouts/kanban_board_ui.fxml"));
        BorderPane board = fxmlLoader.load();
        KanbanBoardController kanbanBoardController = fxmlLoader.getController();
        kanbanBoardController.changeTitle(title);
        return board;
    }

    public static BorderPane makeColumn() throws IOException {
        return FXMLLoader.load(ComponentMaker.class.getResource("/layouts/kanban_column_ui.fxml"));
    }
}
