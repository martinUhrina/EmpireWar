package sk.uniza.fri.builds;



/**
 * Interface budov
 *
 * @author marti
 */
public interface IBuild {
    String description();
    int getLevel();
    String getName();
    void raiseLevel();
    String getPath();
}
