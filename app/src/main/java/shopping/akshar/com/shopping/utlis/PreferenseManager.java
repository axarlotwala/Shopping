package shopping.akshar.com.shopping.utlis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import shopping.akshar.com.shopping.MainActivity;
import shopping.akshar.com.shopping.activity.LoginActivity;
import shopping.akshar.com.shopping.activity.RegisterActivity;

public class PreferenseManager {

    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";
    private static PreferenseManager mInstance;
    private static Context mContext;
    private FirebaseUser user;

    public PreferenseManager(Context context){
        mContext = context;
    }

    public synchronized PreferenseManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PreferenseManager(context);
        }
        return mInstance;
    }


    public Boolean UserLogin(String Email,String User_UID){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_EMAIL, Email);
        editor.putString(KEY_USER_ID,User_UID);


        editor.apply();
        return true;
    }

    public Boolean IsUserLoggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    public Boolean IsLogout(){

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        return true;
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }


}

