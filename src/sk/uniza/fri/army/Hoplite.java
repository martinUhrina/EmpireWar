package sk.uniza.fri.army;

/**
 * Hoplit, utocna jednotka
 *
 * @author marti
 */
public class Hoplite implements IUnit {

    private int power = 25;


    /**
     * @return vrati nazov jednotky
     */
    @Override
    public String getName() {
        return "Hoplite";
    }

    /**
     * @return vrati cestu k suboru
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/HopliteMaterial.txt";
    }

    /**
     * @return Vrati silu jednotky
     */
    public int getPower() {
        return power;
    }
}
