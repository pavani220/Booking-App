package afinal.afinal;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    // Shared Preferences file name
    private static final String PREF_NAME = "UserSession";

    // Shared Preferences keys
    private static final String IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    // Set user login status in the field
    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }
}
//when i click the logout and closes the app.and open then my mainactivity is visible eventhough i logout.give proper validations as if the user is logged out then login should open eventhough we closes the app
