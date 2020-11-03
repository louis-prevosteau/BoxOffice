public class FilmChaine extends Film {

    private FilmChaine previous, next;

    public FilmChaine(String titre, String réalisateur, int année, int nbEntrées, FilmChaine next) {
        super(titre, réalisateur, année, nbEntrées);
        this.next = next;
    }

    public FilmChaine(String titre, String réalisateur, int année, int nbEntrées){
        this(titre, réalisateur, année, nbEntrées, null);
    }

    public FilmChaine getPrevious() {
        return previous;
    }

    public void setPrevious(FilmChaine previous) {
        this.previous = previous;
    }

    public FilmChaine getNext() {
        return next;
    }

    public void setNext(FilmChaine next) {
        this.next = next;
    }
}