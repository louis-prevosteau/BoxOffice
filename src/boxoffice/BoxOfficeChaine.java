package boxoffice;

import boxoffice.BoxOffice;
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

    private FilmChaine elements;
    private ArrayList<Film> top3;
    private static int cptFilm = 0;

    public BoxOfficeChaine(String listing) throws FileNotFoundException {
        super(listing);
    }

    public FilmChaine getElements() {
        return elements;
    }

    public static int getCptFilm() {
        return cptFilm;
    }

    public static void setCptFilm(int cptFilm) {
        BoxOfficeChaine.cptFilm = cptFilm;
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (elements == null){
            elements = new FilmChaine(titre, réalisateur, année, nbEntrées); // Si la liste chainée n'existe pas, on la créé.
            setCptFilm(getCptFilm() + 1);
        }
        FilmChaine tmp = getElements();
        FilmChaine previous = getElements();
        while (tmp != null){
            if (tmp.getTitre().equals(titre) && tmp.getRéalisateur().equals(réalisateur) && tmp.getAnnée() == année){ // Si le film est dans la liste chainée,
                tmp.setNbEntrées(nbEntrées); // On augmente son nombre d'entrées.
                return;
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
        previous.setNext(new FilmChaine(titre, réalisateur, année, nbEntrées));
        setCptFilm(getCptFilm() + 1);
    }

    public static Comparator<Film> compareFilm = new Comparator<Film>() { // Comparateur du ombre d'entrées
        @Override
        public int compare(Film f1, Film f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    };

    public void top3(){
        top3 = new ArrayList<Film>();
        top3.add(getElements());
        FilmChaine tmp = getElements();
        while (tmp != null){
            if (tmp.getNbEntrées() >= top3.get(top3.size() - 1).getNbEntrées()){
                top3.add(tmp);
                top3.sort(compareFilm);
                Collections.reverse(top3);
                if (top3.size() > 3) // Si size() est supérieur à 3, alors on ne garde que les 3 premiers films.
                    top3.remove(3);
            }
            tmp = tmp.getNext();
        }
        for (int i = 0 ; i < top3.size() ; i++)
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
    }
}
