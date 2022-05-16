package adminWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userManagement extends JFrame{
    private JPanel user_mgPanel;
    private JButton deleteUserButton;
    private JButton addUserButton;
    private JTextField tf_username;
    private JTextField tf_password;
    private JTextField tf_confPassword;
    private JButton modifyUserButton;
    private JTextField tf_id;
    private JTable userTable;

    public userManagement(JFrame parent){
        super();
        setTitle("User management");
        setContentPane(user_mgPanel);
        setMinimumSize(new Dimension(600,500));
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setUpButtonListeners();
    }
    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == addUserButton){
                    //add request
                }
                else if (o == deleteUserButton){
                    //delete request
                }
                else if (o == modifyUserButton){
                    //modify request
                }
            }
        };
        addUserButton.addActionListener(buttonListener);
        deleteUserButton.addActionListener(buttonListener);
        modifyUserButton.addActionListener(buttonListener);
    }

    //TODO: listing users in the usersTable
    public static userManagement userManagement;
    public static void main(String[] args) {
        userManagement = new userManagement(null);
    }
}

