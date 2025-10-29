import java.util.Scanner;

public class Main {

    //  menu restoran 
    static Menu[] menuList = {
        // Menu Makanan
        new Menu(1, "Makanan", "Nasi Padang", 25000),
        new Menu(2, "Makanan", "Mie Ayam", 15000),
        new Menu(3, "Makanan", "Nasi Goreng", 20000),
        new Menu(4, "Makanan", "Ayam Bakar", 30000),
        // Menu Minuman
        new Menu(5, "Minuman", "Es Teh", 5000),
        new Menu(6, "Minuman", "Kopi", 12000),
        new Menu(7, "Minuman", "Jus Jeruk", 15000),
        new Menu(8, "Minuman", "Es Campur", 18000)
    };

    // simpan pesanan 
    static String[] pesananNama = new String[4];
    static int[] pesananJumlah = new int[4];
    static int[] pesananHarga = new int[4];
    static String[] pesananKategori = new String[4];
    static int jumlahPesanan = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI RESTORAN APAAJASIH");
        System.out.println("==============================================\n");

        // Tampilkan menu
        tampilkanMenu();

        // Proses pemesanna
        System.out.println("\n==============================================");
        System.out.println("         PROSES PEMESANAN");
        System.out.println("==============================================");
        System.out.println("Silakan masukkan pesanan Anda (maksimal 4 menu)");
        System.out.println("Format: Nama Menu = Jumlah\n");

        prosesInputPesanan(scanner);

        // Hitung total dan cetak struk
        cetakStruk();

