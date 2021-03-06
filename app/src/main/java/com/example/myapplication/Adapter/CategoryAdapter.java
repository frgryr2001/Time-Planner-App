package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Object.ChildCategoryClass;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ParentCategoryClass> listCategory;
    private HashMap<ParentCategoryClass,List<ChildCategoryClass>> listChildCategory;

    static private FirebaseDatabase database = FirebaseDatabase.getInstance();
    static private DatabaseReference userRef;
    static private DatabaseReference CategoryRef;
    static String userId = MainActivity.userId;

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

        // T???t s??? ki???n click focus row c???a image button
        ImageButton ibtnVert = view.findViewById(R.id.ibtnVert);
        ibtnVert.setFocusable(false);
        ibtnVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickMenuParent(view,p);
            }
        });
//
        ImageButton ivGroupIndicator = view.findViewById(R.id.ivGroupIndicator);
        ImageView ivIcon = view.findViewById(R.id.ivIcon);
//       S??? ki???n Click m??? r???ng danh m???c

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
        // Hi???u ???ng select l??n xu???ng c???a arrow
        ivGroupIndicator.setSelected(b);
        // View nameCategory cha
        TextView tvNameCategory = view.findViewById(R.id.tvNameCategory);
        tvNameCategory.setText(p.getName());
        if(p.getColor() == 0){
            tvNameCategory.setTextColor(Color.BLACK);
            ivIcon.setColorFilter(Color.BLACK);
        }else{
            tvNameCategory.setTextColor(p.getColor());
            ivIcon.setColorFilter(p.getColor());
        }
        LinearLayout layout_item = view.findViewById(R.id.layout_item);
//        layout_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, ""+p.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildCategoryClass c =(ChildCategoryClass) getChild(i,i1);
        // cha c???a th???ng con
        ParentCategoryClass p = (ParentCategoryClass) getGroup(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.category_row_child,null);
        }
        ImageButton ibtnVertChild = view.findViewById(R.id.ibtnVertChild);
        ibtnVertChild.setFocusable(false);
        ibtnVertChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickMenuChild(view, p, c);
            }
        });
        ImageView ivIconChild = view.findViewById(R.id.ivIconChild);
        TextView tvNameCategoryChild = view.findViewById(R.id.tvNameCategoryChild);
        tvNameCategoryChild.setText(c.getName());
        if(c.getColor() == 0){
            tvNameCategoryChild.setTextColor(Color.BLACK);
            ivIconChild.setColorFilter(Color.BLACK);
        }else{
            tvNameCategoryChild.setTextColor(c.getColor());
            ivIconChild.setColorFilter(c.getColor());
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    // m??? menu c???a parent
    private void showPickMenuParent(View anchor, ParentCategoryClass  p) {
        PopupMenu popupMenu = new PopupMenu(mContext, anchor);
        popupMenu.inflate(R.menu.category_option);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.deleteCate:
                       // Toast.makeText(mContext, p.getName().toString(), Toast.LENGTH_SHORT).show();
                        removeParentCate(p);
                        break;

                }
                return false;
            }
        });
        popupMenu.show();
    }
    // m??? menu c???a child
    private void showPickMenuChild(View anchor, ParentCategoryClass p, ChildCategoryClass c) {
        PopupMenu popupMenu = new PopupMenu(mContext, anchor);
        popupMenu.inflate(R.menu.category_option);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.deleteCate:
                       // Toast.makeText(mContext, p.getName().toString() + " " +c.getName().toString(), Toast.LENGTH_SHORT).show();
                        removeChildCate(p,c);
                        break;

                }
                return false;
            }
        });
        popupMenu.show();
    }


    // X??a danh m???c cha
    private void removeParentCate(ParentCategoryClass p){
        userRef = database.getReference(userId);
        CategoryRef = userRef.child("Category");
        CategoryRef.child(p.getId()).removeValue();
        Toast.makeText(mContext, "X??a danh m???c " + p.getName().toString() + " th??nh c??ng!", Toast.LENGTH_SHORT).show();
    }
    // x??a danh m???c con
    private void removeChildCate(ParentCategoryClass p, ChildCategoryClass c){

       // p.getChildCategories().remove(Integer.parseInt(c.getId()));
        for(int i = 0; i<p.getChildCategories().size(); i++){
            if(p.getChildCategories().get(i).getId().equals(c.getId())){
                p.getChildCategories().remove(i);
            }
        }
        userRef = database.getReference(userId);
        CategoryRef = userRef.child("Category");
        CategoryRef.child(p.getId()).setValue(p);
        Toast.makeText(mContext, "X??a danh m???c " + c.getName().toString() + " th??nh c??ng!", Toast.LENGTH_SHORT).show();
    }




}
