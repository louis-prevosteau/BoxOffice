package boxoffice.utils;

/**
 * @author PREVOSTEAU Louis
 */

public class Film {

    private String titre, réalisateur;
    private int année, nbEntrées;

    public Film(String titre, String réalisateur, int année, int nbEntrées) {
        this.titre = titre;
        this.réalisateur = réalisateur;
        this.année = année;
        this.nbEntrées = nbEntrées;
    }

    public String getTitre() {
        return titre;
    }

    public String getRéalisateur() {
        return réalisateur;
    }

    public int getAnnée() {
        return année;
    }

    public int getNbEntrées() {
        return nbEntrées;
    }

    /**
     * Incrémentation du nombre d'entrées d'un film.
     *
     * @param nbEntrées
     */

    public void setNbEntrées(int nbEntrées) {
        this.nbEntrées += nbEntrées;
    }
}
