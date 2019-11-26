package utils;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import ui.KanbanColumn;

import java.util.Collections;

public class AnimationMaker {

    public static void playAnimations(Animation... animations) {
        ParallelTransition parallelTransition = new ParallelTransition(animations);
        parallelTransition.play();
    }

    public static TranslateTransition makeAddColumnSlideInAnimation(Node toAnimate) {
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(300));
        slideIn.setNode(toAnimate);
        slideIn.setFromX(-200);
        slideIn.setToX(0);
        return slideIn;
    }

    public static ParallelTransition makeDeleteColumnParallelAnimation(HBox columns, KanbanColumn column) {
        ParallelTransition parallelTransition = null;
        int indexOfDeletion = columns.getChildren().indexOf(column);
        if (indexOfDeletion < columns.getChildren().size() - 1) {
            parallelTransition = new ParallelTransition();
            for (int i = indexOfDeletion + 1; i < columns.getChildren().size(); i++) {
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300));
                translateTransition.setNode(columns.getChildren().get(i));
                translateTransition.setFromX(200);
                translateTransition.setToX(0);
                parallelTransition.getChildren().add(translateTransition);
            }
        }
        return parallelTransition;
    }
}
