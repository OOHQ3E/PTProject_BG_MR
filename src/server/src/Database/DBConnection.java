package Database;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private String connectionString;
    private String DBUserName;
    private String DBPassword;

    public DBConnection(String databaseHost, String databaseName, String databaseUsername, String databasePassword) {
        this.connectionString = "jdbc:mysql://" + databaseHost + ":3306/" + databaseName;
        this.DBUserName = databaseUsername;
        this.DBPassword = databasePassword;
    }

    public ArrayList<User> GetAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            System.out.println(connectionString);
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users;");
            while (rs.next()) {
                users.add(new User(rs.getString(2), rs.getInt(4)));
            }
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public User UserExists(String Username, String Password) {
        User user = null;
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "select * from users where username=\""+Username+"\" and password=md5(\""+Password+"\");";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                user = new User(rs.getString(2), rs.getInt(4));
            }
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User GetUser(int ID) {
        User user = null;
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "select * from users where id="+ID+";";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getInt(4));
            }
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean AddNewUser(String Username, String Password, int AuthLevel) {
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "insert into users (username, password, auth_level) values (\""+ Username +"\",md5(\"" + Password + "\"), " + AuthLevel + ")";
            stmt.execute(query);
            con.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean DeleteUser(int ID) {
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "delete from users where id="+ID+";";
            stmt.execute(query);
            con.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean UpdateUser(int ID, String Username, String Password, int AuthLevel) {
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "update users set username = \""
                    +Username+"\", password=md5(\""+Password+"\"), auth_level = "
                    + AuthLevel + " where id = "+ ID + ";";
            stmt.execute(query);
            con.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
