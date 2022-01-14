package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.MisstionNewActivity;
import com.example.myapplication.Activity.AddChildCategoryActivity;
import com.example.myapplication.Activity.AddFatherCategoryActivity;
import com.example.myapplication.Activity.AddReminderActivity;
import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Adapter.MissionAdapter;
import com.example.myapplication.Adapter.MissionFinishedAdapter;
import com.example.myapplication.Object.ChildCategoryClass;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.Object.ScheduleClass;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    static private ExpandableListView exListview;
    private List<ParentCategoryClass> parentList = new ArrayList<>();
    private HashMap<ParentCategoryClass,List<ChildCategoryClass>> childList
            = new HashMap<ParentCategoryClass,List<ChildCategoryClass>>();
    List<String> mKeys = new ArrayList<String>();
    private LinearLayout lnIconText;
    static ListView lvMission;
    static List<MissionClass> listMission = new ArrayList<>();
    static MissionAdapter adapterMission;

    MissionFinishedAdapter adapterMissonFinished;
    private List<String> MissionParentList;
    private HashMap<String,List<String>> MissionChildList;
    private ExpandableListView elvMissionFinish;
    //private FloatingActionButton btnMoveToAddScheduleActivityDaily;
    static private FirebaseDatabase database = FirebaseDatabase.getInstance();
    static private DatabaseReference userRef;
    static private DatabaseReference CategoryRef;
    private ImageButton ibAddFatherCate, ibAddChildCate;
    static String userId = MainActivity.userId;
    static ParentCategoryClass parent;
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
        setHasOptionsMenu(true); // tạo option
        // Tạo ra toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(CategoryToolbar);
        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).setTitle("Các danh mục");
        }
       // Data ExpandableListView
//        showList();

        initFirebase();

        adapter = new CategoryAdapter(getContext(),parentList,childList);

        exListview.setAdapter(adapter);

//       Mặc định sổ ra cái list

        adapter.notifyDataSetChanged();

       // Mở slide bar khi click
        openNavClickParent();
        openNavClickChild();

        // mở activity thêm danh mục
        /*btnMoveToAddScheduleActivityDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mView.getContext(), AddFatherCategoryActivity.class));
            }
        });*/

        ibAddFatherCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mView.getContext(), AddFatherCategoryActivity.class));
            }
        });
        // mở activity thêm danh mục con
        ibAddChildCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mView.getContext(), AddChildCategoryActivity.class));
            }
        });


        lnIconText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MisstionNewActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();

            }
        });
        // Lấy dữ liệu class Mission
//        getDataMission();
        // Adapter của Listview
//        adapterMission = new MissionAdapter(getContext(),R.layout.misson_row,listMission);
//        lvMission.setAdapter(adapterMission);
//        adapterMission.notifyDataSetChanged();
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



    public static void removeMission(MissionClass s) {
        userRef = database.getReference(userId);
        CategoryRef = userRef.child("Category");
//        CategoryRef.child(parent.getId()).child();
        Toast.makeText(adapterMission.getContext(), ""+parent, Toast.LENGTH_SHORT).show();
        CategoryRef.child(parent.getId()).child("missions").child(s.getId()).removeValue();
        listMission.remove(listMission.indexOf(s));
        adapterMission.notifyDataSetChanged();
    }

    private void init(){
        CategoryToolbar = (Toolbar) mView.findViewById(R.id.categoryToolbar);
        exListview = mView.findViewById(R.id.exListview);
        drawerLayout = mView.findViewById(R.id.drawerLayout);
        lnIconText = mView.findViewById(R.id.lnIconText);
        lvMission = mView.findViewById(R.id.lvMission);
        elvMissionFinish= mView.findViewById(R.id.elvMissionFinish);
        //btnMoveToAddScheduleActivityDaily = mView.findViewById(R.id.btnMoveToAddScheduleActivityDaily);
        ibAddChildCate= mView.findViewById(R.id.ibAddChildCate);
        ibAddFatherCate = mView.findViewById(R.id.ibAddFatherCate);



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
                parent = (ParentCategoryClass)adapter.getGroup(i);
                listMission = parent.getMissions();
                while (listMission.remove(null)) {
                }
//                Toast.makeText(getContext(), ""+listMission, Toast.LENGTH_SHORT).show();
                adapterMission = new MissionAdapter(getContext(),R.layout.misson_row,listMission);
                lvMission.setAdapter(adapterMission);
                adapterMission.notifyDataSetChanged();
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
//    private void showList() {
//        parentList = new ArrayList<>();
//
//        initFirebase();
//
//
////        parentList.add(new ParentCategoryClass(
////                "1",
////                "Nhan",
////                R.drawable.ic_baseline_folder_24,
////                123123,
////                new ArrayList<ChildCategoryClass>(),
////                new ArrayList<MissionClass>(),
////                new ArrayList<ScheduleClass>()
////        ));
////
////
////        List<ChildCategoryClass> child = new ArrayList<>();
////        ChildCategoryClass c = new ChildCategoryClass("",
////                "213",
////                R.drawable.ic_baseline_folder_24,
////                123, new ArrayList<MissionClass>()
////                , new ArrayList<ScheduleClass>());
////        child.add(c);
//
//        Toast.makeText(getContext(), "parentList"+parentList, Toast.LENGTH_SHORT).show();
//        parentList.forEach((element) -> {
//            childList.put(element,element.getChildCategories());
//        });
//
//
//    }


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


    public void initFirebase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = "";
        if (user != null) {
            uid = user.getUid();
        }
        if(!(uid).isEmpty()) {
            userRef = database.getReference(uid);
            CategoryRef = userRef.child("Category");
            CategoryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    ParentCategoryClass p = snapshot.getValue(ParentCategoryClass.class);
                    if (p != null) {
                        parentList.add(p);
                        childList.put(parentList.get(parentList.indexOf(p)),p.getChildCategories());

                        String key = snapshot.getKey();
                        mKeys.add(key);
                        for(int i=0; i < adapter.getGroupCount(); i++)
                            exListview.expandGroup(i);
                        adapter.notifyDataSetChanged();


                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    ParentCategoryClass p =snapshot.getValue(ParentCategoryClass.class);

                    if (p == null || parentList == null || parentList.isEmpty() ) {
                        return;
                    }

                    String key = snapshot.getKey();
                    int index = mKeys.indexOf(key);
                    parentList.set(index, p);
                    childList.put(parentList.get(parentList.indexOf(p)),p.getChildCategories());
                    adapter.notifyDataSetChanged();
                  //  adapterMission.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    ParentCategoryClass p = snapshot.getValue(ParentCategoryClass.class);
                    if (p == null || parentList == null || parentList.isEmpty() ) {
                        return;
                    }
                    String key = snapshot.getKey();
                    int index = mKeys.indexOf(key);
                    if (index != -1) {
                        parentList.remove(index);
                        mKeys.remove(index);
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }


}