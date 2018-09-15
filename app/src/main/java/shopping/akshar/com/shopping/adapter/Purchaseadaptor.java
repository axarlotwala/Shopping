package shopping.akshar.com.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.fragment.AddReviewFragment;
import shopping.akshar.com.shopping.fragment.ProductFragment;
import shopping.akshar.com.shopping.pojo.Productmodel;

public class Purchaseadaptor extends RecyclerView.Adapter<Purchaseadaptor.ViewHolder> {

     List<Productmodel> productmodels;
     Context context;

    public Purchaseadaptor(List<Productmodel> productmodels, Context context) {
        this.productmodels = productmodels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(productmodels.get(position).getProduct_image()).into(holder.purchase_image);
        holder.purchase_name.setText(productmodels.get(position).getProduct_name());
        holder.reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Add Review", Toast.LENGTH_SHORT).show();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new AddReviewFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_main,fragment)
                        .addToBackStack(null)
                        .commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return productmodels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView purchase_image;
        TextView purchase_name;
        Button reviews;

        public ViewHolder(View itemView) {
            super(itemView);

            purchase_image = itemView.findViewById(R.id.purchase_image);
            purchase_name = itemView.findViewById(R.id.purchase_name);
            reviews = itemView.findViewById(R.id.reviews);
        }


    }
}
