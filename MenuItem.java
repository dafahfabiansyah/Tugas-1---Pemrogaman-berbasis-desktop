// ========================================
// CLASS ABSTRACT MENUITEM
// Fungsi: Class abstrak sebagai parent untuk Makanan, Minuman, dan Diskon
//         Menerapkan inheritance dan polymorphism
// ========================================
abstract class MenuItem {
    // Encapsulation - semua atribut private
    private int id;
    private String nama;
    private double harga;
    private String kategori;

    // Constructor
    public MenuItem(int id, String kategori, String nama, double harga) {
        this.id = id;
        this.kategori = kategori;
        this.nama = nama;
        this.harga = harga;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    // Abstract method - harus diimplementasi oleh subclass
    public abstract void tampilMenu();
    
    // Method untuk mendapatkan info lengkap (untuk file I/O)
    public String getInfoLengkap() {
        return id + "," + kategori + "," + nama + "," + harga;
    }
}
