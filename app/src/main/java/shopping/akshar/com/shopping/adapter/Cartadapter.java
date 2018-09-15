package shopping.akshar.com.shopping.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.fragment.ProductFragment;
import shopping.akshar.com.shopping.pojo.Cartmodel;
import shopping.akshar.com.shopping.pojo.Productmodel;

public class Cartadapter extends RecyclerView.Adapter<Cartadapter.ViewHolder> {

    Context context;
    List<Cartmodel> cartmodels;
    String Delete_URL = "http://192.168.0.103/shopping/delete_cart.php";
    String product_id;


    public Cartadapter(Context context, List<Cartmodel> cartmodels) {
        this.context = context;
        this.cartmodels = cartmodels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.cart_title.setText(cartmodels.get(position).getProduct_name());
        holder.cart_price.setText(cartmodels.get(position).getProduct_price());
        Glide.with(context).load(cartmodels.get(position).getCart_imageurl()).into(holder.cart_image);

        holder.edit_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuantity();
            }
        });

        product_id = cartmodels.get(position).getProduct_id();
    }



    /*open dialogbox in edit quantity*/

    private void AddQuantity() {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.edittext_dialog,null);
        final EditText edit_dialog = view.findViewById(R.id.edit_dialog);
        Button dialog_btn = view.findViewById(R.id.dialog_btn);
        builder.setTitle("Enter Quantity");

        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        builder.setView(view);
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    @Override
    public int getItemCount() {
        return cartmodels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cart_image;
        TextView  cart_title,cart_price;
        ImageView edit_quantity;


        public ViewHolder(View itemView) {
            super(itemView);

            cart_image = itemView.findViewById(R.id.cart_image);
            cart_title = itemView.findViewById(R.id.cart_title);
            cart_price = itemView.findViewById(R.id.cart_price);

            edit_quantity = itemView.findViewById(R.id.edit_quantity);



        }


    }
}
