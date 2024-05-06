public class Staff extends User {
    public Staff (int id, String name) {
        super("Sta"+id, name ,User.UserType.Staff);
    }

    public Staff(String id,String  name, UserType userType) {
        super(id,name,User.UserType.Staff);
    }
}
