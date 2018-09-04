package shopping.akshar.com.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import shopping.akshar.com.shopping.MainActivity;
import shopping.akshar.com.shopping.R;

public class SplashscreenActivity extends AppCompatActivity {
    ImageView logo;
    SharedPreferences preferences;
    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        logo = findViewById(R.id.logo);


            final Intent intent = new Intent(this, LoginActivity.class);

            preferences = getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);


            Thread timer = new Thread() {
                public void run() {

                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        startActivity(intent);
                        finish();
                    }
                }
            };

            timer.start();

    }
}
