import java.util.Scanner;

public class Main {

   void displayMenu(String menu) {
       Menu menuList = new Menu(1, "Makanan", "Mie Ayam", 15000);
    //    System.out.println("Menu ID: " + menuList.Id);
    //    System.out.println("Category: " + menuList.Category);
    //    System.out.println("Name: " + menuList.Name);
    //    System.out.println("Price: " + menuList.Price);
       Menu menuList2 = new Menu(2, "Makanan", "Kopi", 12000);
        // System.out.println("Menu ID: " + menuList2.Id);
        // System.out.println("Category: " + menuList2.Category);
        // System.out.println("Name: " + menuList2.Name);
        // System.out.println("Price: " + menuList2.Price);
   }


   public static void main(String[] args) {
       System.out.println("Welcome to Cafe ABC, silahkan pilih menu yang tersedia:");
       Scanner inputUser = new Scanner(System.in);
       System.out.println("mau order apa hari ini? mau makan atau minum?");

    var userOrder = inputUser.nextLine();
    System.out.println("Kamu memesan: " + userOrder);

    //    Main main = new Main();
    //    main.displayMenu("Menu");
   }

  

//    public static String showMenu(String menu) {
   
//    }
   }