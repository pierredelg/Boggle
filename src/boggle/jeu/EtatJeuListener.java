package boggle.jeu;

import java.io.IOException;
import java.util.List;

public interface EtatJeuListener {

    void setTour(Tour tour) throws IOException;

    void finDuJeu(List<Tour> tourList);

}
