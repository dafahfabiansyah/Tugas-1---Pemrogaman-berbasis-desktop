// ========================================
// CLASS DISKON extends MenuItem
// Fungsi: Subclass untuk item diskon
//         Override method tampilMenu() untuk menampilkan info diskon
// ========================================
class Diskon extends MenuItem {
    private double persenDiskon; // Atribut tambahan khusus diskon

    // Constructor
    public Diskon(int id, String nama, double persenDiskon) {
        super(id, "Diskon", nama, 0); // Harga 0 untuk diskon
        this.persenDiskon = persenDiskon;
    }

    // Getter & Setter
    public double getPersenDiskon() {
        return persenDiskon;
    }

    public void setPersenDiskon(double persenDiskon) {
        this.persenDiskon = persenDiskon;
    }

    // Override method abstract dari MenuItem
    @Override
    public void tampilMenu() {
        System.out.printf("%d. %s - Diskon %.0f%%%n", 
            getId(), getNama(), persenDiskon);
    }

    // Override untuk file I/O
    @Override
    public String getInfoLengkap() {
        return getId() + ",Diskon," + getNama() + "," + persenDiskon;
    }
}
