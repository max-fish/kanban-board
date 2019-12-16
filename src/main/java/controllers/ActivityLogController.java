package controllers;

import data.model.BoardModel;
import data.log.ActivityLogModel;
import data.log.Change;
import utils.GUIMaker;

import com.google.common.collect.Lists;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXPopup;

public class ActivityLogController{
    @FXML
    private VBox logContainer;

    private ActivityLogModel activityLogModel;

    public void setActivityLogModel(ActivityLogModel activityLogModel)
    {
        this.activityLogModel = activityLogModel;
    }

    public void fillWithContent()
    {
        logContainer.getChildren().clear();

        for(Change change : Lists.reverse(activityLogModel.getChanges()))
            logContainer.getChildren().add(GUIMaker.makeChangeLabel(change));
    }

    @FXML
    public void undo()
    {
        activityLogModel.undo();
        fillWithContent();
    }

    @FXML
    public void redo()
    {
        activityLogModel.redo();
        fillWithContent();
    }
}
