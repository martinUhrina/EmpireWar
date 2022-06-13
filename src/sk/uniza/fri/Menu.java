package sk.uniza.fri;


import sk.uniza.fri.builds.IBuild;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Screen/UI mesta
 *
 * @author marti
 */
public class Menu {

    private int choice;
    private String answer;
    private JFrame jFrame;
    private JButton buttonSenate;
    private JButton buttonBarrack;
    private JButton buttonAttack;
    private JButton buttonInfo;
    private JButton buttonAnswer;
    private JButton buttonEndTurn;
    private JLabel textCityName;
    private JLabel textInfo;
    private JLabel textBuilds;
    private JLabel textArmy;
    private JLabel textOfFight;
    private City city;
    private JTextField inputName;
    private int lose = 0;
    private int numberOfAttack;

    /**
     * @param city Mesto s ktorym sa bude pracovat na tomto screene
     */
    public Menu(City city) {
        this.city = city;
    }

    /**
     * Metoda vytvori vsetky buttony, texty, input texty, vsetko co sa ma zobrazit na screene
     */
    public void createUI() {
        this.jFrame = new JFrame("Best game ever");
        this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.jFrame.setSize(600, 700);
        this.jFrame.setResizable(false);
        this.jFrame.setLayout(null);

        this.textCityName = new JLabel(this.city.getCityName());
        this.textCityName.setBounds(150, 30,  150, 30);


        this.textOfFight = new JLabel("");
        if (this.lose == 0) {
            this.textOfFight = new JLabel("");
        } else if (this.lose == 1) {
            this.textOfFight = new JLabel("You LOST the last fight");
        } else {
            this.textOfFight = new JLabel("You Win the last fight");
        }
        this.textOfFight.setBounds(150, 550, 140, 30);
        this.jFrame.add(this.textOfFight);

        this.textBuilds = new JLabel("");
        this.textBuilds.setBounds(10, 410, 130, 150);
        this.jFrame.add(this.textBuilds);

        this.textArmy = new JLabel("");
        this.textArmy.setBounds(140, 410, 150, 150);
        this.jFrame.add(this.textArmy);

        this.textInfo = new JLabel("");
        this.textInfo.setBounds(10, 380, 350, 30);
        this.jFrame.add(this.textInfo);

        this.textCityName.setFont(new Font("Calibri", Font.BOLD, 20));
        this.jFrame.add(this.textCityName);
        this.inputName = new JTextField();
        this.inputName.setBounds(450, 550, 120, 30);
        this.jFrame.add(this.inputName);

        this.numberOfAttack = 0;

        this.createButtons();
        this.enableUI();
    }

    private void setTextArmy() {
        HashMap army = this.city.getUnitsWithCounter();

        String output = "<html>";
        output += "SwordMans    " + army.get("SwordMan") + "<br>";
        output += "Archers      " + army.get("Archer") + "<br>";
        output += "Hoplite      " + army.get("Hoplite") + "<br>";
        output += "HorseMan     " + army.get("HorseMan") + "<br>";

        output += "</html>";
        this.textArmy.setText(output);
    }

    private void setTextBuilds() {
        ArrayList<IBuild> builds = this.city.getBuilds();
        String output = "<html>";
        for (IBuild build: builds) {
            output +=  build.getName() + "    " + build.getLevel() + "<br>";
        }
        output += "</html>";
        this.textBuilds.setText(output);
    }


