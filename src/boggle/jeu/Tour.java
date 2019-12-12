package boggle.jeu;

public class Tour implements TourListener {

    private Joueur joueur;
    private Timer timer;
    private final NextTourListener nextTourListener;

    public Tour(NextTourListener nextTourListener, Joueur joueur){
        this.nextTourListener = nextTourListener;
        this.joueur =  joueur;
        this.timer = new Timer(this);
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }


    @Override
    public void findDuTour() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        nextTourListener.nextTour();
    }
}
