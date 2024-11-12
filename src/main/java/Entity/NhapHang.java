/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class NhapHang {
    private String maNhap;
    private String maSP;
    private String ngayNhap;
    private int soLuong;

    public NhapHang() {
    }

    public NhapHang(String maNhap, String maSP, String ngayNhap, int soLuong) {
        this.maNhap = maNhap;
        this.maSP = maSP;
        this.ngayNhap = ngayNhap;
        this.soLuong = soLuong;
    }

    public String getMaNhap() {
        return maNhap;
    }

    public void setMaNhap(String maNhap) {
        this.maNhap = maNhap;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "NhapHang{" + "maNhap=" + maNhap + ", maSP=" + maSP + ", ngayNhap=" + ngayNhap + ", soLuong=" + soLuong + '}';
    }
}
