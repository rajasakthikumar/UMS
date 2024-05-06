public class User {
    public String name;
    public String userId;
    public UserType userType;

    public User(String id, String name,UserType userType) {
        this.userId = id;
        this.name = name;
        this.userType = userType;

    }

    enum UserType {
        Student,Staff,Teacher;
    }

    public String getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }
}





