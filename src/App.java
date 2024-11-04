import java.util.Scanner;

public class App {

    public static int[] creeTableau(int taille) {
        int[] tab = new int[taille];
        return tab;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isConected = false;

        System.out.println("Bienvenu dans votre gestionaire de mot de passe. Crée un nom d'utilisateur");
        String userName = scanner.nextLine();

        System.out.println("Entrez un mot de passe");
        int password = scanner.nextInt();

        String userNameTryConect = scanner.nextLine();
        System.out.println("conectez vous, entrez votre nom d'utilisateur");


        if (userNameTryConect == userName) {
            System.out.println("ok");
        } else {
            System.out.println("nons");
        }
    }
}