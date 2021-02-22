import boxoffice.*;
import java.util.Scanner;

/**
 * @author Louis PREVOSTEAU
 */

public class Main {

    /**
     * Méthode d'éxécution principale, l'utilisateur peut choisir la structure de données à éxécuter
     * ou éxécuter toutes les structures de données en même temps (all).
     *
     * @param args
     */

    public static void main(String[] args) {
        System.out.println("Fichier : " + args[0]);
        System.out.println("----------");
        Scanner input = new Scanner(System.in);
        System.out.print("Entrez une structure de données : ");
        String struct = input.nextLine();
        switch (struct.toLowerCase()){
            case "tableau" :
                System.out.println("Tableau :");
                BoxOfficeTableau.run(args[0]);
                break;
            case "liste chaînée" :
                System.out.println("Liste Chaînée :");
                BoxOfficeChaine.run(args[0]);
                break;
            case "abr" :
                System.out.println("Arbre Binaire de Recherche :");
                BoxOfficeArbre.run(args[0]);
                break;
            case "hashmap" :
                System.out.println("Table de Hachage :");
                BoxOfficeHash.run(args[0]);
                break;
            case "all" :
                System.out.println("Tableau :");
                BoxOfficeTableau.run(args[0]);
                System.out.println("Liste Chaînée :");
                BoxOfficeChaine.run(args[0]);
                System.out.println("Arbre Binaire de Recherche :");
                BoxOfficeArbre.run(args[0]);
                System.out.println("Table de Hachage :");
                BoxOfficeHash.run(args[0]);
                break;
            default:
                System.out.println("Cette structure de données n'existe pas.");
                System.out.println("Choisissez parmi les structures de données suivantes : Tableau, Liste Chaînée, ABR, HashMap ou All.");
                break;
        }
        input.close();
    }
}
