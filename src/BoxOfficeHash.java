import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BoxOfficeHash extends BoxOffice {

    private FilmChaine[] elements;
    private ArrayList<FilmChaine> top3;
    public final int SIZE = 1000000;

    public BoxOfficeHash(String listing) throws FileNotFoundException {
        super(listing);
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (elements == null){
            elements = new FilmChaine[SIZE];
            elements[index(titre)] = new FilmChaine(titre, réalisateur, année, nbEntrées);
            top3 = new ArrayList<FilmChaine>();
            top3.add(elements[index(titre)]);
        }else if (elements[index(titre)] != null){
            if (elements[index(titre)].getTitre().equals(titre))
                elements[index(titre)].setNbEntrées(nbEntrées);
            else if (elements[index(titre)].getNext() != null){
                FilmChaine tmp = elements[index(titre)];
                while (tmp.getNext() != null){
                    if (tmp.getTitre().equals(titre)){
                        tmp.getNext().setNbEntrées(nbEntrées);
                        return;
                    }
                    tmp = tmp.getNext();
                }
                tmp.setNext(new FilmChaine(titre, réalisateur, année, nbEntrées));
            }else
                elements[index(titre)].setNext(new FilmChaine(titre, réalisateur, année, nbEntrées));
        } else
            elements[index(titre)] = new FilmChaine(titre, réalisateur, année, nbEntrées);
    }

    public int index(String titre){
        int i = titre.hashCode() % SIZE;
        if (i < 0)
            i = i * -1;
        return i;
    }

    public static Comparator<FilmChaine> compareFilm = new Comparator<FilmChaine>() {
        @Override
        public int compare(FilmChaine f1, FilmChaine f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    };

    public void top3(){
        for (FilmChaine film : elements){
            if (film != null){
                if (film.getNbEntrées() >= top3.get(top3.size()-1).getNbEntrées()){
                    top3.add(film);
                    top3.sort(compareFilm);
                    Collections.reverse(top3);
                    if (top3.size() > 3)
                        top3.remove(3);
                }
            }
            if (film.getNext() != null) {
                FilmChaine tmp = film;
                while (tmp.getNext() != null){
                    if (tmp.getNext().getNbEntrées() >= top3.get(top3.size()-1).getNbEntrées()){
                        top3.add(tmp.getNext());
                        top3.sort(compareFilm);
                        Collections.reverse(top3);
                        if (top3.size() > 3)
                            top3.remove(3);
                    }
                    tmp = tmp.getNext();
                }
            }
        }
        for (int i = 0 ; i < top3.size() ; i++)
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
    }

    public static void main(String[] args) throws FileNotFoundException{
        if (args.length < 1)
            System.out.println("Pas de fichier");
        else{
            long start = System.currentTimeMillis();
            BoxOfficeHash bo = new BoxOfficeHash(args[0]);
            System.out.println("Fichier : " + args[0]);
            System.out.println("Nombre de lignes : " + bo.getNbLine());
            System.out.println("Nombre de films : " + bo.getNbFilms());
            System.out.println("----------");
            System.out.println("Films comptabilisant le plus grand nombre d’entrées :");
            bo.top3();
            long time = System.currentTimeMillis() - start;
            System.out.println("Temps d'execution : " + time + "ms");
        }
    }
}