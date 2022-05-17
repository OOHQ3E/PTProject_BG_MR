package Classes;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    public User(int ID, String userName, int authLevel) {
        this.setId(ID);
        this.setUserName(userName);
        this.setAuthLevel(authLevel);
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String userName;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private int authLevel;
    public int getAuthLevel() {
        return authLevel;
    }
    public void setAuthLevel(int authLevel) {
        this.authLevel = authLevel;
    }

    @Override
    public String toString() {
        return "User;" + id + ";" + userName + ";" + authLevel;
    }

    public static User convertStringToUser(String userString) {
        String[] data = userString.split(";");
        if (!Objects.equals(data[0], "User")) {
            return null;
        }
        return new User(Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]));
    }

    public static ArrayList<User> convertStringToUserList(String userListString) {
        ArrayList<User> users = new ArrayList<User>();
        String[] data = userListString.split(":");
        if (!Objects.equals(data[0], "UserList")) {
            return null;
        }
        int count = data.length;
        for (int i = 1; i < count; i++) {
            users.add(convertStringToUser(data[i]));
        }
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && authLevel == user.authLevel && Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, authLevel);
    }
}
