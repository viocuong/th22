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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenvancuong_thuchanh2_bai2.model.Quyengop;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnUpdate,btnBack,btnChonngay;
    private SqlOpenHelp db ;
    private EditText txtName, txtSotien;
    private String date="14/5/2021";
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        spinner = findViewById(R.id.spiner);
        ArrayList<String> thanhpho = new ArrayList<>();
        db = new SqlOpenHelp(this);
        tv = findViewById(R.id.tv);
        txtName = findViewById(R.id.txtName);
        txtSotien = findViewById(R.id.txtSotien);
        btnChonngay =findViewById(R.id.btnDate);
        btnUpdate  =findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBack);
        btnChonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog p = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        Quyengop m = (Quyengop) getIntent().getSerializableExtra("Quyengop");
        tv.setText(""+m.getId());
        txtName.setText(m.getName());
        txtSotien.setText(""+m.getSotien());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = m.getId();
                String name = txtName.getText().toString();
                String d = date;
                String tp = spinner.getSelectedItem().toString();
                double tien = Double.parseDouble(txtSotien.getText().toString());
                db.update(new Quyengop(id,name, d, tp, tien));
                Toast.makeText(UpdateActivity.this,""+id+" "+name+" "+tp+" "+date+" "+tien,Toast.LENGTH_LONG).show();
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