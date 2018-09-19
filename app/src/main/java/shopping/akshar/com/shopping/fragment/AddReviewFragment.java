package shopping.akshar.com.shopping.fragment;


import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddReviewFragment extends Fragment {

    ImageView r_productImage;
    RatingBar rat;
    TextView count_rating,r_productName;
    Button submit;
    EditText comment;
    private String Review_URL = "http://192.168.0.103/shopping/addReview.php";
    FirebaseUser firebaseUser;
    String id;

    public AddReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_review, container, false);

        rat = view.findViewById(R.id.rat);
        count_rating = view.findViewById(R.id.count_rating);
        comment = view.findViewById(R.id.comment);
        r_productImage = view.findViewById(R.id.r_productImage);
        r_productName = view.findViewById(R.id.r_productName);



        submit = view.findViewById(R.id.submit);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReview();
            }
        });

        BindData();
        return view;
    }

    private void BindData() {
        Bundle bundle = getArguments();
        id = bundle.getString("product_id");
        String name = bundle.getString("product_name");
        String image = bundle.getString("product_image");


        r_productName.setText(name);
        Glide.with(this).load(image).into(r_productImage);

    }


    private void sendReview() {

         final String message = comment.getText().toString();

         final float rating = rat.getRating();

        count_rating.setText("Rating : "+rating);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Review_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(),response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("token",firebaseUser.getUid());
                params.put("product_id",id);
                params.put("rating", String.valueOf(rating));
                params.put("comment",message);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


}
