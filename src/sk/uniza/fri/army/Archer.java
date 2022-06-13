package sk.uniza.fri.army;

/**
 * Lukostrelec, obranna jednotka
 *
 * @author marti
 */
public class Archer implements IUnit {

    private int power = 22;


    /**
     * @return vrati nazov jednotky
     */
    @Override
    public String getName() {
        return "Archer";
    }

    /**
     * @return vrati cestu k suboru
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/ArcherMaterial.txt";
    }

    /**
     * @return Vrati silu jednotky
     */
    public int getPower() {
        return power;
    }
}
