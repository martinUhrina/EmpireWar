package sk.uniza.fri.builds;


/**
 * Zelezna bana, sluzi na vyrobu zeleza
 *
 * @author marti
 */
public class IronMine extends BuildForMining implements IBuild {

    /**
     * Konstruktor
     */
    public IronMine() {
        super();
        super.setName("Iron Mine");
    }

    /**
     * @return Vrati opis budovy
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
        return "./src/sk/uniza/fri/txtFile/IronMineMaterial.txt";
    }

}
