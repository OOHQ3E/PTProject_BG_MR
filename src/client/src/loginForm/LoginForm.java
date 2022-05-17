package loginForm;

import Classes.Connection;
import Classes.User;
import adminWindow.userManagement;
import userWindow.Draw;
import userWindow.mainwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{

    private JTextField tf_username;
    private JPasswordField pf_password;
    private JButton btn_login;
    private JButton btn_cancel;
    private JPanel loginPanel;
    private static Connection connection;
    public static void LoginAttempt(User user){
        mainwindow main = new mainwindow(connection, user);

        lf.dispose();
    }
    private static LoginForm lf;
    public static void showError(String error){
        lf.showDialogbox(error);
    }
    public static void showMessage(String message){
       lf.showDialogbox(message);
    }
    public LoginForm(JFrame parent){
        super();
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(300,200));
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setUpButtonListeners();
        lf = this;
    }
    //giving listeners to the two buttons
    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == btn_login){
                    String uname = tf_username.getText();
                    String pword = String.valueOf(pf_password.getPassword());
                    connection.sendLoginRequest(uname,pword);
                }
                //pressing the cancel button closes the program
                else if (o == btn_cancel){
                    System.exit(0);
                }
            }
        };
        //adding the actionlistener function to the buttons
        btn_login.addActionListener(buttonListener);
        btn_cancel.addActionListener(buttonListener);
    }
    private void showDialogbox(String message){
        JOptionPane.showMessageDialog(loginForm,message);
    }
    public static LoginForm loginForm;
    public static void main(Connection connection) {
        LoginForm.connection = connection;
        loginForm = new LoginForm(null);
    }
}
