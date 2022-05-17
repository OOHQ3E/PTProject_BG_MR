package userWindow;

import Classes.Pixel;
import Classes.Connection;
import Classes.User;
import adminWindow.userManagement;
import loginForm.LoginForm;

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
    private JButton btn_UsrManagement;
    public Draw draw = new Draw();
    private static Connection c;
    public static mainwindow mainWindow;
    public mainwindow(Connection connection, User user){
        mainwindow.c = connection;
        mainwindow.mainWindow = this;
        setContentPane(mainIguess);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Window");
        setSize(650,350);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1,0,0));

        add(draw);
        btn_UsrManagement.setVisible(user.getAuthLevel() >= 3);
        loggedinUser = user;
        setVisible(true);
        setUpButtonListeners();
        c.getAllPixel();
    }
    public void drawSquare(){
        Draw.paletteRedraw();
        repaint();
    }
    private User loggedinUser;
    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == paintButton){
                    Pixel newPixel = new Pixel((int) nud_X.getValue(),(int) nud_Y.getValue(),(int)nud_red.getValue(),(int)nud_green.getValue(),(int)nud_blue.getValue());
                    c.updatePixel(newPixel);
                }
                else if (o == logOutButton){
                    loggedinUser = null;
                    c.sendLogoutRequest();
                    LoginForm loginForm = new LoginForm(null);
                    dispose();
                }
                else if(o == btn_UsrManagement){
                    userManagement usermanagement = new userManagement(null,c);
                    dispose();
                }

            }
        };
        paintButton.addActionListener(buttonListener);
        logOutButton.addActionListener(buttonListener);
        btn_UsrManagement.addActionListener(buttonListener);
    }
}
