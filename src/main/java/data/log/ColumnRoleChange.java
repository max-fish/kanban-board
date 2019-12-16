package data.log;

import data.model.ColumnModel;
import utils.Constants;
import static utils.Constants.ColumnRole.*;

public class ColumnRoleChange extends ColumnChange{
    private Constants.ColumnRole prevRole;
    private Constants.ColumnRole newRole;

    public ColumnRoleChange(ColumnModel columnModel, Constants.ColumnRole prevRole, Constants.ColumnRole newRole)
    {
        super(columnModel);
        this.prevRole = prevRole;
        this.newRole = newRole;
    }

    public void apply()
    {
        if(applied)
            return;

        columnModel.setRole(newRole);
        columnModel.getGUI().getController().getRoleButton().setText(newRole.roleString);
        applied = true;
    }

    public void revert()
    {
        if(!applied)
            return;

        columnModel.setRole(prevRole);
        columnModel.getGUI().getController().getRoleButton().setText(prevRole.roleString);
        applied = false;
    }

    public String toString()
    {
        return columnModel.getName() + " column role changed to " + newRole.roleString;
    }
}
