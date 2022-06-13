package sk.uniza.fri.builds;


/**
 * Hradby, pridavaju obranny bonus
 *
 * @author marti
 */
public class Walls implements IBuild {

    private int level = 0;

    public int getDeffPower() {
        return this.level * 2;
    }

    /**
     * @return Vrati opis budovy
     */
    @Override
    public String description() {
        return "This build increase your defensive power";
    }

    /**
     * @return Vrati aktualny level budovy
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     * @return  Vrati nazov budovy
     */
    @Override
    public String getName() {
        return "Walls";
    }

    /**
     * Zvysi level budovy o 1
     */
    @Override
    public void raiseLevel() {
        this.level++;
    }

    /**
     * @return  Vrati cestu k suberu pre tuto budovu
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/SenateMaterial.txt";
    }

}
