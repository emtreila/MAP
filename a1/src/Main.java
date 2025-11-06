import controller.Controller;
import view.View;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
//        5. Intr-o sera se cultiva rosii, ardei si vinete.
//        Sa se afiseze toate legumele cu greutatea mai mare
//        de 0.2 kg.

        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        View view = new View(controller, scanner);
        view.start();
    }
}