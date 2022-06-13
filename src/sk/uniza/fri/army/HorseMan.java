package sk.uniza.fri.army;

/**
 * Jazdec na koni, utocna jednotka
 *
 * @author marti
 */
public class HorseMan implements IUnit {

    private int power = 55;

    /**
     * @return vrati nazov jednotky
     */
    @Override
    public String getName() {
        return "HorseMan";
    }

    /**
     * @return vrati cestu k suboru
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/HorseManMaterial.txt";
    }

    /**
     * @return Vrati silu jednotky
     */
    public int getPower() {
        return power;
    }
}
