package data.log;

public interface Change{
//    public void apply();
    public void revert();

    @Override
    public String toString();
}
