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

    public void revert()
    {

    }

    public String toString()
    {
        return columnModel.getName() + " column role changed to " + newRole.roleString;
    }
}
