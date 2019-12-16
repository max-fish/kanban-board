package ui;

import controllers.ActivityLogController;
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXPopup;

import java.io.IOException;

public class ActivityLog extends JFXPopup{
    private ActivityLogController activityLogController;

    public ActivityLog()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/activity_log_popup_ui.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            activityLogController = fxmlLoader.getController();
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public ActivityLogController getController()
    {
        return activityLogController;
    }
}
