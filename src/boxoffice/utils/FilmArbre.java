package boxoffice.utils;

/**
 * @author PREVOSTEAU Louis
 */

public class FilmArbre extends Film {

    private FilmArbre left, right;

    public FilmArbre(String titre, String réalisateur, int année, int nbEntrées, FilmArbre left, FilmArbre right) {
        super(titre, réalisateur, année, nbEntrées);
        this.left = left;
        this.right = right;
    }

    public FilmArbre(String titre, String réalisateur, int année, int nbEntrées) {
        super(titre, réalisateur, année, nbEntrées);
        left = null;
        right = null;
    }

    public FilmArbre getLeft() {
        return left;
    }

    public void setLeft(FilmArbre left) {
        this.left = left;
    }

    public FilmArbre getRight() {
        return right;
    }

    public void setRight(FilmArbre right) {
        this.right = right;
    }

    public int key(){
        return getTitre().hashCode() + getAnnée();
    }
}
