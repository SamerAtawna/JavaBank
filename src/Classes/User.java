package Classes;

import Classes.Enums.Enums;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private String lastLogin;
    private Enums.Permission permission;

    public User(String userName, String password, String lastLogin, Enums.Permission permission) {
        this.userName = userName;
        this.lastLogin = lastLogin;
        this.permission = permission;
        this.password = password;
    }
    public User(String userName, String password, Enums.Permission permission) {
        this.userName = userName;
        this.permission = permission;
        this.password = password;
    }
    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Enums.Permission getPermission() {
        return permission;
    }

    public void setPermission(Enums.Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", permission=" + permission +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
