import java.util.ArrayList;

// ========================================
// CLASS PESANAN
// Fungsi: Mengelola pesanan pelanggan
//         - Menyimpan item-item yang dipesan
//         - Menghitung total biaya
//         - Menerapkan diskon dan pajak
//         - Mencetak struk
// ========================================
class Pesanan {
    // Encapsulation - atribut private
    private ArrayList<MenuItem> itemPesanan;
    private ArrayList<Integer> jumlahPesanan;

    // Constructor
    public Pesanan() {
        this.itemPesanan = new ArrayList<>();
        this.jumlahPesanan = new ArrayList<>();
    }

    // Tambah item ke pesanan
    public void tambahItem(MenuItem item, int jumlah) {
        itemPesanan.add(item);
        jumlahPesanan.add(jumlah);
    }

    // Hapus item dari pesanan
    public void hapusItem(int index) {
        try {
            itemPesanan.remove(index);
            jumlahPesanan.remove(index);
            System.out.println("✅ Item berhasil dihapus dari pesanan.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Error: Item tidak ditemukan! Index tidak valid.");
        }
    }

    // Clear semua pesanan
    public void clearPesanan() {
        itemPesanan.clear();
        jumlahPesanan.clear();
    }

    // Hitung subtotal (sebelum pajak & biaya)
    public double hitungSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < itemPesanan.size(); i++) {
            subtotal += itemPesanan.get(i).getHarga() * jumlahPesanan.get(i);
        }
        return subtotal;
    }

    // Hitung pajak (10%)
    public double hitungPajak() {
        return hitungSubtotal() * 0.10;
    }

    // Biaya pelayanan
    public double getBiayaPelayanan() {
        return 20000;
    }

    // Hitung potongan minuman gratis (beli 1 gratis 1)
    public double hitungPotonganMinuman() {
        double hargaMinumanTermurah = 0;
        
        for (MenuItem item : itemPesanan) {
            if (item.getKategori().equals("Minuman")) {
                if (hargaMinumanTermurah == 0 || item.getHarga() < hargaMinumanTermurah) {
                    hargaMinumanTermurah = item.getHarga();
                }
            }
        }
        
        return hargaMinumanTermurah;
    }

    // Hitung diskon 10% jika subtotal > 100.000
    public double hitungDiskon() {
        double subtotal = hitungSubtotal();
        if (subtotal > 100000) {
            double totalSebelumDiskon = subtotal + hitungPajak() + getBiayaPelayanan();
            return totalSebelumDiskon * 0.10;
        }
        return 0;
    }

    // Hitung total bayar akhir
    public double hitungTotalBayar() {
        double subtotal = hitungSubtotal();
        double pajak = hitungPajak();
        double biayaPelayanan = getBiayaPelayanan();
        double totalSebelumDiskon = subtotal + pajak + biayaPelayanan;
        
        double potonganMinuman = 0;
        if (subtotal > 50000) {
            potonganMinuman = hitungPotonganMinuman();
        }
        
        double diskon = hitungDiskon();
        
        return totalSebelumDiskon - potonganMinuman - diskon;
    }

    // Cetak struk pembayaran
    public String cetakStruk() {
        StringBuilder struk = new StringBuilder();
        
        struk.append("\n==============================================\n");
        struk.append("            STRUK PEMBAYARAN\n");
        struk.append("==============================================\n");

        // Tampilkan semua pesanan
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int jumlah = jumlahPesanan.get(i);
            double totalItem = item.getHarga() * jumlah;
            struk.append(String.format("%s x %d = Rp %.0f%n", 
                item.getNama(), jumlah, totalItem));
        }

        double subtotal = hitungSubtotal();
        double pajak = hitungPajak();
        double biayaPelayanan = getBiayaPelayanan();
        double totalSebelumDiskon = subtotal + pajak + biayaPelayanan;

        struk.append("----------------------------------------------\n");
        struk.append(String.format("Subtotal: Rp %.0f%n", subtotal));
        struk.append(String.format("Pajak (10%%): Rp %.0f%n", pajak));
        struk.append(String.format("Biaya Pelayanan: Rp %.0f%n", biayaPelayanan));
        struk.append("----------------------------------------------\n");
        struk.append(String.format("Total: Rp %.0f%n", totalSebelumDiskon));

        // Cek penawaran dan diskon
        double potonganMinuman = 0;
        if (subtotal > 50000) {
            potonganMinuman = hitungPotonganMinuman();
            if (potonganMinuman > 0) {
                struk.append("\nPENAWARAN SPESIAL!\n");
                struk.append(String.format("Beli 1 Gratis 1 Minuman: -Rp %.0f%n", potonganMinuman));
            }
        }

        double diskon = hitungDiskon();
        if (diskon > 0) {
            struk.append(String.format("Diskon 10%%: -Rp %.0f%n", diskon));
        }

        double totalAkhir = hitungTotalBayar();
        struk.append("----------------------------------------------\n");
        struk.append(String.format("TOTAL BAYAR: Rp %.0f%n", totalAkhir));
        struk.append("==============================================\n");
        struk.append("     TERIMA KASIH ATAS KUNJUNGAN ANDA!\n");
        struk.append("==============================================\n");

        return struk.toString();
    }

    // Getter untuk jumlah item
    public int getJumlahItem() {
        return itemPesanan.size();
    }

    // Getter untuk item pesanan
    public ArrayList<MenuItem> getItemPesanan() {
        return itemPesanan;
    }

    // Getter untuk jumlah pesanan
    public ArrayList<Integer> getJumlahPesanan() {
        return jumlahPesanan;
    }
}
