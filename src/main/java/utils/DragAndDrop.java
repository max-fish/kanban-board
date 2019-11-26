package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import ui.KanbanColumn;

import java.util.Collections;

public class DragAndDrop {
    private double orgSceneX;
    private double orgTranslateX;
    private HBox board;
    private double boundX;

    public void setDragAnimation(KanbanColumn rootPane, HBox board) {
        this.board = board;
        boundX = board.getChildren().size() * 200;
        rootPane.setOnMousePressed(onMousePressed);
        rootPane.setOnMouseDragged(onMouseDragged);
        rootPane.setOnMouseReleased(onDragOver);
    }

    private EventHandler<MouseEvent> onMousePressed =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    orgSceneX = event.getSceneX();
                    orgTranslateX = ((BorderPane) (event.getSource())).getTranslateX();
                    boundX = board.getChildren().size() * 200;

                    BorderPane itemBeingDragged = (BorderPane) event.getSource();
                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setRadius(5);
                    dropShadow.setOffsetX(7);
                    dropShadow.setOffsetY(7);
                    dropShadow.setColor(Color.WHITE);
                    dropShadow.setBlurType(BlurType.GAUSSIAN);
                    itemBeingDragged.setEffect(dropShadow);

                    Rotate tilt = new Rotate();
                    tilt.setAngle(5);
                    tilt.setPivotX(itemBeingDragged.getWidth() / 2);
                    tilt.setPivotY(itemBeingDragged.getHeight() / 2);

                    itemBeingDragged.getTransforms().add(tilt);
                }
            };

    private EventHandler<MouseEvent> onMouseDragged =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double offsetX = event.getSceneX() - orgSceneX;
                    double newTranslateX = orgTranslateX + offsetX;
                    BorderPane itemBeingDragged = (BorderPane) event.getSource();

                    int itemBeingDraggedIndex = board.getChildren().indexOf(itemBeingDragged);
                    double columnBegin = itemBeingDraggedIndex * 200;
                    double columnEnd = columnBegin + 200;
                    if (event.getSceneX() < columnBegin - 30 || event.getSceneX() > columnEnd + 30) {
                        if (event.getSceneX() < columnBegin - 30) {
                            if(itemBeingDraggedIndex - 1 >= 0) {
                                ObservableList<Node> workingCollection = FXCollections.observableArrayList(board.getChildren());
                                Collections.swap(workingCollection, itemBeingDraggedIndex - 1, itemBeingDraggedIndex);
                                board.getChildren().setAll(workingCollection);
                                orgSceneX = event.getSceneX();
                            }
                        } else {
                            if(itemBeingDraggedIndex + 1 < board.getChildren().size()) {
                                if(itemBeingDraggedIndex != board.getChildren().size() - 2) {
                                    ObservableList<Node> workingCollection = FXCollections.observableArrayList(board.getChildren());
                                    Collections.swap(workingCollection, itemBeingDraggedIndex, itemBeingDraggedIndex + 1);
                                    board.getChildren().setAll(workingCollection);
                                    orgSceneX = event.getSceneX();
                                }
                            }
                        }
                    }
                    if ((event.getSceneX() > 0 && event.getSceneX() < boundX)){
                        if(itemBeingDraggedIndex == board.getChildren().size() - 2){
                            if(offsetX <= 0){
                                itemBeingDragged.setTranslateX(newTranslateX);
                            }
                        }
                        else if(itemBeingDraggedIndex == 0){
                            if(offsetX >= 0){
                                itemBeingDragged.setTranslateX(newTranslateX);
                            }
                        }
                        else {
                            itemBeingDragged.setTranslateX(newTranslateX);
                        }
                    }
                }
            };

    private EventHandler<MouseEvent> onDragOver =
            event -> {
                BorderPane itemBeingDragged = (BorderPane) event.getSource();
                itemBeingDragged.setTranslateX(0);
                itemBeingDragged.setEffect(null);
                itemBeingDragged.getTransforms().clear();
            };
}