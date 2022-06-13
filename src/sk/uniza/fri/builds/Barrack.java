package sk.uniza.fri.builds;


/**
 * Kasarne, sluzoa vytvaranie jednotiek
 *
 * @author marti
 */
public class Barrack implements IBuild {

    private int level;

    public Barrack() {

        this.level = 0;
    }


//    public IUnit createUnit(ArrayList<IUnit> unitList) {
//        ArrayList<IUnit> newUnitsToArmy = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the barrack");
//        System.out.println(description());
//        showUnit(unitList);
//        System.out.println("You can create SwordMan, Hoplit or Archer");
//        System.out.println("Your choice: ");
//        String choice = scanner.nextLine();
//        System.out.println("And numbers: ");
//        int numbersToCreate = scanner.nextInt();
//        //TODO
//        return (IUnit)newUnitsToArmy;
//    }

//    private void showUnit(ArrayList<IUnit> unitList) {
//        int countSword = 0;
//        for (IUnit unit: unitList) {
//            if (unit instanceof SwordMan) {
//                countSword++;
//            }
//        }
//        System.out.println("Actual state of army is:");
//        System.out.println("SwordMans: " + countSword);
//    }

    /**
     * @return Vrati opis budovy
     */
    @Override
    public String description() {
        return "This building is used for create new combat unit";
    }

    /**
     * @return Vrati level boduovy
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     * @return Vrati nazov budovy
     */
    @Override
    public String getName() {
        return "Barrack";
    }

    /**
     * zvisi level budovy
     */
    @Override
    public void raiseLevel() {
        this.level++;
    }


    /**
     * @return vrati cestu k suboru
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/BarrackMaterial.txt";
    }
}
