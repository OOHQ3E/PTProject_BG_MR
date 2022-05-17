package adminWindow;

import Classes.User;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        createTable();
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
                    String data[] = {tf_username.getText(),tf_password.getText()};
                    DefaultTableModel tblModel = (DefaultTableModel)userTable.getModel();
                    tblModel.addRow(data);
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
    ArrayList<User> users = new ArrayList<User>();
    User user1 = new User(1,"testuser1",0);

    private void createTable(){
        users.add(user1);

        Object[][] data ={
                {1,"testuser1",0},
                {2,"testuser2",0},
                {3,"testuser3",0}
        };

        userTable.setModel(new DefaultTableModel(
                data,
                new String[]{"id", "username", "authority"}
        ));
        TableColumnModel colums = userTable.getColumnModel();
        colums.getColumn(0).setMaxWidth(50);
        colums.getColumn(1).setMinWidth(100);
        colums.getColumn(2).setMaxWidth(50);
    }

    //TODO: listing users in the usersTable
    public static userManagement userManagement;
    public static void main(String[] args) {
        userManagement = new userManagement(null);
    }
}