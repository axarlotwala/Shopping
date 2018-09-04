package shopping.akshar.com.shopping.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

import shopping.akshar.com.shopping.MainActivity;
import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.utlis.PreferenseManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView tv_email;
    ImageView profile_image;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    EditText et_name,et_address,et_pincode,et_phone;
    RadioGroup gender;
    Button btn_save;
    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";

    private String Profile_Url = "http://192.168.0.103/shopping/userToken.php";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tv_email = view.findViewById(R.id.tv_email);
        et_name = view.findViewById(R.id.et_name);
        et_address = view.findViewById(R.id.et_address);
        et_pincode = view.findViewById(R.id.et_pincode);
        et_phone = view.findViewById(R.id.et_phone);
        gender = view.findViewById(R.id.gender);
        btn_save = view.findViewById(R.id.btn_save);

        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        sharedPreferences = getContext().getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        Log.d("EmailID", "onCreateView: "+sharedPreferences.getString(KEY_EMAIL,null));
        tv_email.setText(sharedPreferences.getString(KEY_EMAIL,null));
        sharedPreferences.getString(KEY_USER_ID,firebaseUser.getUid());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileData();
            }
        });


        return view;
    }

    private void ProfileData() {

        final String name = et_name.getText().toString();
        final String phone = et_phone.getText().toString();
        final String address = et_address.getText().toString();
        final String pincode = et_pincode.getText().toString();

        final int SelectId = gender.getCheckedRadioButtonId();

        if (name.isEmpty()){
            et_name.setError("Enter name");
            et_name.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            et_phone.setError("Enter Phone");
            et_phone.requestFocus();
            return;
        }

        if (address.isEmpty()){
            et_address.setError("Enter Address");
            et_address.requestFocus();
            return;
        }

        if (pincode.isEmpty()){
            et_pincode.setError("Enter pincode");
            et_pincode.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Profile_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("cust_name",name);
                params.put("cust_phoneno",phone);
                params.put("cust_address",address);
                params.put("cust_pincode",pincode);
                params.put("token",firebaseUser.getUid());
                return params;
            }
        };


    }


}
