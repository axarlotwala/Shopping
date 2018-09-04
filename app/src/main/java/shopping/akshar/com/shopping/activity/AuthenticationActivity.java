package shopping.akshar.com.shopping.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import shopping.akshar.com.shopping.MainActivity;
import shopping.akshar.com.shopping.R;

public class AuthenticationActivity extends AppCompatActivity {


    EditText phone_no;
    EditText otp_no;
    TextView email_link;
    FirebaseAuth auth;

    String codeSent;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    Button btn_send;
    Button btn_getcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        phone_no = findViewById(R.id.phone_no);
        otp_no = findViewById(R.id.otp_no);
        email_link = findViewById(R.id.email_link);
        auth = FirebaseAuth.getInstance();

        btn_getcode = findViewById(R.id.btn_getcode);
        btn_send = findViewById(R.id.btn_send);


        email_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthenticationActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Toast.makeText(AuthenticationActivity.this, "wait for code", Toast.LENGTH_SHORT).show();
                sendVerificationCode();

            }
        });



        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                veryfyCode();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = auth.getCurrentUser();
    }

    private void veryfyCode() {

        String Otpcode = otp_no.getText().toString();
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(codeSent, Otpcode);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(AuthenticationActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "Check Otp", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    private void sendVerificationCode() {

        String Phone = phone_no.getText().toString();

        if (Phone.isEmpty()){
            phone_no.setError("phone No Require");
            phone_no.requestFocus();
            return;
        }



            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    Phone,
                    60,
                    TimeUnit.SECONDS,
                    AuthenticationActivity.this,
                    mCallback);

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }




        @Override
        public void onVerificationFailed(FirebaseException e) {



        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }


    };



}
