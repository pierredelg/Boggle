package boggle.jeu;

public class Tour{

    private Joueur joueur;
    private Timer timer;

    public Tour(Joueur joueur){
        this.joueur =  joueur;
        this.timer = new Timer();
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
}
