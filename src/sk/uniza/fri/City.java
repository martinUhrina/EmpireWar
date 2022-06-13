package sk.uniza.fri;

import sk.uniza.fri.army.*;
import sk.uniza.fri.builds.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * reprezentuje hracovo mesto, sluzi na vsetky operacie tykajuce sa mesta
 *
 * @author marti
 */
public class City {

    private ArrayList<IBuild> builds = new ArrayList<>();
    private ArrayList<IUnit> units = new ArrayList<>();
    private HashMap<String, Integer> unitsWithCount = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private int defenseBonus = 0;
    private int wood;
    private int stone;
    private int iron;
    private String cityName;
    private int attackPower;
    private int defensePower;
    private boolean attackOrDef = false;

    /**
     * Konstruktor, vytvori sa mesto, postavia zakladne budovy a pridaju suroviny
     * @param cityName = nazov mesta
     */
    public City(String cityName) {
        this.builds.add(new Senate());
        this.builds.add(new WoodCutter());
        this.builds.add(new Quarry());
        this.builds.add(new IronMine());
        this.units.add(new SwordMan());
        this.units.add(new Archer());
        this.units.add(new Hoplite());
        this.units.add(new HorseMan());
        this.unitsWithCount.put("SwordMan", 1);
        this.unitsWithCount.put("Archer", 0);
        this.unitsWithCount.put("Hoplite", 0);
        this.unitsWithCount.put("HorseMan", 0);
        this.wood = 300;
        this.stone = 300;
        this.iron = 300;
        this.cityName = cityName;
    }


