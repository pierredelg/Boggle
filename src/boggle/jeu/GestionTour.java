package boggle.jeu;


import java.util.ArrayList;
import java.util.List;

public class GestionTour implements NextTourListener {

    private final EtatJeuListener etatJeuListener;
    private int tour = -1;
    private List<Tour> tourList;

    public GestionTour(EtatJeuListener etatJeuListener, int nbreJoueur) {
        this.etatJeuListener = etatJeuListener;
        initialisationPartie(nbreJoueur);
    }

    private void initialisationPartie(int nbreJoueur) {

        this.tourList = new ArrayList<>();
        for (int i = 1; i <= nbreJoueur; i++) {
            this.tourList.add(new Tour(this, new Joueur("Joueur n° " + i)));
        }
    }

    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
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
            etatJeuListener.finDuJeu();
        }
    }
}
