package data.log;

public interface Change{
//    public void apply();
    public void revert();
    public void init();

    @Override
    public String toString();
}
