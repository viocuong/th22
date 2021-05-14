package com.example.nguyenvancuong_thuchanh2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nguyenvancuong_thuchanh2_bai2.model.Quyengop;

import java.util.ArrayList;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnAdd,btnBack,btnChonngay;
    private SqlOpenHelp db ;
    private EditText txtName, txtSotien;
    private String date="14/5/2021";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        spinner = findViewById(R.id.spiner);
        ArrayList<String> thanhpho = new ArrayList<>();
        db = new SqlOpenHelp(this);
        txtName = findViewById(R.id.txtName);
        txtSotien = findViewById(R.id.txtSotien);
        btnChonngay =findViewById(R.id.btnDate);
        btnAdd  =findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        btnChonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog p = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = ""+dayOfMonth+"/"+month+"/"+year;
                    }
                },2021,5,4);
                p.show();
            }
        });

        thanhpho.add("Hung Yen");
        thanhpho.add("Ha Noi");
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,thanhpho));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quyengop m = new Quyengop(txtName.getText().toString(),date,spinner.getSelectedItem().toString(),Double.parseDouble(txtSotien.getText().toString()));
                db.add(m);
                Intent intent = new Intent();
                setResult(27);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(27);
                finish();
            }
        });

    }
}