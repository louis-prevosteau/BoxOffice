import boxoffice.BoxOffice;

import java.io.FileNotFoundException;

public class TestBoxOffice extends BoxOffice {

    public TestBoxOffice(String listing) throws FileNotFoundException{
        super(listing);
    }

    public void addFilm(String titre, String réalisateur, int année, int nbEntrées){
        System.out.println("("+année+") "+titre+" Real.: "
                +réalisateur+" Entrees: "+nbEntrées);
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1)
            System.out.println("Pas de fichier");
        else
            new TestBoxOffice(args[0]);
    }
}
