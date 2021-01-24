package boxoffice;

import boxoffice.utils.FilmChaine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author PREVOSTEAU Louis
 */

public class BoxOfficeHash extends BoxOffice {

    private FilmChaine[] elements;
    private ArrayList<FilmChaine> top3;
    private static int cptFilm = 0;
    public final int SIZE = 1000000;

    public BoxOfficeHash(String listing) throws FileNotFoundException {
        super(listing);
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (elements == null){
            elements = new FilmChaine[SIZE];
            elements[index(titre, année)] = new FilmChaine(titre, réalisateur, année, nbEntrées);
            top3 = new ArrayList<FilmChaine>();
            top3.add(elements[index(titre, année)]);
            cptFilm++;
        }else if (elements[index(titre, année)] != null){ // Si il y a déjà un film à l'index où l'on doit placé le film.
            if (elements[index(titre, année)].getTitre().equals(titre) && elements[index(titre, année)].getAnnée() == année) // Si c'est le bon film, on augmente son nombre d'entrées.
                elements[index(titre, année)].setNbEntrées(nbEntrées);
            else if (elements[index(titre, année)].getNext() != null){
                FilmChaine tmp = elements[index(titre, année)];
                while (tmp != null){
                    if (tmp.getTitre().equals(titre) && tmp.getAnnée() == année){
                        tmp.setNbEntrées(nbEntrées);
                        return;
                    }
                    tmp = tmp.getNext();
                }
                tmp.setNext(new FilmChaine(titre, réalisateur, année, nbEntrées)); // Si on ne trouve pas le film dans le chaine, on l'ajoute en bout de chaine
            }else{ // Si le FilmChaine a la position n'a pas de suivant, on ajoute le nouveau film en suivant.
                elements[index(titre, année)].setNext(new FilmChaine(titre, réalisateur, année, nbEntrées));
                cptFilm++;
            }
        } else{
            elements[index(titre, année)] = new FilmChaine(titre, réalisateur, année, nbEntrées);
            cptFilm++;
        }
    }

    public int index(String titre, int année){ // Calcul de l'index du film en fonction du hashCode du titre et de l'année.
        int i = (titre.hashCode() + année) % SIZE;
        if (i < 0)
            i = i * -1; // L'index doit toujours être positif.
        return i;
    }

    public static Comparator<FilmChaine> compareFilm = new Comparator<FilmChaine>() { // Comparateur du nombre d'entrées
        @Override
        public int compare(FilmChaine f1, FilmChaine f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    };

    public void top3(){
        for (FilmChaine film : elements){
            if (film != null){
                if (film.getNbEntrées() >= top3.get(top3.size()-1).getNbEntrées()){ // Si le film regardé a un plus grand nombre d'entrées que le dernier film du classement.
                    top3.add(film); // On ajoute le film.
                    top3.sort(compareFilm); // On trie le classement.
                    Collections.reverse(top3); // On inverse le classement.
                    if (top3.size() > 3) // Si size() est supérieur à 3, alors on ne garde que les 3 premiers films.
                        top3.remove(3);
                }
            }
        }
        for (int i = 0 ; i < top3.size() ; i++)
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
    }

    public static void run(String listing){ // éxécution
        long start = System.currentTimeMillis();
        try {
            BoxOfficeHash box = new BoxOfficeHash(listing);
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
