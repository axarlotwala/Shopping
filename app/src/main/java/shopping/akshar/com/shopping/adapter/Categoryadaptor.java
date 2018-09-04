package shopping.akshar.com.shopping.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.manager.SupportRequestManagerFragment;

import org.json.JSONObject;

import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.fragment.DashboardFragment;
import shopping.akshar.com.shopping.fragment.ProductFragment;
import shopping.akshar.com.shopping.pojo.Categorymodel;
import shopping.akshar.com.shopping.pojo.Productmodel;

public class Categoryadaptor extends RecyclerView.Adapter<Categoryadaptor.ViewHolder> {

    private Context context;
    private List<Categorymodel> categorymodels;



    public Categoryadaptor(Context context, List<Categorymodel> categorymodels) {
        this.context = context;
        this.categorymodels = categorymodels;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        Glide.with(context).load(categorymodels.get(position).getCat_image()).into(holder.cat_image);
        holder.cat_name.setText(categorymodels.get(position).getCat_name());
        holder.linear_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context,""+categorymodels.get(position).getCat_name(),Toast.LENGTH_LONG).show();


                // open new Fragment in Item Click Event
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new DashboardFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_main,fragment)
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(context,""+categorymodels.get(position).getCat_id(),Toast.LENGTH_SHORT).show();




            }
        });

    }

    @Override
    public int getItemCount() {
        return categorymodels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cat_name;
        private ImageView cat_image;
        private LinearLayout linear_click;


        public ViewHolder(View itemView) {
            super(itemView);

            linear_click = itemView.findViewById(R.id.linear_click);
            cat_image = itemView.findViewById(R.id.cat_image);
            cat_name = itemView.findViewById(R.id.cat_name);
        }
    }


}