    private void createButtons() {
        this.buttonAttack = new JButton("Attack");
        this.buttonSenate = new JButton("Senate");
        this.buttonBarrack = new JButton("Barrack");
        this.buttonInfo = new JButton("Info");
        this.buttonAnswer = new JButton("Confirm");
        this.buttonEndTurn = new JButton("End turn");

        this.buttonInfo.setBounds(150, 80, 250, 50);
        this.buttonSenate.setBounds(150, 130, 250, 50);
        this.buttonBarrack.setBounds(150, 180, 250, 50);
        this.buttonAttack.setBounds(150, 300, 250, 50);
        this.buttonAnswer.setBounds(450, 580, 120, 15);
        this.buttonEndTurn.setBounds(450, 620, 120, 15);

        this.buttonInfo.addActionListener(e -> this.buttonInfo());
        this.buttonSenate.addActionListener(e -> this.buttonSenatePressed());
        this.buttonBarrack.addActionListener(e -> this.buttonBarrackPressed());
        this.buttonAttack.addActionListener(e -> this.buttonAttackPressed());
        this.buttonAnswer.addActionListener(e -> this.buttonAnwerPressed());
        this.buttonEndTurn.addActionListener(e -> this.buttonEndTurnPressed());

        this.jFrame.add(this.buttonInfo);
        this.jFrame.add(this.buttonSenate);
        this.jFrame.add(this.buttonBarrack);
        this.jFrame.add(this.buttonAttack);
        this.jFrame.add(this.buttonAnswer);
        this.jFrame.add(this.buttonEndTurn);
    }

    private void buttonEndTurnPressed() {
 //       System.out.println("Button end turn pressed");
        this.city.addMaterials();
        this.jFrame.dispose();
        Singleton singleton = Singleton.getInstance();
        singleton.changeTurn();
    }

    private void buttonAnwerPressed() {
        this.answer = this.inputName.getText();
    //    System.out.println(this.answer);
        if (this.choice == 1) {
            this.city.toImprove(this.answer);
            this.city.toBuild(this.answer);
            this.update();
            this.city.iAmWinner();
        }
        if (this.choice == 2) {
            if (this.city.getLevelBarrack() > 0) {
                this.city.barrack(this.answer);
                this.update();
            }
        }
        if (this.choice == 4) {
            if (this.answer.equals("yes") && this.numberOfAttack == 0) {
                this.numberOfAttack++;
                Singleton singleton = Singleton.getInstance();
                singleton.fight(this.city.getAttackPower());
                this.update();
            }
        }
    }

    private void update() {
        this.textInfo.setText(this.city.getInfoOfCity());
        this.setTextBuilds();
        this.setTextArmy();
    }

    private void buttonInfo() {
        this.choice = 0;
        setColorPressedButton();
        this.textInfo.setText(this.city.getInfoOfCity());

    }

    private void buttonSenatePressed() {
        this.choice = 1;
        setColorPressedButton();
     //   System.out.println("senate");
        this.setTextBuilds();
    }

    private void buttonBarrackPressed() {
        this.choice = 2;
        setColorPressedButton();
  //      System.out.println("barack");
        this.setTextArmy();
    }


    private void buttonAttackPressed() {
        this.choice = 4;
        setColorPressedButton();
   //     System.out.println("attack");
    }



    private void enableUI() {
        try {
            if (this.jFrame != null) {
                this.jFrame.setVisible(true);
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
    //        System.out.println("neni");
        }
    }

    /**
     * Aktualizuje text po poslednom suboji
     * @param newText Novy text
     */
    public void setTextOfFight(String newText) {
        this.textOfFight.setText(newText);
    }

    /**
     * Nastavi ci posledny suboj bol vytazni alebo nie
     * @param winOrLost Nova hodnota
     */
    public void changeBoolTextLastFight(int winOrLost) {
        this.lose = winOrLost;
    }

    private void setColorPressedButton() {
        this.buttonInfo.setBackground(null);
        this.buttonSenate.setBackground(null);
        this.buttonBarrack.setBackground(null);
        this.buttonAttack.setBackground(null);
        if (this.choice == 0) {
            this.buttonInfo.setBackground(Color.gray);
        }
        if (this.choice == 1) {
            this.buttonSenate.setBackground(Color.gray);
        }
        if (this.choice == 2 && this.city.getLevelBarrack() > 0) {
            this.buttonBarrack.setBackground(Color.gray);
        }
        if (this.choice == 4) {
            this.buttonAttack.setBackground(Color.gray);
        }

    }

}

/*
0 - info
1 - senate
2 - kasarne
4 - utok

 */