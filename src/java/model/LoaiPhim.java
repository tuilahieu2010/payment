/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAO
 */
public class LoaiPhim {
    private int maLP;
    private String loaiPhim;

    public LoaiPhim() {
    }

    public LoaiPhim(int maLP, String loaiPhim) {
        this.maLP = maLP;
        this.loaiPhim = loaiPhim;
    }

    public int getMaLP() {
        return maLP;
    }

    public void setMaLP(int maLP) {
        this.maLP = maLP;
    }

    public String getLoaiPhim() {
        return loaiPhim;
    }

    public void setLoaiPhim(String loaiPhim) {
        this.loaiPhim = loaiPhim;
    }
}

