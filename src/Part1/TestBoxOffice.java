package Part1;

public class TestBoxOffice extends BoxOffice {

    public TestBoxOffice(String listing) {
        super(listing);
    }

    public void addFilm(String titre, String réalisateur, int année, int nbEntrées){
        System.out.println("("+année+") "+titre+" Real.: "
                +réalisateur+" Entrees: "+nbEntrées);
    }

    public static void main(String[] args) {
        if (args.length < 1)
            System.out.println("Pas de fichier");
        else
            new TestBoxOffice(args[0]);
    }
}
