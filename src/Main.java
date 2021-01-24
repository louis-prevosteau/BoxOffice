import boxoffice.BoxOfficeArbre;
import boxoffice.BoxOfficeChaine;
import boxoffice.BoxOfficeHash;
import boxoffice.BoxOfficeTableau;

public class Main {

    /**
     * @author Louis PREVOSTEAU
     */

    public static void main(String[] args) {
        System.out.println("Fichier : " + args[0] + "\n");
        System.out.println("----------");
        BoxOfficeTableau.run(args[0]);
        BoxOfficeChaine.run(args[0]);
        BoxOfficeArbre.run(args[0]);
        BoxOfficeHash.run(args[0]);
    }
}
