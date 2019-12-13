package boggle.jeu;


import boggle.config.ChargerConfig;
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
        this.nbreJoueur = ChargerConfig.getNombreJoueur();
        initialisationPartie();
    }

    private void initialisationPartie() throws IOException {

        this.tourList = new ArrayList<>();
        for (int i = 1; i <= nbreJoueur; i++) {
            this.tourList.add(new Tour(this, new Joueur("Joueur n° " + i)));
        }
    }

    public void start() throws IOException {
        nextTour();
    }

    @Override
    public void nextTour() throws IOException {
        tour++;
        if (tourList.size() > tour) {
            etatJeuListener.setTour(tourList.get(tour));
        } else {
            etatJeuListener.finDuJeu(tourList);
        }
    }
}
