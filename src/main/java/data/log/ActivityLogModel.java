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

    public void init()
    {
        for(Change change : changes)
            change.init();
    }

    public void addChange(Change change)
    {
        if(pointer < changes.size() - 1)
        {
            int i = pointer + 1;
            while(i < changes.size())
                changes.remove(i);
        }

        changes.add(change);

        if(pointer == null)
            pointer = 0;
        else
            pointer++;
    }

    public List<Change> getChanges()
    {
        return changes;
    }

    public void redo()
    {
        if(pointer != null && pointer < changes.size() - 1)
        {
            pointer++;

            changes.get(pointer).apply();
        }
    }

    public void undo()
    {
        if(pointer != null && pointer >= 0)
        {
            if(pointer == changes.size())
                pointer--;

            changes.get(pointer).revert();

            pointer--;
        }
    }
}
