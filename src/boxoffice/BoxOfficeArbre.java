package boxoffice;

import boxoffice.utils.FilmArbre;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author PREVOSTEAU Louis
 */

public class BoxOfficeArbre extends BoxOffice {

    private FilmArbre film;
    private ArrayList<FilmArbre> top3;
    private static int cptFilm = 0;

    public BoxOfficeArbre(String listing) throws FileNotFoundException {
        super(listing);
    }

    public FilmArbre getFilm() {
        return film;
    }

    public void setFilm(FilmArbre film) {
        this.film = film;
    }

    /**
     * Méthode de recherche d'un film à partir d'un film racine.
     *
     * @param titre
     * @param année
     * @param racine
     * @return FilmArbre
     */

    public FilmArbre searchFilm(String titre, int année, FilmArbre racine){
        if (racine != null){
            if (titre.hashCode() + année == racine.key()) // Film trouvé.
                return racine;
            else if (titre.hashCode() + année >= racine.key()) // Si le film recherché a un plus grand hashCode, on part à droite.
                return searchFilm(titre, année, racine.getRight());
            else if (titre.hashCode() + année < racine.key()) // Sinon, on part à gauche.
                return searchFilm(titre, année, racine.getLeft());
        }
        return null;
    }

    public void pointeurFilm(String titre, String réalisateur, int année, int nbEntrées, FilmArbre racine){
        if(titre.hashCode() + année >= racine.key()){ // Si le film a un plus grand hashCode que le film pointé, alors on va le placer à sa droite.
            if (racine.getRight() == null) // Si le film pointé n'a pas déjà un fils droit, on l'ajoute en fils droit.
                racine.setRight(new FilmArbre(titre, réalisateur, année, nbEntrées));
            else // Sinon, on pointe le fils droit et on recommence.
                pointeurFilm(titre, réalisateur, année, nbEntrées, racine.getRight());
        }else { // Si le film a un plus petit hashCode que le film pointé, alors on va le placer à sa gauche.
            if (racine.getLeft() == null) // Si le film pointé n'a pas déjà un fils gauche, on l'ajoute en fils gauche.
                racine.setLeft(new FilmArbre(titre, réalisateur, année, nbEntrées));
            else // Sinon, on pointe le fils gauche et on recommence.
                pointeurFilm(titre, réalisateur, année, nbEntrées, racine.getLeft());
        }
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (getFilm() == null){
            setFilm(new FilmArbre(titre, réalisateur, année, nbEntrées));
            top3 = new ArrayList<FilmArbre>();
            top3.add(getFilm());
            cptFilm++;
        }else {
            FilmArbre tmp = searchFilm(titre, année, getFilm()); // Dans le cas où le film est trouvé, tmp pointera vers le film équivalant.
            if (tmp != null){ // Si le film est trouvé,
                tmp.setNbEntrées(nbEntrées); // On incrémente son nombre d'entrées.
                return;
            }
            else{
                pointeurFilm(titre, réalisateur, année, nbEntrées, getFilm()); // Sinon, on ajoute le film.
                cptFilm++;
                }
        }
    }

    public static Comparator<FilmArbre> compareFilm = new Comparator<FilmArbre>() {
        @Override
        public int compare(FilmArbre f1, FilmArbre f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    };

    public void top3(FilmArbre racine){
        if (racine.getNbEntrées() >= top3.get(top3.size() - 1).getNbEntrées()){
            top3.add(racine);
            top3.sort(compareFilm);
            Collections.reverse(top3);
            if (top3.size() > 3)
                top3.remove(3);
        }
        if (racine.getLeft() != null) // Si il y a un fils gauche, on trie le fils gauche.
            top3(racine.getLeft());
        if (racine.getRight() != null) // Si il y a un fils droit, on trie le fils droit.
            top3(racine.getRight());
    }

    public void afficherTop3(){
        top3(getFilm());
        System.out.println("Films comptabilisant le plus grand nombre d’entrées :");
        for (int i = 0 ; i < top3.size() ; i++)
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
        System.out.println("Hauteur de l'ABR : " + getFilm().height());
        if (getFilm().stable())
            System.out.println("L'ABR est équilibré.");
        else
            System.out.println("L'ABR n'est pas équilibré.");
    }

    public static void run(String listing){ // éxécution
        long start = System.currentTimeMillis();
        try {
            BoxOfficeArbre box = new BoxOfficeArbre(listing);
            long time = System.currentTimeMillis() - start;
            System.out.println("Nombre de lignes : " + box.getNbLine());
            System.out.println("Nombre de films : " + box.cptFilm);
            box.afficherTop3();
            System.out.println("Temps d'execution : " + time + "ms");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
