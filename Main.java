import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    // ArrayList menu restoran 
    static ArrayList<Menu> menuList = new ArrayList<>();
    static int nextId = 9; 

    
    static ArrayList<String> pesananNama = new ArrayList<>();
    static ArrayList<Integer> pesananJumlah = new ArrayList<>();
    static ArrayList<Integer> pesananHarga = new ArrayList<>();
    static ArrayList<String> pesananKategori = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    
    static {
        menuList.add(new Menu(1, "Makanan", "Nasi Padang", 25000));
        menuList.add(new Menu(2, "Makanan", "Mie Ayam", 15000));
        menuList.add(new Menu(3, "Makanan", "Nasi Goreng", 20000));
        menuList.add(new Menu(4, "Makanan", "Ayam Bakar", 30000));
        menuList.add(new Menu(5, "Minuman", "Es Teh", 5000));
        menuList.add(new Menu(6, "Minuman", "Kopi", 12000));
        menuList.add(new Menu(7, "Minuman", "Jus Jeruk", 15000));
        menuList.add(new Menu(8, "Minuman", "Es Campur", 18000));
    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI RESTORAN NUSANTARA");
        System.out.println("==============================================\n");

        menuUtama();
        
        scanner.close();
    }

    
    static void menuUtama() {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("              MENU UTAMA");
            System.out.println("==============================================");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pemilik Restoran (Manajemen)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            
            String pilihan = scanner.nextLine().trim();
            
            if (pilihan.equals("1")) {
                menuPelanggan();
            } else if (pilihan.equals("2")) {
                menuPemilik();
            } else if (pilihan.equals("3")) {
                System.out.println("\n==============================================");
                System.out.println("  TERIMA KASIH TELAH MENGGUNAKAN APLIKASI!");
                System.out.println("==============================================");
                break;
            } else {
                System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-3.");
            }
        }
    }

    static void menuPelanggan() {
        pesananNama.clear();
        pesananJumlah.clear();
        pesananHarga.clear();
        pesananKategori.clear();

        // Tampilkan menu
        tampilkanMenu();

        // Proses pemesanan
        System.out.println("\n==============================================");
        System.out.println("         PROSES PEMESANAN");
        System.out.println("==============================================");
        System.out.println("Masukkan pesanan Anda (tidak terbatas)");
        System.out.println("Format: Nama Menu = Jumlah");
        System.out.println("Ketik 'selesai' untuk mengakhiri pesanan\n");

        prosesInputPesanan();

        // Hitung total dan cetak struk
        if (pesananNama.size() > 0) {
            cetakStruk();
        } else {
            System.out.println("\n❌ Tidak ada pesanan yang dibuat.");
        }
    }

    // Menu Pemilik - untuk manajemen menu
    static void menuPemilik() {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("        MENU PEMILIK RESTORAN");
            System.out.println("==============================================");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Lihat Semua Menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-5): ");
            
            String pilihan = scanner.nextLine().trim();
            
            if (pilihan.equals("1")) {
                tambahMenu();
            } else if (pilihan.equals("2")) {
                ubahHargaMenu();
            } else if (pilihan.equals("3")) {
                hapusMenu();
            } else if (pilihan.equals("4")) {
                tampilkanSemuaMenu();
            } else if (pilihan.equals("5")) {
                break; // Kembali ke menu utama
            } else {
                System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-5.");
            }
        }
    }

    // Menampilkan menu berdasarkan kategori
    static void tampilkanMenu() {
        System.out.println("-------------- MENU MAKANAN --------------");
        for (Menu menu : menuList) {
            if (menu.Category.equals("Makanan")) {
                System.out.println(menu.Id + ". " + menu.Name + " - Rp " + menu.Price);
            }
        }
        
        System.out.println("\n-------------- MENU MINUMAN --------------");
        for (Menu menu : menuList) {
            if (menu.Category.equals("Minuman")) {
                System.out.println(menu.Id + ". " + menu.Name + " - Rp " + menu.Price);
            }
        }
    }

    // Menampilkan semua menu dengan nomor urut
    static void tampilkanSemuaMenu() {
        System.out.println("\n==============================================");
        System.out.println("           DAFTAR SEMUA MENU");
        System.out.println("==============================================");
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            System.out.println((i + 1) + ". [" + menu.Category + "] " + menu.Name + " - Rp " + menu.Price);
        }
        System.out.println("==============================================");
    }

    // Proses input pesanan (tidak terbatas)
    static void prosesInputPesanan() {
        while (true) {
            System.out.print("Pesanan: ");
            String input = scanner.nextLine().trim();
            
            // Cek jika user mengetik 'selesai'
            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            
            // Cek format input (harus ada tanda '=')
            if (!input.contains("=")) {
                System.out.println("❌ Format tidak valid! Gunakan format: Nama Menu = Jumlah");
                continue;
            }
            
            // Parse input
            String[] parts = input.split("=");
            if (parts.length != 2) {
                System.out.println("❌ Format tidak valid! Gunakan format: Nama Menu = Jumlah");
                continue;
            }
            
            String namaMenu = parts[0].trim();
            String jumlahStr = parts[1].trim();
            
            // Validasi jumlah (harus angka)
            int jumlah;
            try {
                jumlah = Integer.parseInt(jumlahStr);
                if (jumlah <= 0) {
                    System.out.println("❌ Jumlah harus lebih dari 0!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Jumlah harus berupa angka!");
                continue;
            }
            
            // Cari menu di menuList
            Menu menuDitemukan = cariMenuByNama(namaMenu);
            if (menuDitemukan == null) {
                System.out.println("❌ Menu '" + namaMenu + "' tidak ditemukan! Silakan coba lagi.");
                continue;
            }
            
            // Tambahkan ke pesanan
            pesananNama.add(menuDitemukan.Name);
            pesananJumlah.add(jumlah);
            pesananHarga.add(menuDitemukan.Price);
            pesananKategori.add(menuDitemukan.Category);
            
            System.out.println("✅ " + menuDitemukan.Name + " x" + jumlah + " ditambahkan ke pesanan.");
        }
    }

    // Mencari menu berdasarkan nama
    static Menu cariMenuByNama(String nama) {
        for (Menu menu : menuList) {
            if (menu.Name.equalsIgnoreCase(nama)) {
                return menu;
            }
        }
        return null;
    }

    // Tambah menu baru
    static void tambahMenu() {
        System.out.println("\n=== TAMBAH MENU BARU ===");
        
        System.out.print("Nama Menu: ");
        String nama = scanner.nextLine().trim();
        
        System.out.print("Kategori (Makanan/Minuman): ");
        String kategori = scanner.nextLine().trim();
        
        // Validasi kategori
        while (!kategori.equalsIgnoreCase("Makanan") && !kategori.equalsIgnoreCase("Minuman")) {
            System.out.println("❌ Kategori harus 'Makanan' atau 'Minuman'!");
            System.out.print("Kategori (Makanan/Minuman): ");
            kategori = scanner.nextLine().trim();
        }
        
        // Capitalize kategori
        kategori = kategori.substring(0, 1).toUpperCase() + kategori.substring(1).toLowerCase();
        
        System.out.print("Harga: ");
        int harga;
        while (true) {
            try {
                harga = Integer.parseInt(scanner.nextLine().trim());
                if (harga <= 0) {
                    System.out.println("❌ Harga harus lebih dari 0!");
                    System.out.print("Harga: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Harga harus berupa angka!");
                System.out.print("Harga: ");
            }
        }
        
        // Tambahkan menu
        menuList.add(new Menu(nextId++, kategori, nama, harga));
        System.out.println("✅ Menu '" + nama + "' berhasil ditambahkan!");
    }

    // Ubah harga menu
    static void ubahHargaMenu() {
        System.out.println("\n=== UBAH HARGA MENU ===");
        tampilkanSemuaMenu();
        
        System.out.print("Pilih nomor menu yang ingin diubah harganya: ");
        int nomor;
        
        while (true) {
            try {
                nomor = Integer.parseInt(scanner.nextLine().trim());
                if (nomor < 1 || nomor > menuList.size()) {
                    System.out.println("❌ Nomor tidak valid! Pilih antara 1-" + menuList.size());
                    System.out.print("Pilih nomor menu: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Input harus berupa angka!");
                System.out.print("Pilih nomor menu: ");
            }
        }
        
        Menu menuDipilih = menuList.get(nomor - 1);
        System.out.println("\nMenu yang dipilih: " + menuDipilih.Name + " (Harga saat ini: Rp " + menuDipilih.Price + ")");
        
        System.out.print("Harga baru: ");
        int hargaBaru;
        while (true) {
            try {
                hargaBaru = Integer.parseInt(scanner.nextLine().trim());
                if (hargaBaru <= 0) {
                    System.out.println("❌ Harga harus lebih dari 0!");
                    System.out.print("Harga baru: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Harga harus berupa angka!");
                System.out.print("Harga baru: ");
            }
        }
        
        // Konfirmasi
        System.out.print("Yakin ingin mengubah harga dari Rp " + menuDipilih.Price + " menjadi Rp " + hargaBaru + "? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine().trim();
        
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            menuDipilih.Price = hargaBaru;
            System.out.println("✅ Harga menu '" + menuDipilih.Name + "' berhasil diubah menjadi Rp " + hargaBaru + "!");
        } else {
            System.out.println("❌ Perubahan harga dibatalkan.");
        }
    }

    // Hapus menu
    static void hapusMenu() {
        System.out.println("\n=== HAPUS MENU ===");
        tampilkanSemuaMenu();
        
        System.out.print("Pilih nomor menu yang ingin dihapus: ");
        int nomor;
        
        while (true) {
            try {
                nomor = Integer.parseInt(scanner.nextLine().trim());
                if (nomor < 1 || nomor > menuList.size()) {
                    System.out.println("❌ Nomor tidak valid! Pilih antara 1-" + menuList.size());
                    System.out.print("Pilih nomor menu: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Input harus berupa angka!");
                System.out.print("Pilih nomor menu: ");
            }
        }
        
        Menu menuDipilih = menuList.get(nomor - 1);
        System.out.println("\nMenu yang akan dihapus: " + menuDipilih.Name + " - Rp " + menuDipilih.Price);
        
        // Konfirmasi
        System.out.print("Yakin ingin menghapus menu ini? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine().trim();
        
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            String namaMenu = menuDipilih.Name;
            menuList.remove(nomor - 1);
            System.out.println("✅ Menu '" + namaMenu + "' berhasil dihapus!");
        } else {
            System.out.println("❌ Penghapusan menu dibatalkan.");
        }
    }

    // Mencetak struk pembayaran
    static void cetakStruk() {
        System.out.println("\n==============================================");
        System.out.println("            STRUK PEMBAYARAN");
        System.out.println("==============================================");

        int subtotal = 0;

        // Tampilkan semua pesanan
        for (int i = 0; i < pesananNama.size(); i++) {
            int totalItem = pesananHarga.get(i) * pesananJumlah.get(i);
            System.out.println(pesananNama.get(i) + " x " + pesananJumlah.get(i) + " = Rp " + totalItem);
            subtotal += totalItem;
        }

        System.out.println("----------------------------------------------");
        System.out.println("Subtotal: Rp " + subtotal);

        // Hitung pajak 10%
        int pajak = subtotal * 10 / 100;
        System.out.println("Pajak (10%): Rp " + pajak);

        // Biaya pelayanan
        int biayaPelayanan = 20000;
        System.out.println("Biaya Pelayanan: Rp " + biayaPelayanan);

        // Total sebelum diskon
        int totalSebelumDiskon = subtotal + pajak + biayaPelayanan;
        System.out.println("----------------------------------------------");
        System.out.println("Total: Rp " + totalSebelumDiskon);

        // Cek penawaran dan diskon
        int diskon = 0;
        int potonganMinuman = 0;

        // Penawaran beli 1 gratis 1 minuman jika total > 50.000
        if (subtotal > 50000) {
            potonganMinuman = hitungPotonganMinuman();
            if (potonganMinuman > 0) {
                System.out.println("\nPENAWARAN SPESIAL!");
                System.out.println("Beli 1 Gratis 1 Minuman: -Rp " + potonganMinuman);
            }
        }

        // Diskon 10% jika total > 100.000
        if (subtotal > 100000) {
            diskon = totalSebelumDiskon * 10 / 100;
            System.out.println("Diskon 10%: -Rp " + diskon);
        }

        // Total akhir
        int totalAkhir = totalSebelumDiskon - potonganMinuman - diskon;
        System.out.println("----------------------------------------------");
        System.out.println("TOTAL BAYAR: Rp " + totalAkhir);
        System.out.println("==============================================");
        System.out.println("     TERIMA KASIH ATAS KUNJUNGAN ANDA!");
        System.out.println("==============================================");
    }

    // Menghitung potongan minuman (beli 1 gratis 1)
    static int hitungPotonganMinuman() {
        int hargaMinumanTermurah = 0;

        for (int i = 0; i < pesananKategori.size(); i++) {
            if (pesananKategori.get(i).equals("Minuman")) {
                if (hargaMinumanTermurah == 0 || pesananHarga.get(i) < hargaMinumanTermurah) {
                    hargaMinumanTermurah = pesananHarga.get(i);
                }
            }
        }

        return hargaMinumanTermurah;
    }
}
