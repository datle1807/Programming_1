import java.util.Scanner;
import User.*;
import Interface.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        PortManagerList.getPortmanagerFile();
        // Display welcome screen
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER MANAGEMENT SYSTEM");
        System.out.println("Instructor: Vu Thanh Minh");
        System.out.println("s3957034, Le Quang Dat");
        System.out.println("s3878071, Vu Thanh Tung");
        System.out.println("s3927178, Lai Dong Khoa");
        System.out.println("s3968993, Pham Minh Nhat");
        MainMenu menu = new MainMenu(true);

        menu.display();

    }
}