    /**
     *Metoda sluzi na vytvorenie bojovej jednotky
     * @param answer = sluzi ako odpoved, aka jednotka sa ma vytvorit
     *
     */
    public void barrack(String answer) {
        for (IUnit unit: this.units) {                                      //hlada jednotku podla odpovede-answer
            if (answer.equals(unit.getName())) {                            //ak sa rovnaju nazvy - true
                File file = new File(unit.getPath());                       //otvori sa subor pre konkretny typ jednotky
                ArrayList<Integer> list = new ArrayList<>();                //list pre zoznam surovin
                Scanner scannerForParse = null;
                try {
                    scannerForParse = new Scanner(file);                    //vytvorenie scannera
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                list.add(Integer.valueOf(scannerForParse.nextLine()));         //pridanie potrebnych surovin
                list.add(Integer.valueOf(scannerForParse.nextLine()));
                list.add(Integer.valueOf(scannerForParse.nextLine()));
                if (minusMaterials(list)) {                       //kontrola ci je mozne jednotku vytvorit, ak ano uberu sa suroviny
                    Integer count;
                    count = this.unitsWithCount.get(unit.getName());    //stary pocet jednotiek
                    this.unitsWithCount.remove(unit.getName());         //odstranenie streho poctu jednotiek
                    this.unitsWithCount.put(unit.getName(), count + 1); //pridanie noveho poctu jednotiek do HashMapy
                }
                break;
            }
        }
    }


//    public void senate() {
//        System.out.println("Do you want buld or Improve build? (B / I)" );
//
//        String answer = scanner.nextLine();
//        if (answer.equals("I") || answer.equals("i")) {
//            toImprove("");
//        } else if (answer.equals("B") || answer.equals("b")) {
//            toBuild("");
//        } else {
//            System.out.println("Nespravna operacia");
//        }
//    }

    /**
     * Metoda sluzi na postavenie budovy, ktora este nepobala postavena
     * @param answer  = sluzi ako odpoved, aka budova sa ma postavit
     */
    public void toBuild(String answer) {
//        if (0 == getSenat().getNumbersOfBuild()) {
//            System.out.println("All building was sucesfully built");
//            return;
//        }
        Senate senate = getSenat();
  //         senate.writeListOfBuildToBuilding();
  //      System.out.println("Your choice: ");
       // String toBuild = scanner.nextLine();
        String toBuild = answer;
        ArrayList<IBuild> help = senate.getListOfBuildToBuilding();
        for (int i = 0; i < help.size(); i++) {
            if (help.get(i).getName().equals(toBuild)) {
                ArrayList<Integer> list = canRaise(this.builds.get(i));
                if (list != null) {
                    if (minusMaterials(list)) {
                        help.get(i).raiseLevel();
                        builds.add(help.get(i));
                        senate.removeBuild(help.get(i));
                        break;
                    }
                }

            }
        }
    }


    /**
     * Metoda sluzi na vylepsenie uz existujucej budovy
     * @param answer = odpoved, ktora budova sa ma vylepsit
     */
    public void toImprove(String answer) {
        IBuild improBuild = null;                                           //vytvorenie premennej, do ktorej sa ulozi vylepsovana budova
        for (IBuild build:this.builds) {                                         //foreach, pre najdenie senatu
            if (build instanceof Senate) {                                  //hladanie senatu
                improBuild = ((Senate)build).canImprove(builds, answer);       //overenie ci je moznu zadanu budovu vylepsit, ak ano da sa do premmenj improBuild
                break;
            }
        }
        if (improBuild != null) {                                           //ak sa na budova nasla vykona sa kod v if
            for (int i = 0; i < this.builds.size(); i++) {                  //hladanie budovy v zozname postavenych budov meste
                if (this.builds.get(i).getName().equals(improBuild.getName())) {        //ak sa nazvy budov zhoduju, vykona sa if
                    ArrayList<Integer> list = canRaise(this.builds.get(i));     //canRaise, ak sa budova moze vylepsit vrati list surovin ktore treba na stavbu
                    if (list != null) {                                         //ak nevrati null
                        if (minusMaterials(list)) {                             //minusMaterial skontroluje ci je dost surovin a ak ano odoberie ich
                            this.builds.get(i).raiseLevel();                    //zvisi level budove, ktora sa vylepsuje
                            return;
                        }
                    }
                }
                if (improBuild instanceof Walls) {                              //ak vylepsovana budova Hradby, zvisi sa aj obranny bonus
                    updateDefenseBonus();
                }
            }
        }
    }

    /**
     * Ak je dostatok surovin v meste, opdocitaju sa suroviny
     * @param list = obsahu pocet surovin, ktore treba na operaciu
     * @return = vrati ci je dostatok surovin na operaciu
     */
    private boolean minusMaterials(ArrayList<Integer> list) {
        if (this.wood - list.get(0) > 0 && this.stone - list.get(1) > 0 && this.iron - list.get(2) > 0) {
            this.wood -= list.get(0);
            this.stone -= list.get(1);
            this.iron -= list.get(2);
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<Integer> canRaise(IBuild iBuild) {
        File file = new File(iBuild.getPath());
        try {
            Scanner scannerForParse = new Scanner(file);
            while (scannerForParse.hasNextLine()) {
                if (scannerForParse.nextLine().equals(iBuild.getLevel() + ".")) {
                    int woodToMinus = Integer.parseInt(scannerForParse.nextLine());
                    int stoneToMinus = Integer.parseInt(scannerForParse.nextLine());
                    int ironToMine = Integer.parseInt(scannerForParse.nextLine());
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(woodToMinus);
                    list.add(stoneToMinus);
                    list.add(ironToMine);
                    scannerForParse.close();
                    return list;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            scanner.close();
            return null;
        }
        scanner.close();
        return null;
    }

    /**
     *
     * @return Vrati zoznam budou v meste
     */
    public ArrayList<IBuild> getBuilds() {
        return this.builds;
    }

    /**
     * @return vrati senat zo zoznamu
     */
    public Senate getSenat() {
        for (IBuild build: this.builds) {
            if (build instanceof Senate) {
                return (Senate)build;
            }
        }
        return null;
    }

    private void updateDefenseBonus() {
        for (IBuild build: this.builds) {
            if (build instanceof Walls) {
                this.defenseBonus = ((Walls)build).getDeffPower();
            }
        }
    }

    /**
     * Kontroluje ci som vytaz, ak su vsetky budovy postavene na maximum
     */
    public void iAmWinner() {
        int counter  = 0;
        for (IBuild build: this.builds) {
            if (build.getLevel() == 7) {
                counter++;
            }
        }
        if (counter == this.builds.size()) {
            Singleton singleton = Singleton.getInstance();
            singleton.gameOver(this.cityName);
        }
    }


    /**
     * @return metoda vrati aktualne info o meste
     */
    public String getInfoOfCity() {
//        for (IBuild build: builds) {
//            System.out.println("  " + build.getName() + "    " + build.getLevel());
//        }
//        System.out.println("  Wood: " + getWood());
//        System.out.println("  Stone: " + getStone());
//        System.out.println("  Iron: " + getIron());
//        System.out.println();
//        System.out.println("Defensive bonus: " + this.defenseBonus);
        return "    Wood: " + getWood()  + "  Stone: " + getStone() +  "  Iron: " + getIron() + "  Defensive Bonus: " + this.defenseBonus;
    }

    /**
     * @return Vrati aktualny level budovy Barrack, sluzi na kontrolu ci uz sa mozu stavat jednotky
     */
    public int getLevelBarrack() {
        for (IBuild build: this.builds) {
            if (build instanceof Barrack) {
                return build.getLevel();
            }
        }
        return 0;
    }

    /**
     * Na zaciatku kola prida hracovi suroviny podla urovne budov na tazbu
     */
    public void addMaterials() {

        this.wood += 50  * this.getMiningBuld("WoodCutter");
        this.stone += 50 * this.getMiningBuld("Quarry");
        this.iron += 50 * this.getMiningBuld("Iron Mine");
    }

    private int getMiningBuld(String buildName) {
        for (IBuild build:this.builds) {
            if (build.getName().equals(buildName)) {
                return build.getLevel();
            }
        }
        return 0;
    }

    /**
     * @return Vrati hashmapu s poctom jednotiek
     */
    public HashMap getUnitsWithCounter() {
        return this.unitsWithCount;
    }
//
//    public ArrayList getUnits() {
//        return this.units;
//    }

    private int getWood() {
        return wood;
    }

    private int getStone() {
        return stone;
    }

    private int getIron() {
        return iron;
    }

    /**
     * @return Vrati nazov mesta
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @return Vrati utocnu silu mesta
     */
    public int getAttackPower() {
        setAttackPower();
        this.attackOrDef = true;
        return attackPower;
    }

    /**
     * @return Vrati obrannu silu mesta
     */
    public int getDefensePower() {
        setDefensePower();
        this.attackOrDef = false;
        return defensePower;
    }

    /**
     * Nastavi utocnu silu mesta
     */
    public void setAttackPower() {
        this.attackPower = this.unitsWithCount.get("Hoplite") * new Hoplite().getPower() + this.unitsWithCount.get("HorseMan") * new HorseMan().getPower();
    }

    /**
     * Nastavi obrannu silu mesta
     */
    public void setDefensePower() {
        this.defensePower = this.unitsWithCount.get("Archer") * new Archer().getPower() + this.unitsWithCount.get("SwordMan") * new SwordMan().getPower();
        this.defensePower += (this.defenseBonus * 5);
    }

    /**
     * Metoda zluzi po boji, aby vygenerovala nove pocti jednotiek, ktore zostali po boji
     * @param dispersion Sluzi na vypocet prezitych jednotiek
     */
    public void takeUnit(double dispersion) {
        if (this.attackOrDef) {
            int hoplites = this.unitsWithCount.get("Hoplite");
            int horseMans = this.unitsWithCount.get("HorseMan");
            hoplites = (int)(hoplites * dispersion);
            horseMans = (int)(horseMans * dispersion);
            this.unitsWithCount.replace("Hoplite", hoplites);
            this.unitsWithCount.replace("HorseMan", horseMans);
        } else {
            int archers = (int)(this.unitsWithCount.get("Archer") * dispersion);
            int swordMans = (int)(this.unitsWithCount.get("SwordMan") * dispersion);
            this.unitsWithCount.replace("Archer", archers);
            this.unitsWithCount.replace("SwordMan", swordMans);
        }
    }

    /**
     * @return Vrati 50% surovin z mesta, pri rabovani
     */
    public ArrayList<Integer> giveLoot() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(this.wood / 2);
        this.wood = this.wood / 2;
        list.add(this.stone / 2);
        this.stone = this.stone / 2;
        list.add(this.iron / 2);
        this.iron = this.iron / 2;
        return list;
    }

    /**
     * @param listOfMaterials Vrati aktualny pocet surovin v meste
     */
    public void addLoot(ArrayList<Integer> listOfMaterials) {
        this.wood += listOfMaterials.get(0);
        this.stone += listOfMaterials.get(1);
        this.iron += listOfMaterials.get(2);
    }
}
