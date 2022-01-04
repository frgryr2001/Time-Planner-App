package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    CategoryAdapter adapter;
    private ExpandableListView exListview;
    private List<String> parentList;
    private HashMap<String,List<String>> childList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        Button algo_button = v.findViewById(R.id.algo_button);
        Button course_button = v.findViewById(R.id.course_button);

//        algo_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Toast.makeText(getActivity(),
//                        "Algorithm Shared", Toast.LENGTH_SHORT)
//                        .show();
//                dismiss();
//            }
//        });
//
//        course_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Toast.makeText(getActivity(),
//                        "Course Shared", Toast.LENGTH_SHORT)
//                        .show();
//                dismiss();
//            }
//        });
//        showList();
//        exListview = v.findViewById(R.id.exListview);
//        adapter = new CategoryAdapter(getContext(),parentList,childList);
//        exListview.setAdapter(adapter);
        return v;
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
