import boxoffice.*;

import java.io.FileNotFoundException;

/**
 * @author PREVOSTEAU Louis
 */

public class TestBoxOffice extends BoxOffice {

    public TestBoxOffice(String listing) throws FileNotFoundException{
        super(listing);
    }

    public void addFilm(String titre, String réalisateur, int année, int nbEntrées){
        System.out.println("("+année+") "+titre+" Real.: "
                +réalisateur+" Entrees: "+nbEntrées);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Fichier : " + args[0] + "\n");
            new TestBoxOffice(args[0]);
            long startTab = System.currentTimeMillis();
            BoxOfficeTableau boTab = new BoxOfficeTableau(args[0]); // Tableau
            System.out.println("Nombre de lignes : " + boTab.getNbLine());
            System.out.println("Nombre de films : " + boTab.getCptFilm());
            System.out.println("----------");
            System.out.println("Films comptabilisant le plus grand nombre d’entrées :");
            boTab.top3();
            long timeTab = System.currentTimeMillis() - startTab;
            System.out.println("Temps d'execution : " + timeTab + "ms\n");

            long startChain = System.currentTimeMillis();
            BoxOfficeChaine boChain = new BoxOfficeChaine(args[0]); // Liste Chaînée
            System.out.println("Nombre de lignes : " + boChain.getNbLine());
            System.out.println("Nombre de films : " + boChain.getCptFilm());
            System.out.println("----------");
            System.out.println("Films comptabilisant le plus grand nombre d’entrées :");
            boChain.top3();
            long timeChain = System.currentTimeMillis() - startChain;
            System.out.println("Temps d'execution : " + timeChain + "ms\n");

            long startABR = System.currentTimeMillis();
            BoxOfficeArbre boABR = new BoxOfficeArbre(args[0]); // Arbre Binaire de Recherche
            System.out.println("Nombre de lignes : " + boABR.getNbLine());
            System.out.println("Nombre de films : " + boABR.getCptFilms());
            System.out.println("----------");
            boABR.afficherTop3();
            long timeABR = System.currentTimeMillis() - startABR;
            System.out.println("Temps d'execution : " + timeABR + "ms\n");

            long startHash = System.currentTimeMillis();
            BoxOfficeHash boHash = new BoxOfficeHash(args[0]); // Table de Hachage
            System.out.println("Nombre de lignes : " + boHash.getNbLine());
            System.out.println("Nombre de films : " + boHash.getCptFilms());
            System.out.println("----------");
            System.out.println("Films comptabilisant le plus grand nombre d’entrées :");
            boHash.top3();
            long timeHash = System.currentTimeMillis() - startHash;
            System.out.println("Temps d'execution : " + timeHash + "ms\n");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
