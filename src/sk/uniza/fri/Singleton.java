package sk.uniza.fri;

import javax.swing.*;
import java.awt.*;

/**
 * SLuzi ako battleManger, stara sa riadenie hry
 *
 * @author marti
 */
class Singleton {

    private static Singleton singleInstance = null;
    private int playerTurnID = 0;
    private int round;
    private boolean gameOver = true;
    private Player player1;
    private Player player2;

    /**
     * @return Vrati instanciu singletona
     */
    public static Singleton getInstance() {
        if (singleInstance == null) {
            singleInstance = new Singleton();
        }
        return singleInstance;
    }

    private int getPlayerTurnID() {
        return this.playerTurnID;
    }

    /**
     * Sluzi na zmenu medzi hracmi
     */
    public void changeTurn() {
   //     System.out.println("make me happy");
        this.round++;
//        System.out.println(this.round);
        if (this.playerTurnID == 0) {
            this.playerTurnID = 1;
        } else {
            this.playerTurnID = 0;
        }
        this.playGame();
    }

    /**
     * Metoda spusta hru
     */
    public void playGame() {
        if (gameOver) {
            if (this.getPlayerTurnID() == 0) {
                this.player1.showMenu();
            } else {
                this.player2.showMenu();
            }
        }
    }

    /**
     * Metoda sa vyuziva pri boji ako BattleManager
     * @param attackPower Utocna sila utocnika
     */
    public void fight(int attackPower) {
        if (this.round >= 6) {
            int defensivePower;
            if (this.playerTurnID == 0) {
                defensivePower = this.player2.getDefensivePower();
            } else {
                defensivePower = this.player1.getDefensivePower();
            }
//            System.out.println("Attack: " + attackPower);
//            System.out.println("Defensive: " + defensivePower);
            double dispersion;
            if (attackPower > defensivePower) {
                dispersion = (double)defensivePower / (double)attackPower;
                if (this.playerTurnID == 0) {
                    this.player1.takeUnit(1 - dispersion);
                    this.player2.takeUnit(0);
                    this.player1.addLoot(this.player2.giveLoot());
                } else {
                    this.player1.takeUnit(0);
                    this.player2.takeUnit(1 - dispersion);
                    this.player2.addLoot(this.player1.giveLoot());
                }
            } else {
                dispersion = (double)attackPower / (double)defensivePower;
                if (this.playerTurnID == 0) {
                    this.player1.takeUnit(0);
                    this.player1.changeTextOfFinght(1);
                    this.player2.takeUnit(1 - dispersion);
                    this.player2.changeTextOfFinght(2);
                } else {
                    this.player1.takeUnit(1 - dispersion);
                    this.player1.changeTextOfFinght(2);
                    this.player2.takeUnit(0);
                    this.player2.changeTextOfFinght(1);
                }

            }
        }
    }

    /**
     * Ak je niekto vytaz, zavola tuto metodu
     * @param cityName Nazov mesta, ktore vyhralo
     */
    public void gameOver(String cityName) {
        this.gameOver = false;
        JFrame jFrame = new JFrame("WINNER");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600, 700);
        jFrame.setResizable(false);
        jFrame.setLayout(null);

        JLabel winner = new JLabel("WINNER IS " + cityName);
        winner.setBounds(185, 175, 300, 150);
        winner.setFont(new Font("Verdana", Font.PLAIN, 25));
        jFrame.add(winner);

        try {
            if (jFrame != null) {
                jFrame.setVisible(true);
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("neni");
        }
    }

    /**
     * Nastavuje nazvy miest
     * @param s nazov mesta player1
     * @param s1 nazov mesta player2
     */
    public void setUpNames(String s, String s1) {
        this.player1 = new Player(s);
        this.player2 = new Player(s1);
    }
}
