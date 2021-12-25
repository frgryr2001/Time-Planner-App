package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Object.CategoryClass;
import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> listCategory;
    private HashMap<String,List<String>> listChildCategory;
    public CategoryAdapter(Context mContext, List<String> listCategory, HashMap<String, List<String>> listChildCategory) {
        this.mContext = mContext;
        this.listCategory = listCategory;
        this.listChildCategory = listChildCategory;
    }


    @Override
    public int getGroupCount() {
        return this.listCategory.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.listChildCategory.get(this.listCategory.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.listCategory.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.listChildCategory.get(this.listCategory.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title =(String) getGroup(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.category_row,null);
        }
        // Tắt sự kiện click focus row của image button
        ImageButton ibtnVert = view.findViewById(R.id.ibtnVert);
        ibtnVert.setFocusable(false);
//
        ImageButton ivGroupIndicator = view.findViewById(R.id.ivGroupIndicator);
//       Sự kiện Click mở rộng danh mục
        ivGroupIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b) ((ExpandableListView) viewGroup).collapseGroup(i);
                else ((ExpandableListView) viewGroup).expandGroup(i, true);
            }
        });
        // Hiệu ứng select lên xuống của arrow
        ivGroupIndicator.setSelected(b);
        // View nameCategory cha
        TextView tvNameCategory = view.findViewById(R.id.tvNameCategory);
        tvNameCategory.setText(title);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String title =(String) getChild(i,i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.category_row_child,null);
        }

        TextView tvNameCategoryChild = view.findViewById(R.id.tvNameCategoryChild);
        tvNameCategoryChild.setText(title);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
