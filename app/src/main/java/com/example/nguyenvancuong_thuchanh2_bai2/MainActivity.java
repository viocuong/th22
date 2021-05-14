package com.example.nguyenvancuong_thuchanh2_bai2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvancuong_thuchanh2_bai2.model.Quyengop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SqlOpenHelp db;
    private RecyclerView rcv;
    private TextView tongtien;
    private FloatingActionButton fAdd;
    private List<Quyengop> list = new ArrayList<>();
    private RcvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        updateRcv();

    }
    public void initView(){
        db = new SqlOpenHelp(this);
        tongtien = findViewById(R.id.tongtienquyengop);
        rcv = findViewById(R.id.rcv);
        fAdd = findViewById(R.id.fadd);
        adapter = new RcvAdapter(this,list,db);
        rcv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new RcvAdapter(this,list,db);
        fAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,22);
            }
        });

    }
    public void updateRcv(){
        List<Quyengop> l=db.getAll();
        adapter.setList(l);
        adapter.notifyDataSetChanged();
        tongtien.setText(""+adapter.getSotien());
        rcv.setAdapter(adapter);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateRcv();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.mSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Quyengop> list=db.search(newText);
                adapter.setList(list);
                rcv.setAdapter(adapter);
                tongtien.setText(""+adapter.getSotien());

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}