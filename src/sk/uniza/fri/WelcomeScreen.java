package sk.uniza.fri;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Trieda vytvori uvodny screen
 *
 * @author marti
 */
public class WelcomeScreen {

    private JFrame jFrame;
    private JTextField player1;
    private JLabel player1Text;
    private JTextField player2;
    private JLabel player2Text;
    private JLabel text;
    private JButton button;
    private ArrayList<String> names = new ArrayList<>();


    /**
     * Konstruktor, na vytvorenie uvodneho screenu, kde si hraci vyberaju nazvy miest
     */
    public WelcomeScreen() {
        this.jFrame = new JFrame("WELCOME MENU");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(300, 300);
        jFrame.setResizable(false);
        jFrame.setLayout(null);

        this.button = new JButton("Confirm");
        this.button.setBounds(75, 175, 150, 30);

        this.player1Text = new JLabel("Player1 : ");
        this.player1Text.setBounds(15, 75, 60, 30);

        this.text = new JLabel("Please, insert your name of your city");
        this.text.setBounds(15, 30, 250, 30);

        this.player2Text = new JLabel("Player2 : ");
        this.player2Text.setBounds(15, 125, 60, 30);
        this.player1 = new JTextField();
        this.player1.setBounds(75, 75, 150, 30);
        this.player2 = new JTextField();
        this.player2.setBounds(75, 125, 150, 30);

        this.button.addActionListener(e -> buttonWasPressed());

        this.jFrame.add(this.button);
        this.jFrame.add(this.player1Text);
        this.jFrame.add(this.player2Text);
        this.jFrame.add(this.player1);
        this.jFrame.add(this.player2);
        this.jFrame.add(this.text);

        try {
            if (this.jFrame != null) {
                this.jFrame.setVisible(true);
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
     //       System.out.println("neni");
        }
    }

    private void buttonWasPressed() {
  //      System.out.println("MOre bol som stlaceny");
        getNames();
        Singleton singleton = Singleton.getInstance();
        singleton.setUpNames(this.names.get(0), this.names.get(1));
        singleton.playGame();
    }

    /**
     * Pridanie nazvov miest do arrayListu a zatvorenie screenu
     */
    public void getNames() {
        this.names.add(this.player1.getText());
        this.names.add(this.player2.getText());
        this.jFrame.dispose();
    }
}
