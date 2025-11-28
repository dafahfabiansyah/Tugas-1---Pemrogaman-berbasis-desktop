// ========================================
// CLASS MINUMAN extends MenuItem
// [TUGAS 3 - INHERITANCE]
// Fungsi: Subclass untuk menu minuman
//         Override method tampilMenu() untuk menampilkan info minuman
// ========================================
class Minuman extends MenuItem {
    private String jenisMinuman; // Atribut tambahan khusus minuman

    // Constructor
    public Minuman(int id, String nama, double harga, String jenisMinuman) {
        super(id, "Minuman", nama, harga);
        this.jenisMinuman = jenisMinuman;
    }

    // Constructor tanpa jenisMinuman (default)
    public Minuman(int id, String nama, double harga) {
        super(id, "Minuman", nama, harga);
        this.jenisMinuman = "Umum";
    }

    // Getter & Setter
    public String getJenisMinuman() {
        return jenisMinuman;
    }

    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }

    // Override method abstract dari MenuItem
    @Override
    public void tampilMenu() {
        System.out.printf("%d. %s - Rp %.0f (%s)%n", 
            getId(), getNama(), getHarga(), jenisMinuman);
    }

    // Override untuk file I/O
    @Override
    public String getInfoLengkap() {
        return super.getInfoLengkap() + "," + jenisMinuman;
    }
}
