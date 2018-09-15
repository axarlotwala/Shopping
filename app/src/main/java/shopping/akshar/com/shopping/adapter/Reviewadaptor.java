package shopping.akshar.com.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.pojo.Reviewmodel;

public class Reviewadaptor extends RecyclerView.Adapter<Reviewadaptor.ViewHolder> {

    private Context context;
    private List<Reviewmodel> reviewmodels;

    public Reviewadaptor(Context context, List<Reviewmodel> reviewmodels) {
        this.context = context;
        this.reviewmodels = reviewmodels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.user.setText(reviewmodels.get(position).getName());
        holder.total_rate.setText(reviewmodels.get(position).getRate());
        holder.date_review.setText(reviewmodels.get(position).getDate());
        holder.comment.setText(reviewmodels.get(position).getReview_comment());
        Glide.with(context).load(reviewmodels.get(position).getUser_image()).into(holder.user_image);

    }

    @Override
    public int getItemCount() {
        return reviewmodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView user_image;
        TextView user,total_rate,date_review,comment;

        public ViewHolder(View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.user_image);
            user = itemView.findViewById(R.id.user);
            total_rate = itemView.findViewById(R.id.total_rat);
            date_review = itemView.findViewById(R.id.date_review);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
