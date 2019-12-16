package data.log;

public abstract class Change{
    protected boolean applied;

    public Change()
    {
        applied = true;
    }

    public boolean isApplied()
    {
        return applied;
    }

    public abstract void apply();
    public abstract void revert();
    public abstract void init();

    @Override
    public abstract String toString();
}
