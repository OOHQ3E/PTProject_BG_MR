package loginForm;

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

    public LoginForm(JFrame parent){
        super();
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(300,200));
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        setUpButtonListeners();
    }
    //giving listeners to the two buttons
    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == btn_login){
                    //getting the data from text fields
                    String uname = tf_username.getText();
                    String pword = String.valueOf(pf_password.getPassword());

                    //passing this data to this function → return is a user supposedly
                    loggedinUser = getAuthenticatedUser(uname,pword);

                    //if the logged in user is a simple user it will open the mainWindow
                    if (loggedinUser.getAuthLevel() == 0){
                        //JOptionPane.showMessageDialog(loginForm, "you pressed the login button");
                        System.out.println("logged user is " + loggedinUser);
                        //opening the mainwindow
                        mainwindow main = new mainwindow();
                        dispose();
                    }

                    //if the user is an admin, it will open the user management page
                    else if (loggedinUser.getAuthLevel() == 3){
                        System.out.println("logged user is " + loggedinUser);
                        userManagement usermanagement = new userManagement(null);
                        dispose();
                    }

                    //if there is no information it will show a dialog
                    else{
                        JOptionPane.showMessageDialog(loginForm, "no information given");
                    }
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

    //initializing a non valid user
    public User loggedinUser = new User(0,null,99);
    //authentication function
    private User getAuthenticatedUser(String uname, String passWD){
        //server connection needed here I think
        //setting the name of the loggedinuser
        loggedinUser.setUserName(uname);
        //if the input is "admin" "admin" then it gives the loggedinuser an authlevel 3
        if (loggedinUser.getUserName().equals("admin") && passWD.equals("admin")){
            loggedinUser.setAuthLevel(3);
        }
        //if there is information given, the user gets an authlevel 0
        else if (!uname.equals("") && !passWD.equals("")){
            loggedinUser.setAuthLevel(0);
        }

        return loggedinUser;
    }

    public static LoginForm loginForm;
    public static void main(String[] args) {
        loginForm = new LoginForm(null);
    }
}
