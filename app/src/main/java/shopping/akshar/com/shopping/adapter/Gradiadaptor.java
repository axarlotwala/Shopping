package shopping.akshar.com.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.pojo.Graidmodel;

public class Gradiadaptor extends RecyclerView.Adapter<Gradiadaptor.ViewHolder> {

    private Context context;
    private List<Graidmodel> graidmodels;

    public Gradiadaptor(Context context, List<Graidmodel> graidmodels) {
        this.context = context;
        this.graidmodels = graidmodels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.graidlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(graidmodels.get(position).getUrl()).into(holder.image_load);
        holder.image_name.setText(graidmodels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return graidmodels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_load;
        TextView image_name;

        public ViewHolder(View itemView) {
            super(itemView);

            image_load = itemView.findViewById(R.id.image_load);
            image_name = itemView.findViewById(R.id.image_name);
        }
    }
}
