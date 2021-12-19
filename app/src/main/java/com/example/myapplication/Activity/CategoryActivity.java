package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Object.CategoryClass;
import com.example.myapplication.R;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView rvCategory;
    CategoryAdapter adapter;
    List<CategoryClass> Lstcategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvCategory = findViewById(R.id.rvCategory);
        Lstcategory = CategoryClass.initList();
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this, Lstcategory);
        rvCategory.setAdapter(adapter);
    }
}