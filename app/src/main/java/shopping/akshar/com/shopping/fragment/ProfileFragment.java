package shopping.akshar.com.shopping.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import shopping.akshar.com.shopping.MainActivity;
import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.utlis.PreferenseManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView tv_email,tv_unique;
    ImageView profile_image;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String profile_URL = "http://192.168.0.103/shopping/update_customer.php";
    EditText et_user,et_phone,et_address,et_pin;
    Button btn_send;

    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tv_email = view.findViewById(R.id.tv_email);
        tv_unique = view.findViewById(R.id.tv_unique);

        et_user = view.findViewById(R.id.et_user);
        et_phone = view.findViewById(R.id.et_phone);
        et_address = view.findViewById(R.id.et_address);
        et_pin = view.findViewById(R.id.et_pin);
        btn_send = view.findViewById(R.id.btn_send);

        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        sharedPreferences = getContext().getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        Log.d("EmailID", "onCreateView: "+sharedPreferences.getString(KEY_EMAIL,null));
        tv_email.setText(sharedPreferences.getString(KEY_EMAIL,null));
        tv_unique.setText(sharedPreferences.getString(KEY_USER_ID,null));
        sharedPreferences.getString(KEY_USER_ID,firebaseUser.getUid());

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_add();
            }
        });


        return view;
    }

    private void profile_add() {

        final String name = et_user.getText().toString();
        final String phone = et_phone.getText().toString();
        final String address = et_address.getText().toString();
        final String pincode = et_pin.getText().toString();


        if (name.isEmpty()){
            et_user.setError("Name Is Require");
            et_user.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            et_phone.setError("Phone Is Require");
            et_phone.requestFocus();
            return;
        }

        if (address.isEmpty()){
            et_address.setError("address Is Require");
            et_address.requestFocus();
            return;
        }

        if (pincode.isEmpty()){
            et_pin.setError("Pincode is Require");
            et_pin.requestFocus();
            return;
        }

        clearData();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, profile_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("token",firebaseUser.getUid());
                params.put("cust_name",name);
                params.put("cust_phoneno",phone);
                params.put("cust_address",address);
                params.put("cust_pincode",pincode);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void clearData() {
        et_user.setText("");
        et_phone.setText("");
        et_address.setText("");
        et_pin.setText("");

    }

}
