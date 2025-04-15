package afinal.afinal;

public class UserModel {
    public String uid;
    public String username;
    public String email;

    // Firestore needs a public no-arg constructor
    public UserModel() {}

    public UserModel(String uid, String username, String email) {
        this.uid = uid;
        this.username = username;
        this.email = email;
    }
}
