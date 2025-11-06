package view;

import controller.Controller;
import model.Ardei;
import model.Rosii;
import model.Vegetable;
import model.Vinete;

import java.util.Scanner;

public class View {
    private Controller controller;
    private Scanner scanner;

    public View(Controller controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("VEGETABLES MENU");
            System.out.println("1. Add a new vegetable.");
            System.out.println("2. Remove a vegetable.");
            System.out.println("3. Show all vegetables.");
            System.out.println("4. Show heavy vegetables.");
            System.out.println("0. EXIT");
            System.out.print("Choose an option: ");

            String option = this.scanner.nextLine();

            switch (option) {
                case "0" -> {
                    System.out.println("Bye, bye!");
                    return;
                }
                case "1" -> addVeggie();
                case "2" -> removeVeggie();
                case "3" -> showVeggie();
                case "4" -> showHeavy();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void addVeggie() {

        System.out.print("Enter the vegetable (ardei/rosii/vinete): ");
        String v = this.scanner.nextLine();
        System.out.print("Enter weight (kg): ");
        String input = scanner.nextLine();

        double weight;
        try {
            weight = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            return;
        }
        if (weight <= 0) {
            System.out.println("The weight must be positive!");
            return;
        }
        try {
            if (v.equalsIgnoreCase("ardei")) {
                this.controller.add(new Ardei(weight));
            } else if (v.equalsIgnoreCase("rosii")) {
                this.controller.add(new Rosii(weight));
            } else if (v.equalsIgnoreCase("vinete")) {
                this.controller.add(new Vinete(weight));
            } else {
                System.out.println("Invalid vegetable!");
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void removeVeggie() {

        System.out.print("Enter the vegetable (ardei/rosii/vinete): ");
        String v = this.scanner.nextLine();
        System.out.print("Enter weight (kg): ");
        String input = this.scanner.nextLine();

        double weight;
        try {
            weight = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            return;
        }
        if (weight <= 0) {
            System.out.println("The weight must be positive!");
            return;
        }

        try {
            if (v.equalsIgnoreCase("ardei")) {
                this.controller.remove(new Ardei(weight));
            } else if (v.equalsIgnoreCase("rosii")) {
                this.controller.remove(new Rosii(weight));
            } else if (v.equalsIgnoreCase("vinete")) {
                this.controller.remove(new Vinete(weight));
            } else {
                System.out.println("Invalid vegetable!");
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void showVeggie() {
        Vegetable[] all = this.controller.getAll();
        if (all.length == 0) {
            System.out.println("No vegetables!");
            return;
        }
        for (Vegetable v : all) {
            System.out.println(v);
        }
    }

    public void showHeavy() {
        Vegetable[] heavy = this.controller.getHeavyVeggie();
        if (heavy.length == 0) {
            System.out.println("No heavy vegetables!");
            return;
        }
        for (Vegetable v : heavy) {
            System.out.println(v);
        }
    }

}
