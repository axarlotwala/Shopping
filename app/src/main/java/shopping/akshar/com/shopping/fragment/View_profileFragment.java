package shopping.akshar.com.shopping.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.pojo.Usermodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class View_profileFragment extends Fragment {


    ImageView update;
    TextView tv_name,tv_phone,tv_addess,tv_pincode,tv_email;
    FirebaseAuth auth;
    FirebaseUser user;

    SharedPreferences preferences;
    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";



    public View_profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        tv_name = view.findViewById(R.id.tv_name);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_addess = view.findViewById(R.id.tv_address);
        tv_pincode = view.findViewById(R.id.tv_pincode);
        tv_email = view.findViewById(R.id.tv_email);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        update = view.findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.frame_main,new ProfileFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        preferences = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        tv_email.setText(preferences.getString(KEY_EMAIL,null));

        profileData();
        return view;
    }

    private void profileData() {




    }

}
