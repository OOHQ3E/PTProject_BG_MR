package Classes;

public class User {
    public User(String userName, int authLevel) {
        this.setUserName(userName);
        this.setAuthLevel(authLevel);
    }
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
}
