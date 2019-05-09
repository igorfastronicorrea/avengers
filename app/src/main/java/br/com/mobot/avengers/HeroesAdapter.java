package br.com.mobot.avengers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Callback;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder> {

    private Context context;
    private List<HeroModel> data;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public HeroesAdapter(Context mContext, List<HeroModel> mData) {
        this.context = mContext;
        this.data = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hero_list, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.txtName.setText(data.get(i).getName());
        Glide.with(context).load(data.get(i).getThumbnail()).into(viewHolder.imgThumbnail);
    }

    public void setRecycleViewOnClickListener(RecyclerViewOnClickListener r) {
        recyclerViewOnClickListener = r;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgThumbnail;
        private TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            txtName = itemView.findViewById(R.id.txtName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnClickListener != null){
                recyclerViewOnClickListener.onClickListener(v, getLayoutPosition(), data.get(getLayoutPosition()).getId());
            }
        }
    }
}
