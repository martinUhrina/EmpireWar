package sk.uniza.fri;

import java.util.ArrayList;

/**
 * reprezentuje hraca, ktory ma mesto a menu
 *
 * @author marti
 */
public class Player {

    private City city;
    private Menu menu;

    /**
     * Konstruktor nasaty mesto a menu pre hraca
     * @param cityName Nazov mesta
     */
    public Player (String cityName) {
        this.city = new City(cityName);
        this.menu = new Menu(this.city);
    }

    /**
     * vytvori UI pre hraca
     */
    public void showMenu() {
        this.menu.createUI();
    }

    /**
     * @return Vrati obrannu silu hraca
     */
    public int getDefensivePower() {
        return this.city.getDefensePower();
    }

    /**
     * sluzi na odobratie jednotiek po boji
     * @param dispersion Pomer strat
     */
    public void takeUnit(double dispersion) {
        this.city.takeUnit(dispersion);
        if (dispersion == 0) {
            this.menu.setTextOfFight("You LOSE the last fight");
        }
    }


    /**
     * Ak vyhra suboj, metoda prida novy material do mesta
     * @param listOfMaterials Novy material
     */
    public void addLoot(ArrayList<Integer> listOfMaterials) {
        this.menu.changeBoolTextLastFight(2);
        this.menu.setTextOfFight("You WIN the last fingt");
        this.city.addLoot(listOfMaterials);
    }

    /**
     * sluzi na zmenu textu, ci vyhral alebo nie
     * @param value Nova hodnota
     */
    public void changeTextOfFinght(int value) {
        this.menu.changeBoolTextLastFight(value);
    }

    /**
     * @return Vrati suroviny o ktore prisiel
     */
    public ArrayList<Integer> giveLoot() {
        this.menu.changeBoolTextLastFight(1);
        return this.city.giveLoot();
    }
}