package utils;

import com.jfoenix.controls.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

public class ComponentMaker {
    public static StackPane makeBoardCard(String title) {
        StackPane boardCard = new StackPane();
        boardCard.setId("BoardCard");

        Label boardLabel = new Label(title);
        boardCard.getChildren().add(boardLabel);
        StackPane.setAlignment(boardLabel, Pos.TOP_LEFT);

        JFXRippler jfxRippler = new JFXRippler(boardCard);
        jfxRippler.getStylesheets().add("/styling/board_card_styling.css");
        return jfxRippler;
    }

    public static JFXButton makeAddButton() {
        JFXButton jfxButton = new JFXButton();
        jfxButton.getStylesheets().add("/styling/add_button_styling.css");
        FontIcon fontIcon = new FontIcon();
        jfxButton.setGraphic(fontIcon);
        return jfxButton;
    }

    public static JFXPopup makeColumnMenu()
    {
        JFXButton addCard = new JFXButton("Add card");
        FontIcon addCardIcon = new FontIcon();
        addCard.setGraphic(addCardIcon);
        addCard.setId("AddButton");

        JFXButton deleteColumn = new JFXButton("Delete");
        FontIcon deleteColumnIcon = new FontIcon();
        deleteColumn.setGraphic(deleteColumnIcon);
        deleteColumn.setId("DeleteButton");

        VBox container = new VBox(addCard, deleteColumn);
        container.getStylesheets().add("/styling/column_menu_styling.css");

        JFXPopup menu = new JFXPopup();
        menu.setPopupContent(container);

        return menu;
    }

    public static JFXPopup makeFileMenu()
    {
        JFXButton importJSON = new JFXButton("Import from JSON");
        FontIcon importIcon = new FontIcon();
        importJSON.setGraphic(importIcon);
        importIcon.setId("Import");

        JFXButton exportJSON = new JFXButton("Export to JSON");
        FontIcon exportIcon = new FontIcon();
        exportJSON.setGraphic(exportIcon);
        exportIcon.setId("Export");

        VBox container = new VBox(importJSON, exportJSON);
        container.getStylesheets().add("/styling/saving_styling.css");

        JFXPopup menu = new JFXPopup();
        menu.setPopupContent(container);

        return menu;
    }

    private static JFXButton makeColumnRoleOption(String role){
        JFXButton roleButton = new JFXButton(role);
        roleButton.getStylesheets().add("/styling/column_role_option.css");
        return roleButton;
    }

    public static JFXPopup makeColumnRoleDropDown(){
        VBox options = new VBox();
        options.getChildren().addAll(
                makeColumnRoleOption("Backlog"),  makeColumnRoleOption("Work In Progress"),
                makeColumnRoleOption("On Hold"), makeColumnRoleOption("Completed Work"),
                makeColumnRoleOption("Info only")
                );
        JFXButton backlog = makeColumnRoleOption("Backlog");

        options.getChildren().add(backlog);
        JFXPopup dropDown = new JFXPopup();
        dropDown.setPopupContent(options);
        return dropDown;
    }
}
