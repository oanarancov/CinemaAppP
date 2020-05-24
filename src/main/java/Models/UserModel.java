package Models;

public class UserModel {
    private String username,password;
    private UserType role;

    public UserModel(String username, String password, UserType role){
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserType role) {
        this.role = role;
    }
}
