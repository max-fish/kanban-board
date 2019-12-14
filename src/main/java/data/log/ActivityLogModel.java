package data.log;

import java.util.List;
import java.util.ArrayList;

public class ActivityLogModel{
    private List<Change> changes;
    private Integer pointer;

    public ActivityLogModel()
    {
        changes = new ArrayList<>();
        pointer = null;
    }

    public void addChange(Change change)
    {
        changes.add(change);

        if(pointer == null)
            pointer = 0;
        else
            pointer++;
    }

    /*public void redo(int changesToRedo)
    {
        while(changesToRedo > 0 && pointer < changes.size())
    }*/

    public List<Change> getChanges()
    {
        return changes;
    }

    public void undo()
    {
        if(pointer > 0)
        {
            changes.get(pointer).revert();
            pointer--;
        }
    }
}
