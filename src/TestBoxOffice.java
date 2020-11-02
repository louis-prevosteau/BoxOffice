import java.io.IOException;

public class TestBoxOffice extends BoxOffice {

    public TestBoxOffice(String listing) throws IOException{
        super(listing);
    }

    public void addFilm(String titre, String réalisateur, int année, int nbEntrées){
        System.out.println("("+année+") "+titre+" Real.: "
                +réalisateur+" Entrees: "+nbEntrées);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1)
            System.out.println("Pas de fichier");
        else
            new TestBoxOffice(args[0]);
    }
}
