package boggle.jeu;

public class Tour implements TourListener {

    private Joueur joueur;
    private Timer timer;
    private boolean peutJouer;
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

    public boolean isPeutJouer() {
        return getTimer().isDisable() && getTimer().isTimerPositif();
    }

    @Override
    public void findDuTour() {
        nextTourListener.nextTour();
    }
}
