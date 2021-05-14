package com.example.nguyenvancuong_thuchanh2_bai2.model;

import java.io.Serializable;
import java.util.Date;

public class Quyengop implements Serializable {
    private int id;
    private String name;
    private String date;
    private String thanhpho;
    private double sotien;

    public Quyengop(int id, String name, String date, String thanhpho, double sotien) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.thanhpho = thanhpho;
        this.sotien = sotien;

    }

    public Quyengop(String name, String date, String thanhpho, double sotien) {
        this.name = name;
        this.date = date;
        this.thanhpho = thanhpho;
        this.sotien = sotien;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public double getSotien() {
        return sotien;
    }
}
