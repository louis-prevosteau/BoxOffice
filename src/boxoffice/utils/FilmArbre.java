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

    public int height(){ // Calcul de la hauteur de l'ABR
        int leftHeight = 0, rightHeight = 0;
        if (left != null)
            leftHeight = left.height();
        if (right != null)
            rightHeight = right.height();
        if (leftHeight > rightHeight)
            return leftHeight + 1;
        return rightHeight + 1;
    }

    public boolean stable(){ // ABR équilibré
        if (left == null && right == null)
            return true;
        if (left == null)
            return ((right.height() < 2) && right.stable());
        if (right == null)
            return ((left.height() <2) && left.stable());
        return left.stable() && right.stable() && Math.abs(left.height() - right.height()) < 2;
        /**
         * un ABR est équilibré si et seulement si la différence de hauteur de ses fils
         * (droit et gauche) est inférieur ou égal à 1.
         */
    }
}
