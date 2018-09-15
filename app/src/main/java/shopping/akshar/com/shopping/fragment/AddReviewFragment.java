package shopping.akshar.com.shopping.fragment;


import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    ImageView productImage;
    RatingBar rat;
    TextView count_rating,productName;
    Button submit;
    EditText comment;
    private String Review_URL = "http://192.168.0.103/shopping/addReview.php";
    FirebaseUser firebaseUser;


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
        productImage = view.findViewById(R.id.product_image);
        productName = view.findViewById(R.id.product_name);

        submit = view.findViewById(R.id.submit);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return view;
    }


}
