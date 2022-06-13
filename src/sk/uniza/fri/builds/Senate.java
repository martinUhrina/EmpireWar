package sk.uniza.fri.builds;

import java.util.ArrayList;


/**
 * Senat, sluzi na stavanie budov
 *
 * @author marti
 */
public class Senate implements IBuild {

    private int level;
    private ArrayList<IBuild> buildsToBuilding = new ArrayList<>();


    /**
     * Konstruktor
     */
    public Senate() {
        this.level = 1;
        this.buildsToBuilding.add(new Barrack());
        this.buildsToBuilding.add(new Walls());
    }

    /**
     * @return Vrati pocet nepostavanych budov
     */
    public int getNumbersOfBuild() {
        return this.buildsToBuilding.size();
    }

    /**
     * Metoda na vylepovanie budov
     * @param builds zoznam postavanych budov
     * @param answer nazov budovy, ktora sa ma postavit
     * @return Vrati budovu, ktora sa vylepsila
     */
    public IBuild canImprove(ArrayList<IBuild> builds, String answer) {
   //     Scanner scan = new Scanner(System.in);
   //     getListBuild(builds);
    //    System.out.println("Write your choice: ");
    //    String choice = scan.nextLine();
        String choice = answer;
        for (IBuild build: builds) {
            if (build.getName().equals(choice)) {
                return build;
            }
        }
        return null;
    }

//    private void getListBuild(ArrayList<IBuild> builds) {
//        for (IBuild build: builds) {
//            System.out.println(build.getName() + "    " + build.getLevel());
//        }
//        System.out.println();
//    }

    /**
     * sluzi na vypis budov ktore nie su postavene do terminalu
     */
//    public void writeListOfBuildToBuilding() {
//        for (int i = 0; i < this.buildsToBuilding.size(); i++) {
//   //         System.out.println(this.buildsToBuilding.get(i).getName() + "    " + this.buildsToBuilding.get(i).getLevel());
//        }
//    }

    /**
     * @return Vrati zoznam nepostavanych budov
     */
    public ArrayList<IBuild> getListOfBuildToBuilding() {
        return (ArrayList<IBuild>)this.buildsToBuilding;
    }

    /**
     * Metoda na odsranenie budovy zo zoznamu na stavanie
     * @param build Budova, ktora sa ma odstranit
     */
    public void removeBuild(IBuild build) {
        for (int i = 0; i < this.buildsToBuilding.size(); i++) {
            if (build == this.buildsToBuilding.get(i)) {
                this.buildsToBuilding.remove(i);
                break;
            }
        }
    }

    /**
     * @return Vrati opis budovy
     */
    @Override
    public String description() {
        return "In this build, you can increase you others builds";
    }

    /**
     * @return Vrati level budovy
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
        return "Senate";
    }

    /**
     * Zvisi level budovy o 1
     */
    @Override
    public void raiseLevel() {
        this.level++;
    }

    /**
     * @return Vrati cestu k suboru pre tuto budovu
     */
    @Override
    public String getPath() {
        return "./src/sk/uniza/fri/txtFile/SenateMaterial.txt";
    }


}
