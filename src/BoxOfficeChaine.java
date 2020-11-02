import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BoxOfficeChaine extends BoxOffice{

    private FilmChaine elements;
    private ArrayList<Film> top3;

    public BoxOfficeChaine(String listing) throws FileNotFoundException {
        super(listing);
    }

    public FilmChaine getElements() {
        return elements;
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (elements == null){
            elements = new FilmChaine(titre, réalisateur, année, nbEntrées);
        }
        FilmChaine tmp = getElements();
        FilmChaine previous = getElements();
        while (tmp != null){
            if (tmp.getTitre().equals(titre) && tmp.getRéalisateur().equals(réalisateur)){
                tmp.setNbEntrées(nbEntrées);
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
        previous.setNext(new FilmChaine(titre, réalisateur, année, nbEntrées));
    }

    public static Comparator<Film> compareFilm = new Comparator<Film>() {
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
                if (top3.size() > 3)
                    top3.remove(3);
            }
            tmp = tmp.getNext();
        }
        for (int i = 0 ; i < top3.size() ; i++)
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
    }
}
