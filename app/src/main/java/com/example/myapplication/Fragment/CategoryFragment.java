package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.MisstionNewActivity;
import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Adapter.MissionAdapter;
import com.example.myapplication.Adapter.MissionFinishedAdapter;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.R;

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
    private LinearLayout lnIconText;
    static ListView lvMission;
    static List<MissionClass> listMission;
    static MissionAdapter adapterMission;

    MissionFinishedAdapter adapterMissonFinished;
    private List<String> MissionParentList;
    private HashMap<String,List<String>> MissionChildList;
    private ExpandableListView elvMissionFinish;

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
        init();

        // Tạo ra toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(CategoryToolbar);
        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).setTitle("Các danh mục");
        }
       // Data ExpandableListView
        showList();

        adapter = new CategoryAdapter(getContext(),parentList,childList);
        exListview.setAdapter(adapter);

       //Mặc định sổ ra cái list
        for(int i=0; i < adapter.getGroupCount(); i++)
            exListview.expandGroup(i);
        adapter.notifyDataSetChanged();

       // Mở slide bar khi click
        openNavClickParent();
        openNavClickChild();

        lnIconText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MisstionNewActivity.class);
                startActivity(intent);

            }
        });
        // Lấy dữ liệu class Mission
        getDataMission();
        // Adapter của Listview
        adapterMission = new MissionAdapter(getContext(),R.layout.misson_row,listMission);
        lvMission.setAdapter(adapterMission);

        //Danh sách nhiệm vụ hoàn thành
        showListMission();
        adapterMissonFinished = new MissionFinishedAdapter(getContext(),MissionParentList,MissionChildList);
        elvMissionFinish.setAdapter(adapterMissonFinished);
        //Mặc định sổ ra cái list

        elvMissionFinish.expandGroup(0);
        adapterMissonFinished.notifyDataSetChanged();
        // end
        return mView;
    }



    public static void removeMission(int position) {
        listMission.remove(position);
        adapterMission.notifyDataSetChanged();
    }

    private void init(){
        CategoryToolbar = (Toolbar) mView.findViewById(R.id.categoryToolbar);
        exListview = mView.findViewById(R.id.exListview);
        drawerLayout = mView.findViewById(R.id.drawerLayout);
        lnIconText = mView.findViewById(R.id.lnIconText);
        lvMission = mView.findViewById(R.id.lvMission);
        elvMissionFinish= mView.findViewById(R.id.elvMissionFinish);
    }
    // ListView nhiệm vụ get data
    private void getDataMission(){
        listMission = MissionClass.init();
    }
    private void openNavClickChild() {
        exListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                drawerLayout.openDrawer(GravityCompat.END);
                return false;
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
    private void showListMission() {

        MissionParentList = new ArrayList<>();
        MissionChildList = new HashMap<String,List<String>>();

        MissionParentList.add("Đã hoàn thành1");
        List<String> child = new ArrayList<>();
        child.add("Đi học1");
        child.add("Đi chơi1");
        child.add("Đi đá banh1");

        MissionChildList.put(MissionParentList.get(0),child);

    }
}