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
                    if (elements[i].getTitre().equals(titre) && elements[i].getRéalisateur().equals(réalisateur)){ // Si le film est déjà dans le tableau,
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

    public void swap(int a, int b){
        Film tmp = new Film(elements[a].getTitre(), elements[a].getRéalisateur(), elements[a].getAnnée(), elements[a].getNbEntrées());
        elements[a] = new Film(elements[b].getTitre(), elements[b].getRéalisateur(), elements[b].getAnnée(), elements[b].getNbEntrées());
        elements[b] = tmp;
    }

    public void triNbEntréesDESC(){
        for (int i = 1 ; i < getCptFilm() ; i++){
            int cpt = i;
            while (cpt > 0 && elements[cpt].getNbEntrées() > elements[cpt - 1].getNbEntrées()){ // Si cpt est plus grand que cpt - 1,
                swap(cpt, cpt-1); // On échange cpt et cpt - 1
                cpt--;
            }
        }
    }

    public void top3(){
        triNbEntréesDESC(); // Tri
        for (int i = 0 ; i < 3 ; i++){
            System.out.println("(" + elements[i].getAnnée() + ") " + elements[i].getTitre() + " entrées : " + elements[i].getNbEntrées()); // Affichage
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        if (args.length < 1)
            System.out.println("Pas de fichier");
        else {
            long start = System.currentTimeMillis();
            BoxOfficeTableau bo = new BoxOfficeTableau(args[0]);
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
