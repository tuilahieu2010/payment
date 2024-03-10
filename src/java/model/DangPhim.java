/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAO
 */
public class DangPhim {
    private int maDP;
    private String dangPhim;

    public DangPhim() {
    }

    public DangPhim(int maDP, String dangPhim) {
        this.maDP = maDP;
        this.dangPhim = dangPhim;
    }

    public int getMaDP() {
        return maDP;
    }

    public void setMaDP(int maDP) {
        this.maDP = maDP;
    }

    public String getDangPhim() {
        return dangPhim;
    }

    public void setDangPhim(String dangPhim) {
        this.dangPhim = dangPhim;
    }
}

