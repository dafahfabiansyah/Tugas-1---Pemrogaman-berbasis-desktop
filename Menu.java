// ========================================
// CLASS MENU
// Fungsi: Mengelola daftar menu restoran (Makanan, Minuman, Diskon)
//         Menggunakan private attributes dengan getter/setter
// ========================================
class Menu {

    // Encapsulation - atribut private
    private Integer id;
    private String category;
    private String name;
    private Integer price;
    
    // Constructor
    Menu(Integer id, String category, String name, Integer price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    // Getter methods
    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    // Setter methods
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    // Method untuk mendapatkan info lengkap (untuk file I/O)
    public String getInfoLengkap() {
        return id + "," + category + "," + name + "," + price;
    }
}
