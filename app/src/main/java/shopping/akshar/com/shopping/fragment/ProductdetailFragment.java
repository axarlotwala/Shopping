package shopping.akshar.com.shopping.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.HashMap;

import java.util.Map;



import shopping.akshar.com.shopping.R;

import shopping.akshar.com.shopping.pojo.Productmodel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductdetailFragment extends Fragment {


    ImageView image;
    TextView detail_pname,detail_price,detail_desc,p_id;
    Button add_cart,buy;
    FirebaseUser firebaseUser;
    String URL = "http://192.168.0.103/shopping/cart.php";
    String email;

    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";






    public ProductdetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_productdetail, container, false);

        p_id = view.findViewById(R.id.p_id);
        image = view.findViewById(R.id.image);
        detail_pname = view.findViewById(R.id.detail_pname);
        detail_price = view.findViewById(R.id.detail_price);
        detail_desc = view.findViewById(R.id.detail_desc);
        add_cart = view.findViewById(R.id.add_cart);
        buy = view.findViewById(R.id.buy);
        sharedPreferences = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        binddata();

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addto_cart();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buynow();
            }
        });

        return view;

    }

    private void buynow() {
        // add payment method
    }

    // add data on cart table user id get successfull but product id not get

    private void addto_cart()  {

        //if user id null then not add value on cart table automatic force close app
        if(firebaseUser.getUid() != null && p_id != null){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    Toast.makeText(getActivity(),"SuccessFully Add To Cart",Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();

                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();

                            params.put("token", firebaseUser.getUid());  //Its a Reference Key Of User succesfull fetch
                            Bundle bundle = getArguments();
                            String product_id = bundle.getString("product_id");
                            params.put("product_id", product_id);


                            return params;
                    }


            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

        }
        else {
                Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
        }
    }

//get data FROM Productpage
    private void binddata() {
        Bundle bundle = getArguments();

        String product_id =  bundle.getString("product_id");
        String name =  bundle.getString("product_name");
        String price = bundle.getString("price");
        String description = bundle.getString("product_desc");
        String imageurl = bundle.getString("product_image");


        p_id.setText(product_id);
        detail_pname.setText(name);
        detail_price.setText(price);
        detail_desc.setText(description);
        Glide.with(this).load(imageurl).into(image);
    }




}
