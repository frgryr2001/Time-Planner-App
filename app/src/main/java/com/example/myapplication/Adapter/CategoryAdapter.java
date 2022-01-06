package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.Object.ChildCategoryClass;
import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ParentCategoryClass> listCategory;
    private HashMap<ParentCategoryClass,List<ChildCategoryClass>> listChildCategory;
    public CategoryAdapter(Context mContext, List<ParentCategoryClass> listCategory, HashMap<ParentCategoryClass, List<ChildCategoryClass>> listChildCategory) {
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
        ParentCategoryClass p =(ParentCategoryClass) getGroup(i);

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
                    if(b) {
                        ((ExpandableListView) viewGroup).collapseGroup(i);
                    }else {
                        ((ExpandableListView) viewGroup).expandGroup(i, true);
                    }

                }
        });
        ivGroupIndicator.setFocusable(false);
        // Hiệu ứng select lên xuống của arrow
        ivGroupIndicator.setSelected(b);
        // View nameCategory cha
        TextView tvNameCategory = view.findViewById(R.id.tvNameCategory);
        tvNameCategory.setText(p.getName());
        tvNameCategory.setTextColor(p.getColor());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildCategoryClass c =(ChildCategoryClass) getChild(i,i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.category_row_child,null);
        }
        ImageButton ibtnVertChild = view.findViewById(R.id.ibtnVertChild);
        ibtnVertChild.setFocusable(false);

        TextView tvNameCategoryChild = view.findViewById(R.id.tvNameCategoryChild);
        tvNameCategoryChild.setText(c.getName());
        tvNameCategoryChild.setTextColor(c.getColor());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
