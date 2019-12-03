package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Collections;

public class DragAndDropForCards {
    private double orgSceneY;
    private double orgTranslateY;
    private VBox column;
    private double boundX;
    private double boundY;
    private double columnHeaderOffset;

    public void setDragAnimation(BorderPane rootPane, BorderPane columnContainer) {
        this.column = (VBox) columnContainer.getCenter();
        columnHeaderOffset = ((VBox) columnContainer.getTop()).getHeight();
        boundY = column.getChildren().size() * (rootPane.getHeight()) + columnHeaderOffset + 200;

        rootPane.setOnMousePressed(onMousePressed);
        rootPane.setOnMouseDragged(onMouseDragged);
        rootPane.setOnMouseReleased(onDragOver);
    }

    private EventHandler<MouseEvent> onMousePressed =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    BorderPane itemBeingDragged = (BorderPane) event.getSource();
                    orgSceneY = event.getSceneY();
                    orgTranslateY = itemBeingDragged.getTranslateY();
                    boundY = column.getChildren().size() * (itemBeingDragged.getHeight()) + columnHeaderOffset + 200;
                }
            };
    private EventHandler<MouseEvent> onMouseDragged =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double offsetY = event.getSceneY() - orgSceneY;
                    double newTranslateY = orgTranslateY + offsetY;
                    BorderPane itemBeingDragged = (BorderPane) event.getSource();

                    int itemBeingDraggedIndex = column.getChildren().indexOf(itemBeingDragged);
                    double cardBegin = itemBeingDraggedIndex * itemBeingDragged.getHeight() + columnHeaderOffset;
                    double cardEnd = cardBegin + itemBeingDragged.getHeight();

                    if (event.getSceneY() < cardBegin|| event.getSceneY() > cardEnd) {
                        if (event.getSceneY() < cardBegin) {
                            if (itemBeingDraggedIndex - 1 >= 0) {
                                ObservableList<Node> workingCollection = FXCollections.observableArrayList(column.getChildren());
                                Collections.swap(workingCollection, itemBeingDraggedIndex - 1, itemBeingDraggedIndex);
                                column.getChildren().setAll(workingCollection);
                                orgSceneY = event.getSceneY();
                            }
                        } else if (event.getSceneY() > cardEnd) {
                            System.out.println("pos: " + event.getSceneY());
                            System.out.println("cardEnd: " + cardEnd);
                            if (itemBeingDraggedIndex + 1 < column.getChildren().size()) {
                                ObservableList<Node> workingCollection = FXCollections.observableArrayList(column.getChildren());
                                Collections.swap(workingCollection, itemBeingDraggedIndex, itemBeingDraggedIndex + 1);
                                column.getChildren().setAll(workingCollection);
                                orgSceneY = event.getSceneY();
                            }
                        }
                    }
                    //System.out.println("location: " + event.getSceneY());
                    if ((event.getSceneY() > 0 && event.getSceneY() < boundY)) {
                        if (itemBeingDraggedIndex == 0) {
                            if (offsetY >= 0) {
                                itemBeingDragged.setTranslateY(newTranslateY);
                            }
                        } else if (itemBeingDraggedIndex == column.getChildren().size() - 1) {
                            if (offsetY <= 0) {
                                itemBeingDragged.setTranslateY(newTranslateY);
                            }
                        } else {
                            itemBeingDragged.setTranslateY(newTranslateY);
                        }
                    }
                }
            };

    private EventHandler<MouseEvent> onDragOver =
            event -> {
                BorderPane itemBeingDragged = (BorderPane) event.getSource();
                itemBeingDragged.setTranslateY(0);
                itemBeingDragged.setEffect(null);
                itemBeingDragged.getTransforms().clear();
            };
}
