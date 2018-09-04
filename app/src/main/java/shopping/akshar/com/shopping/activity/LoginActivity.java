package shopping.akshar.com.shopping.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import shopping.akshar.com.shopping.MainActivity;
import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.utlis.PreferenseManager;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText login_email,login_password;
    Button btn_login;
    FirebaseAuth auth;
    FirebaseUser user;
    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        user = FirebaseAuth.getInstance().getCurrentUser();





        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);

        Is_setSharePreference();
    }

    private void Is_setSharePreference() {
        String email = sharedPreferences.getString(KEY_EMAIL,null);

        if(email != null){
            Intent i= new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }


    private void LoginUser() {


            final String email = login_email.getText().toString();
            String password = login_password.getText().toString();

            if (email.isEmpty()) {
                login_email.setError("Email is Require");
                login_email.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                login_password.setError("password is Require");
                login_password.requestFocus();
                return;
            }


            final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "please wait", "loading...", true);

            (auth.signInWithEmailAndPassword(login_email.getText().toString(), login_password.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();


                        /*auth.getUid();
                        auth.getAccessToken(true);
                        auth.getCurrentUser();
                        listener = UUID;*/


                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login SuccesFull", Toast.LENGTH_SHORT).show();


                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(KEY_EMAIL,email);
                                editor.putString(KEY_USER_ID,auth.getUid());
                                editor.putBoolean("activity_execute",true);
                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {

                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }


                        }
                    });
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                LoginUser();
                break;
            }
        }
    }
}
