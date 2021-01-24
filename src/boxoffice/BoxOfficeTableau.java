package boxoffice;

import boxoffice.utils.Film;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author PREVOSTEAU Louis
 */

public class BoxOfficeTableau extends BoxOffice {

    public final static int SIZE = 100;
    private Film[] elements;
    private ArrayList<Film> top3;
    private static int cptFilm;

    public BoxOfficeTableau(String listing) throws FileNotFoundException {
        super(listing);
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (elements == null){
            this.elements = new Film[SIZE]; // Si le tableau elements n'existe pas, on le créé.
        }

        if (cptFilm < elements.length){
            if (cptFilm == 0){
                Film newFilm = new Film(titre, réalisateur, année, nbEntrées); // Si le tableau elements est vide, on créé un film.
                elements[cptFilm] = newFilm; // On l'ajoute dans le tableau
                cptFilm++; // On incrémente le nombre de films dans le tableau.
            }else{
                boolean add = false;
                for (int i = 0 ; i < cptFilm ; i++){
                    if (elements[i].getTitre().equals(titre) && elements[i].getRéalisateur().equals(réalisateur) && elements[i].getAnnée() == année){ // Si le film est déjà dans le tableau,
                        elements[i].setNbEntrées(nbEntrées); // On augmente son nombre d'entrées.
                        add = true;
                    }
                }
                if (add == false){ // Si le film n'a pas été trouvé, on l'ajoute à la fin du tableau.
                    Film newFilm = new Film(titre, réalisateur,année,nbEntrées);
                    elements[cptFilm] = newFilm;
                    cptFilm++;
                }
            }
        }else{ // Si le tableau est rempli,
            Film[] tmp = elements;
            elements = new Film[tmp.length * 2]; // On multiplie la taille du tableau par 2.
            for (int i = 0 ; i < tmp.length ; i++)
                elements[i] = tmp[i];
        }
    }

    public static Comparator<Film> compareFilm = new Comparator<Film>() { // Comparateur du nombre d'entrées
        @Override
        public int compare(Film f1, Film f2) {
            return f1.getNbEntrées() - f2.getNbEntrées();
        }
    };

    public void top3(){
        top3 = new ArrayList<Film>();
        top3.add(elements[0]);
        for (Film film : elements){
            if (film == null)
                break;
            if (film.getNbEntrées() >= top3.get(top3.size()-1).getNbEntrées()){ // Si le film regardé a un plus grand nombre d'entrées que le dernier film du classement.
                top3.add(film); // On ajoute le film
                top3.sort(compareFilm); // On trie le classement.
                Collections.reverse(top3); // On inverse le classement.
                if (top3.size() > 3) // Si size() est supérieur à 3, alors on ne garde que les 3 premiers films.
                    top3.remove(3);
            }
        }
        for (int i = 0 ; i < top3.size() ; i++) // Affichage du classement
            System.out.println("(" + top3.get(i).getAnnée() + ") " + top3.get(i).getTitre() + " entrées : " + top3.get(i).getNbEntrées());
    }

    public static void run(String listing){ // éxécution
        long start = System.currentTimeMillis();
        try {
            BoxOfficeTableau box = new BoxOfficeTableau(listing);
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
