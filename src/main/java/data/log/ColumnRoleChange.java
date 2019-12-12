package data.log;

import data.model.ColumnModel;
import static utils.Constants.ColumnRole.*;

public class ColumnRoleChange extends ColumnChange{
    private Constants.ColumnRole prevRole;
    private Constants.ColumnRole newRole;

    public ColumnNameChange(ColumnModel columnModel, Constants.ColumnRole prevRole, Constants.ColumnRole newRole)
    {
        super(columnModel);
        this.prevRole = prevRole;
        this.newRole = newRole;
    }
}
