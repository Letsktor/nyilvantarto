package hu.undieb.nyilvantarto;

import java.util.List;

public class User {
    private String userName;
    private String password;
    private String email;
    private String name;
    private List<TimeTable> tables;

    public User(String userName, String password, String email, String name) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", tables=" + tables +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeTable> getTables() {
        return tables;
    }

    public void setTables(List<TimeTable> tables) {
        this.tables = tables;
    }
}
