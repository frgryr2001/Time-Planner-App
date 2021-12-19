package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Object.CategoryClass;
import com.example.myapplication.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<CategoryClass> lstCategory;
    private Context mContxet;
    public CategoryAdapter( Context contxet,List<CategoryClass> lstCategory) {
        this.mContxet = contxet;
        this.lstCategory = lstCategory;
    }
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row,
                parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryClass ca = lstCategory.get(position);
        if(ca==null)
            return;
        holder.ivIcon.setImageResource(ca.getIcon());
        holder.tvNameCategory.setText(ca.getName());


        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code

            }
        });
    }

    @Override
    public int getItemCount() {
        return lstCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvNameCategory;
        CardView layout_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
