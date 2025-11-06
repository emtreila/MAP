import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int cnt, sum = 0;
//        Scanner sc = new Scanner(System.in);
//        System.out.print("n= ");
//        cnt = sc.nextInt();
//        for (int i = 0; i < cnt; i++) {
//            int x = sc.nextInt();
//            sum += x;
//        }
//        System.out.println(sum / cnt);

        Dog d1 = new Dog(5, "Rex", "Brown");
        Labrador l1 = new Labrador(3, "Max", "Yellow");
        System.out.println(l1);
        
        Dog dogs[] = new Dog[10];
        dogs[0] = d1;
        dogs[1] = l1;
        
        for (int i = 0; i < dogs.length; i++){
            System.out.println(dogs[i]);
        }
    }
}