        scanner.close();
    }

    // tmpilkan menu berdasarkan kategori
    static void tampilkanMenu() {
        System.out.println("-------------- MENU MAKANAN --------------");
        tampilkanMenuMakanan();
        
        System.out.println("\n-------------- MENU MINUMAN --------------");
        tampilkanMenuMinuman();
    }

    // tampilkan menu makanan
    static void tampilkanMenuMakanan() {
        if (menuList[0].Category.equals("Makanan")) {
            System.out.println(menuList[0].Id + ". " + menuList[0].Name + " - Rp " + menuList[0].Price);
        }
        if (menuList[1].Category.equals("Makanan")) {
            System.out.println(menuList[1].Id + ". " + menuList[1].Name + " - Rp " + menuList[1].Price);
        }
        if (menuList[2].Category.equals("Makanan")) {
            System.out.println(menuList[2].Id + ". " + menuList[2].Name + " - Rp " + menuList[2].Price);
        }
        if (menuList[3].Category.equals("Makanan")) {
            System.out.println(menuList[3].Id + ". " + menuList[3].Name + " - Rp " + menuList[3].Price);
        }
    }

    // menampilkan menu minuman
    static void tampilkanMenuMinuman() {
        if (menuList[4].Category.equals("Minuman")) {
            System.out.println(menuList[4].Id + ". " + menuList[4].Name + " - Rp " + menuList[4].Price);
        }
        if (menuList[5].Category.equals("Minuman")) {
            System.out.println(menuList[5].Id + ". " + menuList[5].Name + " - Rp " + menuList[5].Price);
        }
        if (menuList[6].Category.equals("Minuman")) {
            System.out.println(menuList[6].Id + ". " + menuList[6].Name + " - Rp " + menuList[6].Price);
        }
        if (menuList[7].Category.equals("Minuman")) {
            System.out.println(menuList[7].Id + ". " + menuList[7].Name + " - Rp " + menuList[7].Price);
        }
    }

    // roses input pesanan
    static void prosesInputPesanan(Scanner scanner) {
        // Pesanan 1
        System.out.print("Pesanan 1 (kosongkan jika tidak ada): ");
        String input1 = scanner.nextLine();
        if (!input1.trim().isEmpty()) {
            tambahPesanan(input1);
        }

        // Pesanan 2
        System.out.print("Pesanan 2 (kosongkan jika tidak ada): ");
        String input2 = scanner.nextLine();
        if (!input2.trim().isEmpty()) {
            tambahPesanan(input2);
        }

        // Pesanan 3
        System.out.print("Pesanan 3 (kosongkan jika tidak ada): ");
        String input3 = scanner.nextLine();
        if (!input3.trim().isEmpty()) {
            tambahPesanan(input3);
        }

        // Pesanan 4
        System.out.print("Pesanan 4 (kosongkan jika tidak ada): ");
        String input4 = scanner.nextLine();
        if (!input4.trim().isEmpty()) {
            tambahPesanan(input4);
        }
    }

    // nambah pesanan
    static void tambahPesanan(String input) {
        String[] parts = input.split("=");
        if (parts.length == 2) {
            String namaMenu = parts[0].trim();
            int jumlah = Integer.parseInt(parts[1].trim());

            // Cari menu di menuList
            Menu menuDitemukan = cariMenu(namaMenu);
            if (menuDitemukan != null) {
                pesananNama[jumlahPesanan] = menuDitemukan.Name;
                pesananJumlah[jumlahPesanan] = jumlah;
                pesananHarga[jumlahPesanan] = menuDitemukan.Price;
                pesananKategori[jumlahPesanan] = menuDitemukan.Category;
                jumlahPesanan++;
            }
        }
    }

    // cari menu berdasarkan nama
    static Menu cariMenu(String nama) {
        if (menuList[0].Name.equalsIgnoreCase(nama)) return menuList[0];
        if (menuList[1].Name.equalsIgnoreCase(nama)) return menuList[1];
        if (menuList[2].Name.equalsIgnoreCase(nama)) return menuList[2];
        if (menuList[3].Name.equalsIgnoreCase(nama)) return menuList[3];
        if (menuList[4].Name.equalsIgnoreCase(nama)) return menuList[4];
        if (menuList[5].Name.equalsIgnoreCase(nama)) return menuList[5];
        if (menuList[6].Name.equalsIgnoreCase(nama)) return menuList[6];
        if (menuList[7].Name.equalsIgnoreCase(nama)) return menuList[7];
        return null;
    }

    // cetak struk
    static void cetakStruk() {
        System.out.println("\n==============================================");
        System.out.println("            STRUK PEMBAYARAN");
        System.out.println("==============================================");

        int subtotal = 0;

        // Tampilkan pesanan 1
        if (jumlahPesanan >= 1) {
            int totalItem1 = pesananHarga[0] * pesananJumlah[0];
            System.out.println(pesananNama[0] + " x " + pesananJumlah[0] + " = Rp " + totalItem1);
            subtotal += totalItem1;
        }

        // Tampilkan pesanan 2
        if (jumlahPesanan >= 2) {
            int totalItem2 = pesananHarga[1] * pesananJumlah[1];
            System.out.println(pesananNama[1] + " x " + pesananJumlah[1] + " = Rp " + totalItem2);
            subtotal += totalItem2;
        }

        // Tampilkan pesanan 3
        if (jumlahPesanan >= 3) {
            int totalItem3 = pesananHarga[2] * pesananJumlah[2];
            System.out.println(pesananNama[2] + " x " + pesananJumlah[2] + " = Rp " + totalItem3);
            subtotal += totalItem3;
        }

        // Tampilkan pesanan 4
        if (jumlahPesanan >= 4) {
            int totalItem4 = pesananHarga[3] * pesananJumlah[3];
            System.out.println(pesananNama[3] + " x " + pesananJumlah[3] + " = Rp " + totalItem4);
            subtotal += totalItem4;
        }

        System.out.println("----------------------------------------------");
        System.out.println("Subtotal: Rp " + subtotal);

        // count pajak 10%
        int pajak = subtotal * 10 / 100;
        System.out.println("Pajak (10%): Rp " + pajak);

        // biaya pelayanan
        int biayaPelayanan = 20000;
        System.out.println("Biaya Pelayanan: Rp " + biayaPelayanan);

        // total sebelum diskon
        int totalSebelumDiskon = subtotal + pajak + biayaPelayanan;
        System.out.println("----------------------------------------------");
        System.out.println("Total: Rp " + totalSebelumDiskon);

        // cek penawaran dan diskon
        int diskon = 0;
        int potonganMinuman = 0;

        // penawaran beli 1 gratis 1 minuman jika total > 50.000
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

    // hitung potongan minuman (beli 1 gratis 1)
    static int hitungPotonganMinuman() {
        // search minuman termurah untuk gratis
        int hargaMinumanTermurah = 0;

        if (jumlahPesanan >= 1 && pesananKategori[0] != null && pesananKategori[0].equals("Minuman")) {
            hargaMinumanTermurah = pesananHarga[0];
        }

        if (jumlahPesanan >= 2 && pesananKategori[1] != null && pesananKategori[1].equals("Minuman")) {
            if (hargaMinumanTermurah == 0 || pesananHarga[1] < hargaMinumanTermurah) {
                hargaMinumanTermurah = pesananHarga[1];
            }
        }

        if (jumlahPesanan >= 3 && pesananKategori[2] != null && pesananKategori[2].equals("Minuman")) {
            if (hargaMinumanTermurah == 0 || pesananHarga[2] < hargaMinumanTermurah) {
                hargaMinumanTermurah = pesananHarga[2];
            }
        }

        if (jumlahPesanan >= 4 && pesananKategori[3] != null && pesananKategori[3].equals("Minuman")) {
            if (hargaMinumanTermurah == 0 || pesananHarga[3] < hargaMinumanTermurah) {
                hargaMinumanTermurah = pesananHarga[3];
            }
        }

        return hargaMinumanTermurah;
    }
}