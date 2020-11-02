import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class BoxOffice {

    private String listing;

    public BoxOffice(String listing) throws FileNotFoundException {
        Scanner in = new Scanner(new File(listing));

        while (in.hasNextLine()){ 
            String line = in.nextLine();
            in.skip("FILM : ");
            String titre = in.next();
            in.skip("REALISATEUR : ");
            String réalisateur = in.next();
            in.skip("ANNEE : ");
            int année = in.nextInt();
            int nbEtrées = in.nextInt();

            this.addFilm(titre, réalisateur, année, nbEtrées);
        }
        in.close();
    }

    public abstract void addFilm(String titre, String réalisateur, int année, int nbEntrées);
}
