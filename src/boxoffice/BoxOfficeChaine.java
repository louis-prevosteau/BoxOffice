package boxoffice;

import boxoffice.utils.Film;
import boxoffice.utils.FilmChaine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author PREVOSTEAU Louis
 */

public class BoxOfficeChaine extends BoxOffice {

    private FilmChaine film;
    private ArrayList<Film> top3;
    private static int cptFilm = 0;

    public BoxOfficeChaine(String listing) throws FileNotFoundException {
        super(listing);
    }

    public FilmChaine getFilm() {
        return film;
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (film == null){
            film = new FilmChaine(titre, réalisateur, année, nbEntrées); // Si la liste chainée n'existe pas, on la créé.
            cptFilm++;
        }
        FilmChaine tmp = getFilm();
        FilmChaine previous = getFilm();
        while (tmp != null){
            if (tmp.getTitre().equals(titre) && tmp.getRéalisateur().equals(réalisateur) && tmp.getAnnée() == année){ // Si le film est dans la liste chainée,
                tmp.setNbEntrées(nbEntrées); // On augmente son nombre d'entrées.
                return;
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
        previous.setNext(new FilmChaine(titre, réalisateur, année, nbEntrées)); // Si le film n'est pas trouvé, on l'ajoute à la fin de la liste.
        cptFilm++;
    }

    public static Comparator<Film> compareFilm = new Comparator<Film>() { // Comparateur du nombre d'entrées
        @Override
        public int compare(Film f1, Film f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    };

    public void top3(){
        top3 = new ArrayList<Film>();
        top3.add(getFilm());
        FilmChaine tmp = getFilm();
        while (tmp != null){
            if (tmp.getNbEntrées() >= top3.get(top3.size() - 1).getNbEntrées()){ // Si le film regardé a un plus grand nombre d'entrées que le dernier film du classement.
                top3.add(tmp); // On ajoute le film.
                top3.sort(compareFilm); // On trie le classement.
                Collections.reverse(top3); // On inverse le classement.
                if (top3.size() > 3) // Si size() est supérieur à 3, alors on ne garde que les 3 premiers films.
                    top3.remove(3);
            }
            tmp = tmp.getNext();
        }
        for (int i = 0 ; i < top3.size() ; i++) // Affichage du classement
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
    }

    public static void run(String listing){
        long start = System.currentTimeMillis();
        try {
            BoxOfficeChaine box = new BoxOfficeChaine(listing);
            System.out.println("Nombre de lignes : " + box.getNbLine());
            System.out.println("Nombre de films : " + box.cptFilm);
            System.out.println("Films comptabilisant le plus grand nombre d’entrées :");
            box.top3();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Temps d'execution : " + time + "ms\n");
    }
}
