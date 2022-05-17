package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

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
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getInt(4)));
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
                user = new User(rs.getInt(1), rs.getString(2), rs.getInt(4));
            }
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean IsAdmin(int id) {
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "select count(*) from users where id="+id+" and auth_level=3;";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
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
            String query = "select count(*) from users where username=\""+Username+"\";";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() && rs.getInt(1) > 0) {
                con.close();
                return false;
            }
            query = "insert into users (username, password, auth_level) values (\""+ Username +"\",md5(\"" + Password + "\"), " + AuthLevel + ");";
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
            String query = "select count(*) from users where id != "+ID+" and username = \"" + Username + "\";";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() && rs.getInt(1) > 0) {
                con.close();
                return false;
            }
            if (!Objects.equals(Password, "")) {
                query = "update users set username = \""
                        + Username + "\", password=md5(\"" + Password + "\"), auth_level = "
                        + AuthLevel + " where id = " + ID + ";";
            }
            else {
                query = "update users set username = \""
                        + Username + "\", auth_level = "
                        + AuthLevel + " where id = " + ID + ";";
            }
            stmt.execute(query);
            con.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Pixel> GetAllPixels() {
        ArrayList<Pixel> pixels = new ArrayList<Pixel>();
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pixels;");
            while (rs.next()) {
                pixels.add(new Pixel(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)
                ));
            }
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return pixels;
    }

    public boolean ResetPixelCanvas(int width) {
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "truncate pixels";
            stmt.execute(query);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < width; y++) {
                    stmt.execute("insert into pixels (x, y, r, g, b) values (" + x + ", " + y + ", 255, 255, 255)");
                }
            }
            con.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean UpdatePixel(int x, int y, int r, int g, int b) {
        try {
            Connection con = DriverManager.getConnection(connectionString, DBUserName, DBPassword);
            Statement stmt = con.createStatement();
            String query = "update pixels set r = " + r + ", g = " + g + ", b = " + b
                    + " where x = " + x + " and y = " + y + ";";
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
