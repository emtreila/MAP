package view;

import java.util.Scanner;

public class View {
    private int selectedExample = 0;

    public void printMenu() {
        System.out.println("\n========= MENU =========");
        System.out.println("1. Input a program to execute");
        System.out.println("2. Execute the selected program");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public int run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAvailable examples:");
                    System.out.println("1. int v; v=2; Print(v)");
                    System.out.println("2. int a; int b; a=2+3*5; b=a+1; Print(b)");
                    System.out.println("3. bool a; int v; a=true; If a Then v=2 Else v=3; Print(v)");
                    System.out.print("Choose example number (1-3): ");
                    this.selectedExample = scanner.nextInt();
                    System.out.println("Example " + this.selectedExample + " selected.");
                }
                case 2 -> {
                    if (this.selectedExample == 0) {
                        System.out.println("No example selected! Please choose option 1 first.");
                    } else {
                        return this.selectedExample; 
                    }
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    return 0;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
}
