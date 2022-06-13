package sk.uniza.fri.builds;

/**
 * rodicovska trieda pre budovy na tazbu
 *
 * @author marti
 */
public abstract class BuildForMining {

    private int level;
    private int extractionOfProduct = 100;
    private String name;

    /**
     * Konstructor
     */
    public BuildForMining() {
        this.level = 1;
    }
//
//    public int getExtractionOfProduct() {
//        return this.extractionOfProduct;
//    }
//
//    public int pridaj() {
//        return this.extractionOfProduct;
//    }

    /**
     * @return Vrati level budovy
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * @return Vrati nazov budovy
     */
    public String getName() {
        return this.name;
    }

    /**
     * nastavy novy level boduvy
     * @param newLevel novy level
     */
    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

//    public void setExtractionOfProduct(int newValue) {
//        this.extractionOfProduct = newValue;
//    }

    /**
     * Nastavi novy nazov
     * @param newName novy Nazov
     */
    public void setName(String newName) {
        this.name = newName;
    }
}
