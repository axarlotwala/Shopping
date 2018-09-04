package shopping.akshar.com.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.fragment.ProductFragment;
import shopping.akshar.com.shopping.pojo.Dashboardmodel;

public class Dashboard_adaptor extends RecyclerView.Adapter<Dashboard_adaptor.ViewHolder> {

    Context context;
    private List<Dashboardmodel> dashboardmodels;

    public Dashboard_adaptor(Context context, List<Dashboardmodel> dashboardmodels) {
        this.context = context;
        this.dashboardmodels = dashboardmodels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(dashboardmodels.get(position).getImage()).into(holder.brand_logo);
        holder.brand_name.setText(dashboardmodels.get(position).getName());
        holder.brand_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new ProductFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_main,fragment)
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(context,""+dashboardmodels.get(position).getName(),Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardmodels.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{


        ImageView brand_logo;
        TextView brand_name;
        CardView brand_card;

        public ViewHolder(View itemView) {
            super(itemView);

            brand_logo = itemView.findViewById(R.id.brand_logo);
            brand_name = itemView.findViewById(R.id.brand_name);
            brand_card = itemView.findViewById(R.id.brand_card);
        }



    }
}
