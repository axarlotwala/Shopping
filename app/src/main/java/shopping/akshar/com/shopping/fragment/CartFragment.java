package shopping.akshar.com.shopping.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.adapter.Cartadapter;
import shopping.akshar.com.shopping.pojo.Cartmodel;
import shopping.akshar.com.shopping.pojo.Productmodel;
import shopping.akshar.com.shopping.utlis.PreferenseManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    String URL = "http://192.168.0.103/shopping/viewCart.php";
    String qty_cartURL = "http://192.168.0.103/shopping/cart.php";
    private List<Cartmodel> cartmodels;
    RecyclerView recycler_cart;
    TextView total_price;
    Button btn_change;

    SharedPreferences sharedPreferences;


    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recycler_cart = view.findViewById(R.id.recycler_cart);
        total_price = view.findViewById(R.id.total_price);
        btn_change = view.findViewById(R.id.btn_change);
        cartmodels = new ArrayList<>();
        sharedPreferences = getContext().getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        cartView();

       /* show editetxt dialog on button click event*/

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.edittext_dialog,null);
                final EditText edit_dialog = view1.findViewById(R.id.edit_dialog);
                Button dialog_btn = view1.findViewById(R.id.dialog_btn);

                dialog_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!edit_dialog.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(),"Edit Success",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"Please fill it",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycler_cart.setLayoutManager(linearLayoutManager);
        return view;
    }



    private void cartView() {


         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {



                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            sharedPreferences = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                            jsonObject = response.getJSONObject(i);

                                Cartmodel cartmodel = new Cartmodel();
                                cartmodel.setCart_id(jsonObject.getString("cart_id"));
                                cartmodel.setToken(jsonObject.getString("token"));
                                cartmodel.setProduct_name(jsonObject.getString("product_name"));
                                cartmodel.setProduct_price(jsonObject.getString("product_price"));
                                cartmodel.setCart_imageurl(jsonObject.getString("product_image"));
                                cartmodel.setCart_date(jsonObject.getString("cart_date"));
                                cartmodels.add(cartmodel);

                                sharedPreferences.getString(cartmodel.getToken(),KEY_USER_ID);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Cartadapter cartadapter = new Cartadapter(getActivity(), cartmodels);
                    recycler_cart.setAdapter(cartadapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(jsonArrayRequest);

        }



}
