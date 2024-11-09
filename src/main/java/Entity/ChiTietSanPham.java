/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPham {
    private String maCTSP;
    private String maSP;
    private String mauSac;
    private String chatLieu;
    private String size;
    private String hinhAnh;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maCTSP, String maSP, String mauSac, String chatLieu, String size, String hinhAnh) {
        this.maCTSP = maCTSP;
        this.maSP = maSP;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.size = size;
        this.hinhAnh = hinhAnh;
    }

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
