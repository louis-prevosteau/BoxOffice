import java.io.FileNotFoundException;

public class BoxOfficeTableau extends BoxOffice {

    public final static int SIZE = 100;
    private Film[] elements;
    private static int cptFilm;

    public BoxOfficeTableau(String listing) throws FileNotFoundException {
        super(listing);
    }

    public static int getCptFilm() {
        return cptFilm;
    }

    public static void setCptFilm(int cptFilm) {
        BoxOfficeTableau.cptFilm = cptFilm;
    }

    @Override
    public void addFilm(String titre, String réalisateur, int année, int nbEntrées) {
        if (elements == null){
            this.elements = new Film[SIZE]; // Si le tableau elements n'existe pas, on le créé.
        }

        if (getCptFilm() < elements.length){
            if (getCptFilm() == 0){
                Film newFilm = new Film(titre, réalisateur, année, nbEntrées); // Si le tableau elements est vide, on créé un film.
                elements[getCptFilm()] = newFilm; // On l'ajoute dans le tableau
                setCptFilm(getCptFilm() + 1); // On incrémente le nombre de films dans le tableau.
            }else{
                boolean add = false;
                for (int i = 0 ; i < getCptFilm() ; i++){
                    if (elements[i].getTitre().equals(titre)){ // Si le film est déjà dans le tableau,
                        elements[i].setNbEntrées(nbEntrées); // On augmente son nombre d'entrées.
                        add = true;
                    }
                }
                if (add == false){
                    Film newFilm = new Film(titre, réalisateur,année,nbEntrées);
                    elements[getCptFilm()] = newFilm;
                    setCptFilm(getCptFilm() + 1);
                }
            }
        }else{ // Si le tableau est rempli,
            Film[] tmp = elements;
            elements = new Film[tmp.length * 2]; // On ultiplie la taille du tableau par 2.
            for (int i = 0 ; i < tmp.length ; i++)
                elements[i] = tmp[i];
        }
    }
}
