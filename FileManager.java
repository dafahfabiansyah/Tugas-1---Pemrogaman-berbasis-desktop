import java.io.*;
import java.util.ArrayList;

// ========================================
// CLASS FILEMANAGER
// Fungsi: Mengelola operasi file 
//         - Simpan daftar menu ke/dari file
//         - Simpan struk pesanan ke file
// ========================================
class FileManager {
    
    private static final String MENU_FILE = "menu_restoran.txt";
    private static final String STRUK_FILE = "struk_pesanan.txt";

    // ========================================
    // SIMPAN MENU KE FILE
    // ========================================
    public static void simpanMenuKeFile(ArrayList<Menu> menuList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MENU_FILE))) {
            writer.println("=== DAFTAR MENU RESTORAN ===");
            writer.println("Format: ID,Kategori,Nama,Harga\n");
            
            for (Menu menu : menuList) {
                writer.println(menu.getInfoLengkap());
            }
            
            System.out.println("✅ Menu berhasil disimpan ke file: " + MENU_FILE);
        } catch (IOException e) {
            System.out.println("❌ Error menyimpan menu ke file: " + e.getMessage());
        }
    }

    // ========================================
    // LOAD MENU DARI FILE
    // ========================================
    public static ArrayList<Menu> loadMenuDariFile() {
        ArrayList<Menu> menuList = new ArrayList<>();
        File file = new File(MENU_FILE);
        
        if (!file.exists()) {
            System.out.println("⚠️  File menu tidak ditemukan. Menggunakan menu default.");
            return menuList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                // Skip header lines
                if (lineNumber <= 2 || line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0].trim());
                        String kategori = parts[1].trim();
                        String nama = parts[2].trim();
                        int harga = Integer.parseInt(parts[3].trim());
                        
                        menuList.add(new Menu(id, kategori, nama, harga));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️  Baris " + lineNumber + " tidak valid, dilewati.");
                }
            }
            
            System.out.println("✅ Menu berhasil dimuat dari file: " + MENU_FILE);
            System.out.println("   Total menu: " + menuList.size());
            
        } catch (IOException e) {
            System.out.println("❌ Error membaca menu dari file: " + e.getMessage());
        }
        
        return menuList;
    }

    // ========================================
    // SIMPAN STRUK KE FILE
    // ========================================
    public static void simpanStrukKeFile(String strukContent) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STRUK_FILE, true))) {
            writer.println("=== STRUK PESANAN ===");
            writer.println("Tanggal: " + java.time.LocalDateTime.now());
            writer.println(strukContent);
            writer.println("\n" + "=".repeat(50) + "\n");
            
            System.out.println("✅ Struk berhasil disimpan ke file: " + STRUK_FILE);
        } catch (IOException e) {
            System.out.println("❌ Error menyimpan struk ke file: " + e.getMessage());
        }
    }

    // ========================================
    // TAMPILKAN ISI FILE STRUK
    // ========================================
    public static void tampilkanStrukDariFile() {
        File file = new File(STRUK_FILE);
        
        if (!file.exists()) {
            System.out.println("⚠️  Belum ada struk pesanan yang tersimpan.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n" + "=".repeat(50));
            System.out.println("    RIWAYAT STRUK PESANAN");
            System.out.println("=".repeat(50));
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            System.out.println("❌ Error membaca struk dari file: " + e.getMessage());
        }
    }

    // ========================================
    // HAPUS FILE STRUK (untuk reset)
    // ========================================
    public static void hapusFileStruk() {
        File file = new File(STRUK_FILE);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("✅ Riwayat struk berhasil dihapus.");
            } else {
                System.out.println("❌ Gagal menghapus riwayat struk.");
            }
        } else {
            System.out.println("⚠️  Tidak ada riwayat struk untuk dihapus.");
        }
    }
}
