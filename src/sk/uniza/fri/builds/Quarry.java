package sk.uniza.fri.builds;



/**
 * Kamenolom, sluzi na vyrobu kamena
 *
 * @author marti
 */
public class Quarry extends BuildForMining implements IBuild {

    /**
     * Konstruktor
     */
    public Quarry() {
        super();
        super.setName("Quarry");
    }

    /**
     * @return Vrati opis budovi
     */
    @Override
    public String description() {
        return null;
    }

    /**
     * @return Vrati level budovy
     */
    @Override
    public int getLevel() {
        return super.getLevel();
    }

    /**
     * Zvisi level budovy o 1
     */
    @Override
    public void raiseLevel() {
        super.setLevel(super.getLevel() + 1);
    }

    /**
     * @return Vrati cestu k suboru pre tuto budovu
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/QuarryMaterial.txt";
    }

}
