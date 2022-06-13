package sk.uniza.fri.builds;


/**
 * Drevorubac, pridava drevo do mesta
 *
 * @author marti
 */
public class WoodCutter extends BuildForMining implements IBuild {


    /**
     * Konstructor, nastavenie nazvu budovy
     */
    public WoodCutter() {
        super();
        super.setName("WoodCutter");
    }

    /**
     * Zvysi aktualny level o 1
     */
    @Override
    public void raiseLevel() {
        super.setLevel(super.getLevel() + 1);
    }

    /**
     * @return Vrati cestu k subory, pre tuto budovu
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/WoodCutterMaterial.txt";
    }

    /**
     * @return Vrati opis budovy
     */
    @Override
    public String description() {
        return null;
    }

    /**
     * @return Vrati aktualny level budovy
     */
    @Override
    public int getLevel() {
        return super.getLevel();
    }


}
