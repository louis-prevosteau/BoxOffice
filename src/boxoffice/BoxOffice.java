package boxoffice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author PREVOSTEAU Louis
 */

public abstract class BoxOffice {

    private int nbLine; // Nombre de lignes du fichier

    public BoxOffice(String listing) {
       try {
           Scanner in = new Scanner(new File(listing));
           in.useDelimiter("\t");

           while (in.hasNextLine()){ // Lecture du fichier
               String line = in.nextLine();
               if (line.length() < 5)
                   break;
               nbLine++;
               String[] film = line.split("\t");
               String titre = film[0].substring(6);
               String réalisateur = film[1].substring(14);
               String tmp = film[2].substring(8);
               int année = Integer.parseInt(tmp);
               String ville = film[3];
               String tmp2 = film[4].substring(9);
               int nbEtrées = Integer.parseInt(tmp2);
               addFilm(titre,réalisateur,année,nbEtrées);
           }
           in.close();
       }catch (FileNotFoundException e){
           e.printStackTrace();
           System.exit(1);
       }catch (NumberFormatException e){
           e.printStackTrace();
           System.exit(1);
       }
    }

    public int getNbLine() {
        return nbLine;
    }

    public abstract void addFilm(String titre, String réalisateur, int année, int nbEntrées);
}
