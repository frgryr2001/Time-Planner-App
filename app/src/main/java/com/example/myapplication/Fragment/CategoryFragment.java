package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Object.CategoryClass;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;

    private Toolbar CategoryToolbar;
    DrawerLayout drawerLayout;
    CategoryAdapter adapter;
    private ExpandableListView exListview;
    private List<String> parentList;
    private HashMap<String,List<String>> childList;


    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters



    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category, container, false);

        CategoryToolbar = (Toolbar) mView.findViewById(R.id.categoryToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(CategoryToolbar);
        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).setTitle("Các danh mục");
        }
        exListview = mView.findViewById(R.id.exListview);
        showList();

        adapter = new CategoryAdapter(getContext(),parentList,childList);
        exListview.setAdapter(adapter);

        for(int i=0; i < adapter.getGroupCount(); i++)
            exListview.expandGroup(i);
        adapter.notifyDataSetChanged();
        drawerLayout = mView.findViewById(R.id.drawerLayout);
        openNavClickParent();
        openNavClickChild();


        return mView;
    }

    private void openNavClickChild() {
        exListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                drawerLayout.openDrawer(GravityCompat.END);
                return true;
            }
        });
    }

    private void openNavClickParent() {
        exListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                drawerLayout.openDrawer(GravityCompat.END);
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.save, menu);
        androidx.appcompat.widget.SearchView menuSearch = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.menuSearch).getActionView();
        menuSearch.setQueryHint("Tìm kiếm");
        menuSearch.setMaxWidth(Integer.MAX_VALUE);
        super.onCreateOptionsMenu(menu, inflater);

    }
    private void showList() {
        parentList = new ArrayList<>();
        childList = new HashMap<String,List<String>>();
        parentList.add("Hoc van 1");
        parentList.add("Hoc van 2");


        List<String> child = new ArrayList<>();
        child.add("123");
        child.add("456");
        child.add("678");
        List<String> child1 = new ArrayList<>();
        child1.add("123");
        child1.add("4256");
        child1.add("678");

        childList.put(parentList.get(0),child);
        childList.put(parentList.get(1),child1);
    }



}