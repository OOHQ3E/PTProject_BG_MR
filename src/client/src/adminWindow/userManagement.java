package adminWindow;

import Classes.Connection;
import Classes.User;
import userWindow.mainwindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JTextField tf_authority;
    private JButton btn_Back;
    private JButton btn_Refresh;
    Connection conn;
    private static userManagement um;
    public static void resetCurrentTable(){
        um.resetTable();
    }
    public static void addUserList(ArrayList<User> users){
        um.addTableElements(users);
    }
    private User loggedinUser;

    public userManagement(JFrame parent, Connection connection, User loggedinUser){
        super();
        um = this;
        conn = connection;
        this.loggedinUser = loggedinUser;
        createTable();
        setTitle("User management");
        setContentPane(user_mgPanel);
        setMinimumSize(new Dimension(600,500));
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setUpButtonListeners();
        conn.getAllUsers();
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String id = userTable.getValueAt(userTable.getSelectedRow(),0).toString();
                String name = userTable.getValueAt(userTable.getSelectedRow(),1).toString();
                String authority = userTable.getValueAt(userTable.getSelectedRow(),2).toString();
                tf_id.setText(id);
                tf_username.setText(name);
                tf_authority.setText(authority);
            }
        });
    }
    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == addUserButton){
                    conn.sendNewUser(tf_username.getText(),tf_password.getText(),Integer.parseInt(tf_authority.getText()));
                    conn.getAllUsers();
                }
                else if (o == deleteUserButton){
                    if (!tf_id.getText().equals("")){
                        conn.deleteUser(Integer.parseInt(tf_id.getText()));
                        conn.getAllUsers();
                    }
                }
                else if (o == modifyUserButton){
                    User selectedUser = new User(Integer.parseInt(tf_id.getText()), tf_username.getText(),Integer.parseInt(tf_authority.getText()));
                    conn.updateUser(selectedUser,tf_password.getText());
                    conn.getAllUsers();
                } else if (o == btn_Back) {
                    mainwindow main = new mainwindow(conn,loggedinUser);
                    dispose();
                }
                else if (o == btn_Refresh){
                    conn.getAllUsers();
                }
            }
        };
        addUserButton.addActionListener(buttonListener);
        deleteUserButton.addActionListener(buttonListener);
        modifyUserButton.addActionListener(buttonListener);
        btn_Back.addActionListener(buttonListener);
        btn_Refresh.addActionListener(buttonListener);
    }


    User user1 = new User(1,"testuser1",0);
    private Object[][] data;
    private void addTableElements(ArrayList<User> users){
       data = new Object[users.size()][3];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getId();
            data[i][1] = users.get(i).getUserName();
            data[i][2] = users.get(i).getAuthLevel();
        }
        createTable();
    }
    private void resetTable(){
        data = null;
    }

    private void createTable(){

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

}