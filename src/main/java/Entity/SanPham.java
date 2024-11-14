/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;
import Map.MapLoaiSanPham;
/**
 *
 * @author ADMIN
 */
public class SanPham {
    private String maSP;
    private String maLoai;
    private String tenSP;
    private double donGia;
    private int soLuong;
    private String mauSac;
    private String chatLieu;
    private String size;
    private String hinhAnh;
    private boolean trangThai;

    public SanPham() {
    }

    public SanPham(String maSP, String maLoai, String tenSP, double donGia, int soLuong, String mauSac, String chatLieu, String size, String hinhAnh, boolean trangThai) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.size = size;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", maLoai=" + maLoai + ", tenSP=" + tenSP + ", donGia=" + donGia + ", soLuong=" + soLuong + ", mauSac=" + mauSac + ", chatLieu=" + chatLieu + ", size=" + size + ", hinhAnh=" + hinhAnh + ", trangThai=" + trangThai + '}';
    }
    
//    public Object[] getObjectArray() {
//        return new Object[]{
//            maSP,
//            maLoai,
//            tenSP,
//            donGia,donGia,
//            soLuong,
//            mauSac,
//            chatLieu,
//            size,hinhAnh,
//            trangThai
//        };
//    }
//    
//    public Map<String, String> getDataMap() {
//        Map<String, String> map = new TreeMap<>();
//        LoaiSanPhamDAO dao = new LoaiSanPhamDAO();
//        List<LoaiSanPham> list = dao.getAllData();
//        
//        for (LoaiSanPham o : list) {
//            map.put(
//                    o.getMaLoai(), 
//                    o.getTenLoai()
//            );
//        }
//        
//        return map;
//    }
//    
//    public String getTenByMa(String ma) {
//        String tenLoai = null;
//        Map<String, String> map = getDataMap();
//        
//        for (Map.Entry<String, String> o : map.entrySet()){
//            if (o.getKey().equalsIgnoreCase(ma)){
//                tenLoai = o.getValue();
//            }
//        }
//        
//        return tenLoai;
//    }
    
    public Object[] getObjectSanPham() {
        return new Object[]{
            getMaSP(),
            MapLoaiSanPham.getTenByMa(maSP),
            getTenSP(),
            getDonGia(),
            getSoLuong(),
            isTrangThai()?"Còn hàng":"Hết hàng"
        };
    }
}
