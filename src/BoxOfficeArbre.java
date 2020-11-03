import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

public class BoxOfficeArbre extends BoxOffice {

    private FilmArbre elements;
    private ArrayList<FilmArbre> top3;

    public BoxOfficeArbre(String listing) throws FileNotFoundException {
        super(listing);
    }

    public FilmArbre getElements() {
        return elements;
    }

    public void setElements(FilmArbre elements) {
        this.elements = elements;
    }

    public FilmArbre searchFilm(String titre, FilmArbre racine){
        if (racine != null){
            if (titre.hashCode() == racine.key())
                return racine;
            else if (titre.hashCode() >= racine.key())
                return searchFilm(titre, racine.getRight());
            else if (titre.hashCode() < racine.key())
                return searchFilm(titre, racine.getLeft());
        }
        return null;
    }

    public void ajouterFilm(String titre, String réalisateur, int année, int nbEntrées, FilmArbre racine){
        if(titre.hashCode() >= racine.key()){
            if (racine.getRight() == null)
                racine.setRight(new FilmArbre(titre, réalisateur, année, nbEntrées));
            else
                ajouterFilm(titre, réalisateur, année, nbEntrées, racine.getRight());
        }else {
            if (racine.getLeft() == null)
                racine.setLeft(new FilmArbre(titre, réalisateur, année, nbEntrées));
            else
                ajouterFilm(titre, réalisateur, année, nbEntrées, racine.getLeft());
        }
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (getElements() == null){
            setElements(new FilmArbre(titre, réalisateur, année, nbEntrées));
            top3 = new ArrayList<FilmArbre>();
            top3.add(getElements());
        }else {
            FilmArbre tmp = searchFilm(titre, getElements());
            if (tmp != null)
                tmp.setNbEntrées(nbEntrées);
            else
                ajouterFilm(titre, réalisateur, année, nbEntrées, getElements());
        }
    }

    public static Comparator<FilmArbre> compareFilm = new Comparator<FilmArbre>() {
        @Override
        public int compare(FilmArbre f1, FilmArbre f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    }
}
