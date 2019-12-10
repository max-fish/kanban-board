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
        addCardIcon.setId("AddButton");

        JFXButton deleteColumn = new JFXButton("Delete");
        FontIcon deleteColumnIcon = new FontIcon();
        deleteColumn.setGraphic(deleteColumnIcon);
        deleteColumnIcon.setId("DeleteButton");

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

    private static JFXButton makeColumnRoleOption(Constants.ColumnRole role){
        JFXButton roleButton = new JFXButton(role.roleString);
        roleButton.getStylesheets().add("/styling/column_role_option.css");
        return roleButton;
    }

    public static JFXPopup makeColumnRoleDropDown(){
        VBox options = new VBox();
        options.getChildren().addAll(
                makeColumnRoleOption(Constants.ColumnRole.BACKLOG),  makeColumnRoleOption(Constants.ColumnRole.WORK_IN_PROGRESS),
                makeColumnRoleOption(Constants.ColumnRole.ON_HOLD), makeColumnRoleOption(Constants.ColumnRole.COMPLETED_WORK),
                makeColumnRoleOption(Constants.ColumnRole.INFO_ONLY)
                );

        JFXPopup dropDown = new JFXPopup();
        dropDown.setPopupContent(options);
        return dropDown;
    }

    public static void makeWipLimitSnackbar(Pane snackbarContainer){
        JFXSnackbar snackbar = new JFXSnackbar(snackbarContainer);
        JFXSnackbarLayout snackbarLayout = new JFXSnackbarLayout("WIP Limit exceeded");
        snackbarLayout.getStylesheets().add("/styling/wip_limit_snackbar_styling.css");
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(snackbarLayout));
    }
}
