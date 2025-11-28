import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    
    // ArrayList menu restoran menggunakan class Menu
    static ArrayList<Menu> menuList = new ArrayList<>();
    static int nextId = 9;

    static Scanner scanner = new Scanner(System.in);
    static Pesanan pesananAktif = new Pesanan();

    // Inisialisasi menu awal
    static {
        // Load menu dari file, jika tidak ada gunakan default
        ArrayList<Menu> loadedMenu = FileManager.loadMenuDariFile();
        
        if (loadedMenu.isEmpty()) {
            // Menu default
            menuList.add(new Menu(1, "Makanan", "Nasi Padang", 25000));
            menuList.add(new Menu(2, "Makanan", "Mie Ayam", 15000));
            menuList.add(new Menu(3, "Makanan", "Nasi Goreng", 20000));
            menuList.add(new Menu(4, "Makanan", "Ayam Bakar", 30000));
            menuList.add(new Menu(5, "Minuman", "Es Teh", 5000));
            menuList.add(new Menu(6, "Minuman", "Kopi", 12000));
            menuList.add(new Menu(7, "Minuman", "Jus Jeruk", 15000));
            menuList.add(new Menu(8, "Minuman", "Es Campur", 18000));
        } else {
            menuList = loadedMenu;
            // Update nextId
            for (Menu menu : menuList) {
                if (menu.getId() >= nextId) {
                    nextId = menu.getId() + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI RESTORAN NUSANTARA");
        System.out.println("       [TUGAS 3 - OOP & FILE I/O]");
        System.out.println("==============================================\n");

        menuUtama();
        
        scanner.close();
    }

    // ========================================
    // MENU UTAMA
    // ========================================
    static void menuUtama() {
        while (true) {
            try {
                System.out.println("\n==============================================");
                System.out.println("              MENU UTAMA");
                System.out.println("==============================================");
                System.out.println("1. Menu Pelanggan (Pemesanan)");
                System.out.println("2. Menu Pemilik Restoran (Manajemen)");
                System.out.println("3. Lihat Riwayat Struk");
                System.out.println("4. Simpan Menu ke File");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu (1-5): ");
                
                String pilihan = scanner.nextLine().trim();
                
                if (pilihan.equals("1")) {
                    menuPelanggan();
                } else if (pilihan.equals("2")) {
                    menuPemilik();
                } else if (pilihan.equals("3")) {
                    FileManager.tampilkanStrukDariFile();
                } else if (pilihan.equals("4")) {
                    FileManager.simpanMenuKeFile(menuList);
                } else if (pilihan.equals("5")) {
                    // Simpan menu sebelum keluar
                    FileManager.simpanMenuKeFile(menuList);
                    System.out.println("\n==============================================");
                    System.out.println("  TERIMA KASIH TELAH MENGGUNAKAN APLIKASI!");
                    System.out.println("==============================================");
                    break;
                } else {
                    System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-5.");
                }
            } catch (Exception e) {
                System.out.println("❌ Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    // ========================================
    // MENU PELANGGAN
    // ========================================
    static void menuPelanggan() {
        try {
            // Reset pesanan
            pesananAktif = new Pesanan();

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

            // Cetak struk jika ada pesanan
            if (pesananAktif.getJumlahItem() > 0) {
                String struk = pesananAktif.cetakStruk();
                System.out.println(struk);
                
                // Simpan struk ke file
                FileManager.simpanStrukKeFile(struk);
            } else {
                System.out.println("\n❌ Tidak ada pesanan yang dibuat.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error di menu pelanggan: " + e.getMessage());
        }
    }

    // ========================================
    // MENU PEMILIK
    // ========================================
    static void menuPemilik() {
        while (true) {
            try {
                System.out.println("\n==============================================");
                System.out.println("        MENU PEMILIK RESTORAN");
                System.out.println("==============================================");
                System.out.println("1. Tambah Menu Baru");
                System.out.println("2. Ubah Harga Menu");
                System.out.println("3. Hapus Menu");
                System.out.println("4. Lihat Semua Menu");
                System.out.println("5. Simpan Menu ke File");
                System.out.println("6. Kembali ke Menu Utama");
                System.out.print("Pilih menu (1-6): ");
                
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
                    FileManager.simpanMenuKeFile(menuList);
                } else if (pilihan.equals("6")) {
                    break;
                } else {
                    System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-6.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error di menu pemilik: " + e.getMessage());
            }
        }
    }

    // ========================================
    // TAMPILKAN MENU UNTUK PELANGGAN
    // ========================================
    static void tampilkanMenu() {
        System.out.println("-------------- MENU MAKANAN --------------");
        for (Menu menu : menuList) {
            if (menu.getCategory().equals("Makanan")) {
                System.out.println(menu.getId() + ". " + menu.getName() + " - Rp " + menu.getPrice());
            }
        }
        
        System.out.println("\n-------------- MENU MINUMAN --------------");
        for (Menu menu : menuList) {
            if (menu.getCategory().equals("Minuman")) {
                System.out.println(menu.getId() + ". " + menu.getName() + " - Rp " + menu.getPrice());
            }
        }
    }

    // ========================================
    // TAMPILKAN SEMUA MENU UNTUK PEMILIK
    // ========================================
    static void tampilkanSemuaMenu() {
        System.out.println("\n==============================================");
        System.out.println("           DAFTAR SEMUA MENU");
        System.out.println("==============================================");
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            System.out.println((i + 1) + ". [" + menu.getCategory() + "] " + menu.getName() + " - Rp " + menu.getPrice());
        }
        System.out.println("==============================================");
    }

    // ========================================
    // PROSES INPUT PESANAN - dengan Exception Handling
    // ========================================
    static void prosesInputPesanan() {
        while (true) {
            try {
                System.out.print("Pesanan: ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("selesai")) {
                    break;
                }
                
                if (!input.contains("=")) {
                    System.out.println("❌ Format tidak valid! Gunakan format: Nama Menu = Jumlah");
                    continue;
                }
                
                String[] parts = input.split("=");
                if (parts.length != 2) {
                    System.out.println("❌ Format tidak valid! Gunakan format: Nama Menu = Jumlah");
                    continue;
                }
                
                String namaMenu = parts[0].trim();
                String jumlahStr = parts[1].trim();
                
                // Exception handling untuk parsing integer
                int jumlah;
                try {
                    jumlah = Integer.parseInt(jumlahStr);
                    if (jumlah <= 0) {
                        System.out.println("❌ Jumlah harus lebih dari 0!");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Jumlah harus berupa angka! Input: '" + jumlahStr + "'");
                    continue;
                }
                
                // Cari menu - dengan exception handling
                Menu menuDitemukan = cariMenuByNama(namaMenu);
                if (menuDitemukan == null) {
                    System.out.println("❌ Menu '" + namaMenu + "' tidak ditemukan! Silakan coba lagi.");
                    continue;
                }
                
                // Konversi Menu ke MenuItem untuk Pesanan
                MenuItem item = new MenuItem(
                    menuDitemukan.getId(),
                    menuDitemukan.getCategory(),
                    menuDitemukan.getName(),
                    menuDitemukan.getPrice()
                ) {
                    @Override
                    public void tampilMenu() {
                        System.out.println(getId() + ". " + getNama() + " - Rp " + getHarga());
                    }
                };
                
                pesananAktif.tambahItem(item, jumlah);
                System.out.println("✅ " + menuDitemukan.getName() + " x" + jumlah + " ditambahkan ke pesanan.");
                
            } catch (Exception e) {
                System.out.println("❌ Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    // ========================================
    // CARI MENU BY NAMA
    // ========================================
    static Menu cariMenuByNama(String nama) {
        for (Menu menu : menuList) {
            if (menu.getName().equalsIgnoreCase(nama)) {
                return menu;
            }
        }
        return null;
    }

    // ========================================
    // TAMBAH MENU BARU - dengan Exception Handling
    // ========================================
    static void tambahMenu() {
        try {
            System.out.println("\n=== TAMBAH MENU BARU ===");
            
            System.out.print("Nama Menu: ");
            String nama = scanner.nextLine().trim();
            
            if (nama.isEmpty()) {
                System.out.println("❌ Nama menu tidak boleh kosong!");
                return;
            }
            
            System.out.print("Kategori (Makanan/Minuman): ");
            String kategori = scanner.nextLine().trim();
            
            while (!kategori.equalsIgnoreCase("Makanan") && !kategori.equalsIgnoreCase("Minuman")) {
                System.out.println("❌ Kategori harus 'Makanan' atau 'Minuman'!");
                System.out.print("Kategori (Makanan/Minuman): ");
                kategori = scanner.nextLine().trim();
            }
            
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
            
            menuList.add(new Menu(nextId++, kategori, nama, harga));
            System.out.println("✅ Menu '" + nama + "' berhasil ditambahkan!");
            
        } catch (Exception e) {
            System.out.println("❌ Error menambah menu: " + e.getMessage());
        }
    }

    // ========================================
    // UBAH HARGA MENU - dengan Exception Handling
    // ========================================
    static void ubahHargaMenu() {
        try {
            System.out.println("\n=== UBAH HARGA MENU ===");
            tampilkanSemuaMenu();
            
            if (menuList.isEmpty()) {
                System.out.println("❌ Tidak ada menu untuk diubah!");
                return;
            }
            
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
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("❌ Nomor menu tidak valid!");
                    return;
                }
            }
            
            Menu menuDipilih = menuList.get(nomor - 1);
            System.out.println("\nMenu yang dipilih: " + menuDipilih.getName() + " (Harga saat ini: Rp " + menuDipilih.getPrice() + ")");
            
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
            
            System.out.print("Yakin ingin mengubah harga dari Rp " + menuDipilih.getPrice() + " menjadi Rp " + hargaBaru + "? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine().trim();
            
            if (konfirmasi.equalsIgnoreCase("Ya")) {
                menuDipilih.setPrice(hargaBaru);
                System.out.println("✅ Harga menu '" + menuDipilih.getName() + "' berhasil diubah menjadi Rp " + hargaBaru + "!");
            } else {
                System.out.println("❌ Perubahan harga dibatalkan.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error mengubah harga: " + e.getMessage());
        }
    }

    // ========================================
    // HAPUS MENU - dengan Exception Handling
    // ========================================
    static void hapusMenu() {
        try {
            System.out.println("\n=== HAPUS MENU ===");
            tampilkanSemuaMenu();
            
            if (menuList.isEmpty()) {
                System.out.println("❌ Tidak ada menu untuk dihapus!");
                return;
            }
            
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
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("❌ Nomor menu tidak valid!");
                    return;
                }
            }
            
            Menu menuDipilih = menuList.get(nomor - 1);
            System.out.println("\nMenu yang akan dihapus: " + menuDipilih.getName() + " - Rp " + menuDipilih.getPrice());
            
            System.out.print("Yakin ingin menghapus menu ini? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine().trim();
            
            if (konfirmasi.equalsIgnoreCase("Ya")) {
                String namaMenu = menuDipilih.getName();
                menuList.remove(nomor - 1);
                System.out.println("✅ Menu '" + namaMenu + "' berhasil dihapus!");
            } else {
                System.out.println("❌ Penghapusan menu dibatalkan.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error menghapus menu: " + e.getMessage());
        }
    }
}
