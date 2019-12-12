package boggle.jeu;


import boggle.mots.GrilleLettres;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionTour {

    private List<Tour> tourList;

    public GestionTour(int nbreJoueur){
        initialisationPartie(nbreJoueur);
    }

    private void initialisationPartie(int nbreJoueur){

        this.tourList = new ArrayList<>();
        for(int i = 1; i <= nbreJoueur; i++){
            this.tourList.add(new Tour(new Joueur("Joueur n° "+i)));
        }
    }

    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
    }
}
