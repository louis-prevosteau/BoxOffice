import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class BoxOffice {

    private String listing;
    private int nbLine;
    private int nbFilms;

    public BoxOffice(String listing) throws FileNotFoundException {
        Scanner in = new Scanner(new File(listing));

        while (in.hasNextLine()){ // Lecture du fichier
            String line = in.nextLine();
            nbLine++;
            in.skip("FILM : ");
            String titre = in.next();
            in.skip("REALISATEUR : ");
            String réalisateur = in.next();
            in.skip("ANNEE : ");
            int année = in.nextInt();
            int nbEtrées = in.nextInt();
            Scanner scannerFilm = new Scanner(line);
            while (scannerFilm.hasNext()){
                String myFilm = scannerFilm.next();
                nbFilms++;
            }
            scannerFilm.close();
        }
        in.close();
    }

    public int getNbLine() {
        return nbLine;
    }

    public int getNbFilms() {
        return nbFilms;
    }

    public abstract void addFilm(String titre, String réalisateur, int année, int nbEntrées);
}
