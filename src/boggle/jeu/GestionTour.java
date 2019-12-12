package boggle.jeu;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionTour implements NextTourListener {

    private final EtatJeuListener etatJeuListener;
    private int tour = -1;
    private List<Tour> tourList;
    private int nbreJoueur;

    public GestionTour(EtatJeuListener etatJeuListener) throws IOException {
        this.etatJeuListener = etatJeuListener;
        lireFichierConfig();
        initialisationPartie();
    }

    private void initialisationPartie() throws IOException {

        this.tourList = new ArrayList<>();
        for (int i = 1; i <= nbreJoueur; i++) {
            this.tourList.add(new Tour(this, new Joueur("Joueur n° " + i)));
        }
    }


    public void start() {
        nextTour();
    }

    @Override
    public void nextTour() {
        tour++;
        if (tourList.size() > tour) {
            etatJeuListener.setTour(tourList.get(tour));
        } else {
            etatJeuListener.finDuJeu(tourList);
        }
    }

    private void lireFichierConfig() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("config/parametres.txt"));

        String ligne = "";
        ligne = bufferedReader.readLine();
        String[] split = ligne.split("=");
        this.nbreJoueur = Integer.parseInt(split[1]);
        bufferedReader.close();
    }
}
