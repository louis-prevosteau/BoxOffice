import boxoffice.*;
import java.util.Scanner;

/**
 * @author Louis PREVOSTEAU
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Fichier : " + args[0]);
        System.out.println("----------");
        Scanner input = new Scanner(System.in);
        System.out.print("Entrez une structure de données : ");
        String struct = input.nextLine();
        switch (struct.toLowerCase()){
            case "tableau" :
                BoxOfficeTableau.run(args[0]);
                break;
            case "liste chaînée" :
                BoxOfficeChaine.run(args[0]);
                break;
            case "abr" :
                BoxOfficeArbre.run(args[0]);
                break;
            case "hashmap" :
                BoxOfficeHash.run(args[0]);
                break;
            case "all" :
                BoxOfficeTableau.run(args[0]);
                BoxOfficeChaine.run(args[0]);
                BoxOfficeArbre.run(args[0]);
                BoxOfficeHash.run(args[0]);
                break;
            default:
                System.out.println("Cette structure de données n'existe pas.");
                System.out.println("Choisissez parmi les structures de données suivantes : Tableau, Liste chaînée, ABR, HashMap ou All.");
                break;
        }
        input.close();
    }
}
