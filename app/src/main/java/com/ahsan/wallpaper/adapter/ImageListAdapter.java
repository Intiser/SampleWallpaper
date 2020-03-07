package com.ahsan.wallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ahsan.wallpaper.R;
import com.ahsan.wallpaper.model.HitsItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.Holder> {

    private LayoutInflater inflater;
    private Context context;

    public ImageListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private ArrayList<HitsItem> hitsItemArrayList = new ArrayList<>();

    public void setHitsItemArrayList(ArrayList<HitsItem> hitsItemArrayList) {
        this.hitsItemArrayList = hitsItemArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_image, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setPosition(position);
        HitsItem item = hitsItemArrayList.get(position);
        Glide.with(context).load(item.getPreviewURL()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return hitsItemArrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        private int position = -1;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(this::onClick);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void onClick(View view) {
            callback.onSelectedItem(position);
        }
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback{
        void onSelectedItem(int position);
    }

}
