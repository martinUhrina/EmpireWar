package sk.uniza.fri.army;

/**
 * Bojovnik s mecom, obranna jednotka
 *
 * @author marti
 */
public class SwordMan implements IUnit {

    private int power = 18;


    /**
     * @return Vrati cestu k suboru
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/SwordManMaterial.txt";
    }

    /**
     * @return Vrati nazov jednotky
     */
    @Override
    public String getName() {
        return "SwordMan";
    }

    /**
     * @return Vrati silu jednotky
     */
    public int getPower() {
        return power;
    }
}
