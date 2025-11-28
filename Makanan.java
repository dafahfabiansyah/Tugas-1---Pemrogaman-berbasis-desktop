// ========================================
// CLASS MAKANAN extends MenuItem
// [TUGAS 3 - INHERITANCE]
// Fungsi: Subclass untuk menu makanan
//         Override method tampilMenu() untuk menampilkan info makanan
// ========================================
class Makanan extends MenuItem {
    private String jenisMakanan; // Atribut tambahan khusus makanan

    // Constructor
    public Makanan(int id, String nama, double harga, String jenisMakanan) {
        super(id, "Makanan", nama, harga);
        this.jenisMakanan = jenisMakanan;
    }

    // Constructor tanpa jenisMakanan (default)
    public Makanan(int id, String nama, double harga) {
        super(id, "Makanan", nama, harga);
        this.jenisMakanan = "Umum";
    }

    // Getter & Setter
    public String getJenisMakanan() {
        return jenisMakanan;
    }

    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }

    // Override method abstract dari MenuItem
    @Override
    public void tampilMenu() {
        System.out.printf("%d. %s - Rp %.0f (%s)%n", 
            getId(), getNama(), getHarga(), jenisMakanan);
    }

    // Override untuk file I/O
    @Override
    public String getInfoLengkap() {
        return super.getInfoLengkap() + "," + jenisMakanan;
    }
}
