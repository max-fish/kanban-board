package utils;

import com.jfoenix.controls.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.scene.input.KeyCode;

import data.model.BoardModel;
import data.model.ColumnModel;
import data.log.Change;

public class GUIMaker {

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

    public static Label makeChangeLabel(Change change)
    {
        Label changeLabel = new Label(change.toString());
        if(!change.isApplied())
            changeLabel.setStyle("-fx-background-color: rgba(210, 120, 255, 0.4)");
        return changeLabel;
    }

    public static JFXTextField makeBoardEditField(Pane titleContainer, Label boardTitle, BoardModel boardModel)
    {
        JFXTextField boardEdit = new JFXTextField(boardTitle.getText());
        boardEdit.setId("BoardEdit");
        boardEdit.getStylesheets().add("/styling/board_name_edit_styling.css");
        boardEdit.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
            {
                String newName = boardEdit.getText();
                  System.out.println("New name: " + newName);


                if(!newName.isEmpty())
                {
                    if(!newName.equals(boardModel.getName()))
                    {
                        boardTitle.setText(newName);
                        boardModel.setName(newName);
                    }
                }

                titleContainer.getChildren().remove(boardEdit);
                titleContainer.getChildren().add(boardTitle);
            }
        });

        return boardEdit;
    }

    public static JFXTextField makeColumnEditField(Pane nameContainer, Label columnName, ColumnModel columnModel)
    {
        JFXTextField columnEdit = new JFXTextField(columnName.getText());
        columnEdit.setId("ColumnEdit");
        columnEdit.getStylesheets().add("/styling/column_name_edit_styling.css");
        columnEdit.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
            {
                String newName = columnEdit.getText();
                  System.out.println("New name: " + newName);


                if(!newName.isEmpty())
                {
                    if(!newName.equals(columnModel.getName()))
                    {
                        columnName.setText(newName);
                        columnModel.setName(newName);
                    }
                }

                nameContainer.getChildren().remove(columnEdit);
                nameContainer.getChildren().add(columnName);
            }
        });

        return columnEdit;
    }
}
