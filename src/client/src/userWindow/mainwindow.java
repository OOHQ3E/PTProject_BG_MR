package userWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainwindow extends JFrame{
    private JPanel mainIguess;
    private JSpinner nud_Y;
    private JSpinner nud_X;
    private JButton paintButton;
    private JButton logOutButton;
    private JSpinner nud_red;
    private JSpinner nud_green;
    private JSpinner nud_blue;
    public Draw draw = new Draw();
    public mainwindow(){
        setContentPane(mainIguess);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Window");
        setSize(650,350);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1,0,0));
        add(draw);
        setVisible(true);

        setUpButtonListeners();
    }
    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == paintButton){
                    Draw.pixels[(int) nud_Y.getValue()][(int) nud_X.getValue()]
                            = new Color((int)nud_red.getValue(),(int)nud_green.getValue(),(int)nud_blue.getValue());
                    System.out.println(Draw.pixels[(int) nud_Y.getValue()][(int) nud_X.getValue()]);
                    System.out.println((int) nud_Y.getValue()+","+(int) nud_X.getValue());
                    Draw.paletteRedraw();
                    //add(draw);
                    repaint();
                }
                else if (o == logOutButton){
                    System.exit(0);
                }
            }
        };
        paintButton.addActionListener(buttonListener);
        logOutButton.addActionListener(buttonListener);
    }


    public static void main(String[] args) {
        JFrame frame = new mainwindow();
        frame.setLayout(null);

    }
}
