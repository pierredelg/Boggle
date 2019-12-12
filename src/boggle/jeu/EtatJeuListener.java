package boggle.jeu;

import java.util.List;

public interface EtatJeuListener {

    void setTour(Tour tour);

    void finDuJeu(List<Tour> tourList);

}
